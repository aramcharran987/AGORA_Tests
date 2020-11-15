package com.fdmgroup.test;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fdmgroup.util.DriverUtilities;
import java.awt.*;

public class TestLogin {
	DriverUtilities driverUtilities;
	WebDriver driver;
	
	@Test 
	public void testLogin() throws Exception {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		final String ANSI_RED= "\u001B[31m";
		final String ANSI_RESET= "\u001B[0m";
		//Set the property of the webdriver to chrome driver
				//System.setProperty("webdriver.chrome.driver","/Users/blue_/Desktop/ForumDM/chromedriver");
				File logins = new File("AGORA_TestLogins.xlsx");
				FileInputStream fis = new FileInputStream(logins);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet = wb.getSheetAt(0);
				
				int rowcount = sheet.getLastRowNum();
		
				driver.get("http://127.0.0.1/");
			for(int i=1 ; i<=rowcount; i++) {
				//driver.get("https://dev61841.service-now.com/sp/?id=landing");
				
				XSSFCell cell = sheet.getRow(i).getCell(0);
				XSSFCell cell2 = sheet.getRow(i).getCell(1);
				String username_holder="";
				String password_holder="";
				String password_converter="";
				
				if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
					username_holder=cell.getStringCellValue();
				}else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
					username_holder=String.valueOf(cell.getNumericCellValue());
				}else if (cell.getCellType()==Cell.CELL_TYPE_BLANK) {
					username_holder="";
				}
				
				if(cell2.getCellType()==Cell.CELL_TYPE_STRING) {
					password_holder=cell2.getStringCellValue();
				}else if (cell2.getCellType()==Cell.CELL_TYPE_NUMERIC) {
					password_converter=String.valueOf(cell2.getNumericCellValue()).substring(0,String.valueOf(cell2.getNumericCellValue()).length()-2);
					password_holder=password_converter;
				}else if (cell2.getCellType()==Cell.CELL_TYPE_BLANK) {
					password_holder="";
				}
				
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
				//Thread.sleep(800);
				WebElement username = driver.findElement(By.id("username"));
				WebElement password = driver.findElement(By.id("password"));
				
				username.sendKeys(username_holder);
				password.sendKeys(password_holder);
				
				username.submit();
				
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
				String page_checker = driver.getCurrentUrl();
				String holder="http://127.0.0.1/";
				
				if(page_checker.equals(holder)) {
					System.out.println("Login test successfull: Username: " + username_holder + " Password: " + password_holder);
				}else {
					
					System.err.println("Login test fail: Username: " + username_holder + " Password: " + password_holder);
					System.err.println("Page URL: "+ page_checker);
					continue;
				}
				
				//WebElement user_dropdown = driver.findElement(By.id("user_info_dropdown"));
				//user_dropdown.click();
				//WebElement logout = driver.findElement(By.linkText("Logout"));
				String source = driver.getPageSource();
				if(driver.findElement(By.linkText("Logout")) != null) {
					WebElement logout = driver.findElement(By.linkText("Logout"));
					
					logout.click();
				}
				
			}
			fis.close();
			driver.quit();
				
			}
			

		}

	
	


