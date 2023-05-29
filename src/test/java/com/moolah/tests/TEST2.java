package com.moolah.tests;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TEST2 {
	
	XSSFRow row;
	XSSFCell cell;
	String elementName = null;
	int i;
	int [] rgb;
	StringBuilder sb = new StringBuilder();

		WebDriver driver = null;
		
		public void storeMismatchtoFile(String pageName, String elementName, String property, String actual, String expected) throws Exception {
			
			System.out.println(actual + "----------------"+expected);
			File file = new File("./resources/datamismatch.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("mismatch");

			System.out.println(sheet.getLastRowNum());
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			
			
			for(int i = sheet.getLastRowNum() + 1; i <= sheet.getLastRowNum() + 1; i++) {
				
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(sdf.format(timestamp));
				
				cell = row.createCell(1);
				cell.setCellValue(pageName);
				
				cell = row.createCell(2);
				cell.setCellValue(elementName);
				
				cell = row.createCell(3);
				cell.setCellValue(property);
				
				cell = row.createCell(4);
				cell.setCellValue(actual);
				
				cell = row.createCell(5);
				cell.setCellValue(expected);
				
				break;
			}
			
			
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			System.out.println("END OF WRITING DATA IN EXCEL");
			
		}
		
		
	    @Test
	    public void test2() throws Exception {
	    	//Launch the application
	    	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://20.198.3.41:3000/");
			Thread.sleep(10000);
			
			
			/**
			 * ===================HOME PAGE NAVIGATION STARTS=========================
			 */
			//Verify MoolahMate Logo width
			String logoActualWidth = driver.findElement(By.xpath("(//img[@alt='hamburger mobile icon'])[1]")).getCssValue("width");
			String logoExpectedWidth = "203px";
			if(!(logoActualWidth.equals(logoExpectedWidth))) {
				storeMismatchtoFile("Home Page", "MoolahMate Logo", "width", logoActualWidth, logoExpectedWidth);
			}
			
			//Verify MoolahMate Logo height
			String logoActualHeight = driver.findElement(By.xpath("(//img[@alt='hamburger mobile icon'])[1]")).getCssValue("height");	
			String logoExpectedHeight = "24px";
			if(!(logoActualWidth.equals(logoExpectedWidth))) {
				storeMismatchtoFile("Home Page", "MoolahMate Logo", "height", logoActualHeight, logoExpectedHeight);
			}
			
			//Verify How It Works color
			String howItWorksActualColor = driver.findElement(By.xpath("//a[@class='navInActiveStyle' and contains(text(), 'How it works')]")).getCssValue("color");
			i = Integer.decode("#27272a");
			int[] rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String howItWorksExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(howItWorksActualColor.equals(howItWorksExpectedColor))) {
				storeMismatchtoFile("Home Page", "How It Works", "color", howItWorksActualColor, howItWorksExpectedColor);
			}
			
			//Verify Calculators color
			String calculatorsActualColor = driver.findElement(By.xpath("//a[@class='navInActiveStyle' and contains(text(), 'Calculators')]")).getCssValue("color");
			i = Integer.decode("#27272a");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String calculatorsExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(calculatorsActualColor.equals(calculatorsExpectedColor))) {
				storeMismatchtoFile("Home Page", "Calculators", "color", calculatorsActualColor, calculatorsExpectedColor);
			}
			
			//Verify Learn color
			String learnActualColor = driver.findElement(By.xpath("//a[@class='navInActiveStyle' and contains(text(), 'Learn')]")).getCssValue("color");
			i = Integer.decode("#27272a");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String learnExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(learnActualColor.equals(learnExpectedColor))) {
				storeMismatchtoFile("Home Page", "Learn", "color", learnActualColor, learnExpectedColor);
			}
			
			//Verify About color
			String aboutActualColor = driver.findElement(By.xpath("//a[@class='navInActiveStyle' and contains(text(), 'About')]")).getCssValue("color");
			i = Integer.decode("#27272a");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String aboutExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(aboutActualColor.equals(aboutExpectedColor))) {
				storeMismatchtoFile("Home Page", "About", "color", aboutActualColor, aboutExpectedColor);
			}
			
			//Verify Login Button color
			String LoginButtonActualColor = driver.findElement(By.xpath("//a[text()='Login']/span")).getCssValue("color");
			i = Integer.decode("#27272a");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String LoginButtonExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(LoginButtonActualColor.equals(LoginButtonExpectedColor))) {
				storeMismatchtoFile("Home Page", "Login Button", "color", LoginButtonActualColor, LoginButtonExpectedColor);
			}
			
			//Verify Start your Free Trial color
			String StartyourFreeTrialButtonActualColor = driver.findElement(By.xpath("//a[text()='Start your Free Trial']/span")).getCssValue("color");
			i = Integer.decode("#29de94");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String StartyourFreeTrialButtonExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(StartyourFreeTrialButtonActualColor.equalsIgnoreCase(StartyourFreeTrialButtonExpectedColor))) {
				storeMismatchtoFile("Home Page", "Start your Free Trial Button", "color", StartyourFreeTrialButtonActualColor, StartyourFreeTrialButtonExpectedColor);
			}
			
			//Verify Smarter Simpler color
			String smarterSimplerTextActualColor = driver.findElement(By.xpath("//h6[text()='Smarter, Simpler Money Management Starts Here']")).getCssValue("color");
			i = Integer.decode("#048b67");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String smarterSimplerTextExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(smarterSimplerTextActualColor.equals(smarterSimplerTextExpectedColor))) {
				storeMismatchtoFile("Home Page", "Smarter, Simpler Money Management text", "color", smarterSimplerTextActualColor, smarterSimplerTextExpectedColor);
			}
			
			//Verify Smarter Simpler font size
			String smarterSimplerTextActualFontSize = driver.findElement(By.xpath("//h6[text()='Smarter, Simpler Money Management Starts Here']")).getCssValue("font-size");
			String smarterSimplerTextExpectedFontSize = "16px";
			if(!(smarterSimplerTextActualFontSize.equals(smarterSimplerTextExpectedFontSize))) {
				storeMismatchtoFile("Home Page", "Smarter, Simpler Money Management font text", "font size", smarterSimplerTextActualFontSize, smarterSimplerTextExpectedFontSize);
			}
			
			//Verify Save More font size
			String saveMoreTextActualFontSize = driver.findElement(By.xpath("//h1[text()='Save more, spend less and reduce your financial stress.']")).getCssValue("font-size");
			String saveMoreTextExpectedFontSize = "56px";
			if(!(saveMoreTextActualFontSize.equals(saveMoreTextExpectedFontSize))) {
				storeMismatchtoFile("Home Page", "Save More text", "font-size", saveMoreTextActualFontSize, saveMoreTextExpectedFontSize);
			}
			
			//Verify Save More text color
			String saveMoreTextActualColor = driver.findElement(By.xpath("//h1[text()='Save more, spend less and reduce your financial stress.']")).getCssValue("color");
			i = Integer.decode("#27272A");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String saveMoreTextExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(saveMoreTextActualColor.equalsIgnoreCase(saveMoreTextExpectedColor))) {
				storeMismatchtoFile("Home Page", "Save More text", "color", saveMoreTextActualColor, saveMoreTextExpectedColor);
			}
			
			//Verify MoolahMate is an easy to use color
			String MoolahMateisaneasytouseTextActualColor = driver.findElement(By.xpath("//h5[contains(text(),'MoolahMate is an easy-to-use money tracker')]")).getCssValue("color");
			i = Integer.decode("#27272A");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String MoolahMateisaneasytouseTextExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(MoolahMateisaneasytouseTextActualColor.equalsIgnoreCase(MoolahMateisaneasytouseTextExpectedColor))) {
				storeMismatchtoFile("Home Page", "Moolah Mate is an easy to use text", "color", MoolahMateisaneasytouseTextActualColor, MoolahMateisaneasytouseTextExpectedColor);
			}
			
			//Verify MoolahMate is an easy to use font size
			String MoolahMateisaneasytouseTextActualFontSize = driver.findElement(By.xpath("//h5[contains(text(),'MoolahMate is an easy-to-use money tracker')]")).getCssValue("font-size");
			String MoolahMateisaneasytouseTextExpectedFontSize = "20px";
			if(!(MoolahMateisaneasytouseTextActualFontSize.equals(MoolahMateisaneasytouseTextExpectedFontSize))) {
				storeMismatchtoFile("Home Page", "Moolah Mate is an easy to use text", "font-size", MoolahMateisaneasytouseTextActualFontSize, MoolahMateisaneasytouseTextExpectedFontSize);
			}
			
			//Verify get Started Button color
			String getStartedButtonActualColor = driver.findElement(By.xpath("(//a[text()='Get Started'])[1]/span")).getCssValue("color");
			i = Integer.decode("#29de94");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String getStartedButtonExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(getStartedButtonActualColor.equalsIgnoreCase(getStartedButtonExpectedColor))) {
				storeMismatchtoFile("Home Page", "Get Started button", "color", getStartedButtonActualColor, getStartedButtonExpectedColor);
			}
			
			//Verify get Started Button Text color
			String getStartedButtonTextActualColor = driver.findElement(By.xpath("(//a[text()='Get Started'])[1]")).getCssValue("color");
			i = Integer.decode("#27272A");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String getStartedButtonTextExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(getStartedButtonTextActualColor.equalsIgnoreCase(getStartedButtonTextExpectedColor))) {
				storeMismatchtoFile("Home Page", "Get Started button text", "color", getStartedButtonTextActualColor, getStartedButtonTextExpectedColor);
			}
				
			//Verify Learn More Button text color
			String learnmoreButtonTextActualColor = driver.findElement(By.xpath("(//a[text()='Learn More'])[1]")).getCssValue("color");
			i = Integer.decode("#27272A");
			rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};
			String learnmoreButtonTextExpectedColor = "rgba("+rgb[0]+", "+rgb[1]+", "+rgb[2]+", 1)";
			if(!(learnmoreButtonTextActualColor.equalsIgnoreCase(learnmoreButtonTextExpectedColor))) {
				storeMismatchtoFile("Home Page", "Learn More button text", "color", learnmoreButtonTextActualColor, learnmoreButtonTextExpectedColor);
			}
			
			/**
			 * ===================HOME PAGE NAVIGATION ENDS=========================
			 */
			
				
			driver.close();
			driver.quit();
			
			
	    }

	}


