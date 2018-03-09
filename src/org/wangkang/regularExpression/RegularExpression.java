package org.wangkang.regularExpression;

public class RegularExpression {

	/**
	 * @param args
	 */
	public static boolean checkQuote(String command){
		String regex = "^([\\d]{1,}[.]){2,}.*$";
		return command.matches(regex);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(checkQuote("1222.ssss"));
	}

}
