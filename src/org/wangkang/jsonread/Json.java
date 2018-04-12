package org.wangkang.jsonread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.wangkang.entity.Message;
import org.wangkang.regularExpression.RegularExpression;

public class Json {
	private ArrayList<Message> messageList;
	private ArrayList<Message> quoteList;
	private int messageNum;
	
    public int getMessageNum() {
		return messageNum;
	}

	public ArrayList<Message> getMessageList() {
		return messageList;
	}
	
	public ArrayList<Message> getQuoteList() {
		return quoteList;
	}

	public Json(){
    	messageList = new ArrayList<Message>();
    	quoteList = new ArrayList<Message>();
    	readJson();
    }
    
    public void readJson(){
    	//String s = ReadFile(Json.class.getResourceAsStream("/exported_sns.json")); 
    	//String s = ReadFile(Json.class.getResourceAsStream("/exported_sns_20180213.json")); 
    	//String s = ReadFile(Json.class.getResourceAsStream("/exported_sns_20180223.json"));
    	//String s = ReadFile(Json.class.getResourceAsStream("/exported_sns_20180309.json"));
    	String s = ReadFile(Json.class.getResourceAsStream("/exported_sns_20180324.json"));
    	//String s = ReadFile(Json.class.getResourceAsStream("/exported_sns_20180412.json"));
    	//System.out.println(s);
    	//String jsonStr=URLDecoder.decode(s,"utf-8");  
    	//String str=new String(s.getBytes("GB2312"),"8859_1");
		
    	JSONArray JsonArr = JSONArray.fromObject(s);
    	int counter = 0;
    	for(int i=0;i<JsonArr.size();i++){
    		Message m = packageMsg();
    		JSONObject jsonObj = JsonArr.getJSONObject(i);
    		//System.out.println(jsonObj.get("authorName")+"."+jsonObj.get("timestamp")+":"+jsonObj.get("content"));
    		m.setAuthorName((String)jsonObj.get("authorName"));
    		m.setCreateTime(TimeStamp2Date(""+jsonObj.get("timestamp")));
    		m.setContent((String)jsonObj.get("content"));
    		messageList.add(m);
    		counter++;
    		
    		//add quotes
    		if(RegularExpression.checkQuote((String)jsonObj.get("content"))){
    			Message mq = new Message();
    			mq.setAuthorName((String)jsonObj.get("authorName"));
    			mq.setCreateTime(TimeStamp2Date(""+jsonObj.get("timestamp")));
        		mq.setContent((String)jsonObj.get("content"));
        		quoteList.add(mq);
    		}
    	} 
    	messageNum = counter;
    	//Message m = packageMsg();
    	//System.out.println("***add message:"+m.getAuthorName()+"***");
    	//messageList.add(m);
    }
    
    //copy
    public String TimeStamp2Date(String timestampString) {
        String formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
    private Message packageMsg(){
    	Message m = new Message();
    	m.setAuthorName("wk");
    	//m.setCreateTime(new Date());
    	m.setContent("test");
    	return m;
    }
    
    //copy
    public String ReadFile(InputStream in) { 
        BufferedReader reader = null;  
        String laststr = "";  
		try {
			reader = new BufferedReader(new InputStreamReader(in,"utf-8"));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr = laststr + tempString;  
            }  
            reader.close();  
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        
		//System.out.println(laststr);
        return laststr;  
    }  
    
    public static void main(String[] args) {
    	Json j = new Json();
    	//String s = j.ReadFile("./src/exported_sns.json");  
        //System.out.println(s); 
    	//j.readJson();
    	System.out.println("你好"); 
	}
}
