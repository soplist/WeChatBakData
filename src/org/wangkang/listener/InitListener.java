package org.wangkang.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.wangkang.entity.Message;
import org.wangkang.jsonread.Json;

public class InitListener implements  ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		
		Json json = new Json();
		ArrayList<Message> messageList = json.getMessageList();
		ArrayList<Message> quoteList = json.getQuoteList();
		int msgNum = json.getMessageNum();
		System.out.println("***add message list:size."+messageList.size()+"***");
        context.setAttribute("msgs",messageList);
        context.setAttribute("msgNum",msgNum);
        context.setAttribute("quoteList",quoteList);
	}

}
