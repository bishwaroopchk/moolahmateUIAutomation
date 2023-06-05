package com.moolah.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BugTracker {

	WebDriver driver = null;

	@Test
	public void runScript() throws InterruptedException, IOException {

		File file = new File("./resources/BugTracker.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("BugTracker");
		XSSFRow row;
		XSSFCell cell;

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://projects.zoho.com/portal/zirkeltechinc#mybugs/0");

		driver.findElement(By.id("login_id")).sendKeys("rashid@brickredsys.in");
		driver.findElement(By.id("nextbtn")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("password")).sendKeys("Table@123");
		driver.findElement(By.id("nextbtn")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Issues']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@id='bugViewType']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Plain']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='My Open']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='All Issues']")).click();
		Thread.sleep(3000);
		
		List<WebElement> elements = null;

		for (int x = 0; x < 25; x++) {

			
			/**
			 * CODE FOR ISSUE COLUMN
			 */
			elements = driver.findElements(By.xpath("//tbody[@class='table-group-list']/tr/td/div/div/div/div/span"));
			for (int i = 0; i < elements.size(); i++) {
				for (int j = i + 1; j <= i + 1; j++) {
					String IssueText1 = elements.get(i).getText();
					String IssueText2 = driver.findElement(By.xpath("(//tbody[@class='table-group-list']/tr/td/div/div/div/div/span/following-sibling::div)["+ j + "]")).getText();
					String issueName = IssueText1 + " " + IssueText2;
					row = sheet.createRow(sheet.getLastRowNum() + 1);
					cell = row.createCell(0);
					cell.setCellValue(issueName);
					break;
				}
			}
			
			
			/**
			 * CODE FOR PROJECT COLUMN
			 */
			elements = driver.findElements(By.xpath("//tbody[@class='table-group-list']/tr/td/div/div/div/div/span"));
			for (int i = 0; i < elements.size(); i++) {
				for (int j = i + 1; j <= i + 1; j++) {
					String IssueText1 = elements.get(i).getText();
					String IssueText2 = driver.findElement(By.xpath("(//tbody[@class='table-group-list']/tr/td/div/div/div/div/span/following-sibling::div)["+ j + "]")).getText();
					String issueName = IssueText1 + " " + IssueText2;
					row = sheet.createRow(sheet.getLastRowNum() + 1);
					cell = row.createCell(0);
					cell.setCellValue(issueName);
					break;
				}
			}
			
			
			
			
			

			boolean enabled = driver.findElement(By.xpath("//i[@class='zoho-next-arrow']")).isEnabled();
			String variable = driver.findElement(By.xpath("//div[@class='pagination-total']")).getText();
			String[] params = variable.split(" ");
			String paginationTotal = params[2];
			String paginationRange = driver.findElement(By.xpath("//div[@class='pagination-range']/span[3]")).getText();

			if (enabled && !(paginationTotal.equals(paginationRange))) {
				driver.findElement(By.xpath("//i[@class='zoho-next-arrow']")).click();
				Thread.sleep(5000);
			} else if (paginationTotal.equals(paginationRange)) {
				break;
			}
		}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		System.out.println("END OF WRITING DATA IN EXCEL");

		Thread.sleep(5000);
		driver.close();
		driver.quit();

	}

}
