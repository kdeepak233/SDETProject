package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadAndRecordData {

		public String getPropertyData(String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream=new FileInputStream(new File(""));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property=new Properties();
		try {
			property.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}
	
}
