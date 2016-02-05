package com.jj.web.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverUtil {

	private WebDriver page;
	
	@Before
	public void before(){
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\shaojiabing\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");// 指定驱动路径
//		page = new HtmlUnitDriver();
		page = new ChromeDriver();
	}
	
	@Test
	public void testSearchPage(){
		Options manager = page.manage();
		page.get("http://127.0.0.1:8092");
		 System.out.println("start chrome browser succeed...");   
		 
		 page.get("http://www.google.cn");  
		  
//	        WebElement searchBox = page.findElement(By.name("q"));  
//	        searchBox.sendKeys("JavaEye");  
//	  
//	        WebElement subBtn = page.findElement(By.name("btnG"));  
//	        subBtn.submit();  
//	  
//	        WebElement result = page.findElement(By.linkText("http://www.iteye.com"));  
//	        assertNotNull(result); 
	}
	
}
