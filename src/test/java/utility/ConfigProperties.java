package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigProperties 
{
	private static Properties prop = new Properties();
	public static void loadPropties()
	{
		try
		{
			prop.load(new FileInputStream("config/avactis.properties"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("config.properties not found");
		} 
		catch (IOException e) 
		{
			System.out.println("IO exception while accessing config.properties");
			e.printStackTrace();
		}
	}
	public static String getProperty(String keyValue)
	{
		return prop.getProperty(keyValue);
	}
	
}
