package com.fdmgroup.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverUtilities {
	//Implement singleton design pattern
	//1. Create a static instance of a class
	private static DriverUtilities driverUtilities;
	private WebDriver driver;
	//2. Make the constructor private so that user can't call the default constructor
	private DriverUtilities() {
		super();
	}
	//Create a static method that will return the instance of the single object 
	public static DriverUtilities getInstance() {
		if(driverUtilities == null) {
			driverUtilities = new DriverUtilities();
		}
		return driverUtilities; //End of singleton design pattern
	}
	public WebDriver getDriver() {
		if(driver == null) {
			createDriver();
		}
		return driver;
	}
	private void createDriver() {
		String driverName = getDriverName();
		switch (driverName) {
		case "google chrome":
			System.setProperty("Webdriver.chrome.driver", "chromedriver.exe");
			this.driver = new ChromeDriver();
			break;
			
		case "firefox":
			System.setProperty("Webdriver.firefox.driver", "firefoxdriver.exe");
			this.driver = new FirefoxDriver();
			break;
			
		case "microsoft edge":
			System.setProperty("Webdriver.fedge.driver", "edgedriver.exe");
			this.driver = new EdgeDriver();
			break;
			
			default:
				break;
		}
		
	}
	private String getDriverName() {
	//Open close principal: Closed for changes open for extensions	
	Properties config = new Properties();
	String driverName = "";
	try {
		config.load(new FileInputStream("config.properties"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	for (String key : config.stringPropertyNames()) {
		if (key.equals("browser"))
			driverName = config.getProperty(key);
	}
	return driverName;
	}

}
