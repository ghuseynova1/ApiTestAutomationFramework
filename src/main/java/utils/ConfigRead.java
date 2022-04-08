package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {
	public static Properties readConfigProperties (String fileName) throws Exception {
		fileName = fileName.trim();
		
		Properties configProperties = new Properties();
		
		InputStream inStream = new FileInputStream(fileName);
		
		configProperties.load(inStream);
		
		return configProperties;
	}

}
