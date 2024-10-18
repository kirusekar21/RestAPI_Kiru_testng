package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configReader {

	public static Properties initprop()   {
		Properties prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(propFile);
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;	



	}

}
