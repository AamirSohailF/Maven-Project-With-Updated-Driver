package com.etrading.skillkart.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileLibrary
{
	public static String getData(String filePath, String key)
	{
		String data="";
		try
		{
			FileInputStream propertyFile = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(propertyFile);
			data = prop.getProperty(key);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		return data;
	}	
}
