package com.etrading.skillkart.generic;

public class Utilities
{
	public static void sleepInSeconds(long seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int returnInteger(String text)//"5.0"
	{
		return Integer.parseInt(text.split("\\.")[0]);
	}
	
	public static String getIntText(String text)//"10.0"
	{		
		return Integer.toString(returnInteger(text));
	}
	
	public static void closeThePopUp()
	{
		
	}
}
