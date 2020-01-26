package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ReadAndRecordData {

	public static HashMap<String,String> storageData=new HashMap<String, String>();
	
	public void setData(String key,String value) {
		storageData.put(key,value);
	}
	public String getData(String key) {
		return storageData.get(key).toString();
	}

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
