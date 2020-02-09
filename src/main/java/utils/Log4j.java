package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {
public Logger logger;
	public  Log4j()
	{
		logger = Logger.getLogger("Log4j");
		PropertyConfigurator.configure("Log4j.properties");
	}
}