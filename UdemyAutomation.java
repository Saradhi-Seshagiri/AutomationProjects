package com.udemy.businessLayer;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.ScrollAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.udemy.services.GenericServices;

public class UdemyAutomation {
	
	//Added new comment on 13th Feb 2022 ! 9:54 AM IST.
	//Added 2nd new comment on 13th Feb 2022 ! 10:06 AM IST.
	//Added 3rd new comment on 13th Feb 2022 ! 10:35 AM IST.
	//Added 4th new comment on 13th Feb 2022 ! 10:37 AM IST.

	//Member variables..
	private WebDriver newBrowser;
	private String applicationURL = "https://www.udemy.com/";
	
	//Member functions to Automate different flows...
	public String automatingCourseSearching() throws InterruptedException {
		try {
			//Step 1 : Creating the browser instance of our choice...
			System.setProperty("webdriver.chrome.driver", "E:\\Online_Trainings\\October-2021_Batch\\chromedriver.exe");
			newBrowser = new ChromeDriver();
		
			//Step 2 : Launching the browser instance and loading the AUT..
			newBrowser.get(applicationURL);
		
			newBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Step 3 : Business logic for automating course search feature...
			WebElement searchTextBox = newBrowser.findElement(By.name("q"));
			//searchTextBox.sendKeys("Selenium");
			
			WebElement searchButton = newBrowser.findElement(By.id("udemy"));
			//searchButton.click();
			
			//Using the AUI API..
			Actions newActions = new Actions(newBrowser);
			
			Action newAction = newActions.moveToElement(searchTextBox)
										.sendKeys(searchTextBox,"Selenium")
										.moveToElement(searchButton)
										.click(searchButton)
										.build();
			//Thread.sleep(50000);
			newAction.perform();
			
			System.out.println("Search Successfull...");
		} // Closing of try..
		catch (IllegalArgumentException lae) {
			// TODO: handle exception
			System.out.println("IllegalArgumentException...");
		}
		finally {
		newBrowser.quit(); }//Closing all the instances..
		
		return "Success!!";
	}
	
	public String automatingUdemySigIn() throws IOException {
		try {
			//Step 1 : Creating the browser instance of our choice...
			newBrowser = GenericServices.getBrowserInstance();
		
			//Step 2 : Launching the browser instance and loading the AUT..
			newBrowser.get(applicationURL);
	
			//Unconditional wait/implicit wait...
			newBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Optional to the launch the browser in max.mode...
			newBrowser.manage().window().maximize();
					
			//Step 3 : Business logic for automating sign in feature...
			//WebElement signIn = newBrowser.findElement(By.xpath("//*[@id=\'udemy\']/div[2]/div[1]/div[3]/div[7]/a/span"));
			WebElement signIn = newBrowser.findElement(By.cssSelector("a.udlite-btn.udlite-btn-medium.udlite-btn-primary.udlite-heading-sm"));
			//signIn.click();
			//WebDriverWait ww = new WebDriverWait(newBrowser, 10);
			//ww.until(ExpectedConditions.visibilityOf(signIn));
			
			WebElement fullNameTextBox = newBrowser.findElement(By.id("id_fullname"));
			//fullNameTextBox.sendKeys("Radhika");
			WebElement emailTextBox = newBrowser.findElement(By.id("email--1"));
			//emailTextBox.sendKeys("radhi18020@gmail.com");
			WebElement passwordTextBox = newBrowser.findElement(By.id("password"));
			//passwordTextBox.sendKeys("12345");
			WebElement signUp = newBrowser.findElement(By.id("submit-id-submit"));
			//signUp.click();
			
			//Using the AUI API to handle the generic events..
			Actions newActions = new Actions(newBrowser);
			
			Action newAction = newActions.moveToElement(signIn)
					  					.click()
					  					.moveToElement(fullNameTextBox)
					  					.sendKeys(fullNameTextBox, "Radhika")
					  					.moveToElement(emailTextBox)
					  					.sendKeys(emailTextBox, "radhi18020@gmail.com")
					  					.moveToElement(passwordTextBox)
					  					.sendKeys(passwordTextBox, "12345")
					  					.moveToElement(signUp)
					  					.click(signUp).build();
			
			newAction.perform(); //Performing all the events as a single batch..

			System.out.println("Search Successfull...");
		} // Closing of try..
		catch (IllegalArgumentException lae) {
			// TODO: handle exception
			System.out.println("IllegalArgumentException...");
		}
		finally {
		newBrowser.quit(); }//Closing all the instances..
		
		return "Success!!";
   }
	
	public String automatingHandlingPopups() throws IOException {
		try {
			//Step 1 : Creating the browser instance of our choice...
			newBrowser = GenericServices.getBrowserInstance();
		
			//Step 2 : Launching the browser instance and loading the AUT..
			newBrowser.get("https://www.naukri.com/");
		
			//Step 3 : Business logic for handling Popups...
				//Step 3.1 : Get the unique id/handler for the popup window..
					Set<String> windowsId = newBrowser.getWindowHandles();
					System.out.println("Total No. of Popups : " + windowsId.size());

					for (String handle : windowsId) {
						newBrowser.switchTo().window(handle);  //Switching the focus onto the window

						System.out.println(newBrowser.getTitle());

						// if(!"Jobs-Recruitment-Job Search -Employment-Job Vacancies
						if (!"Automation...".equalsIgnoreCase(newBrowser.getTitle()))
							newBrowser.close();
					
					}//End Of For..Each loop
					System.out.println("Successfully Automated...Great!!!");
				
		} // Closing of try..
		catch (IllegalArgumentException lae) {
			// TODO: handle exception
			System.out.println("IllegalArgumentException...");
		}
		finally {
		newBrowser.quit(); }//Closing all the instances..
		
		return "Success!!";
   }
	
	public String automatingDragAndDrp() throws IOException, InterruptedException {
		try {
			//Step 1 : Creating the browser instance of our choice...
			newBrowser = GenericServices.getBrowserInstance();
		
			//Step 2 : Launching the browser instance and loading the AUT..
			newBrowser.get("https://demoqa.com/droppable/");
			
			//Additional features for browser instance..
			newBrowser.manage().window().maximize();;
			newBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			GenericServices.securedAccessServices();
		
			//Thread.sleep(10000);
			//Step 3 : Business logic for handling Popups...
				
				//Locating the element to click on Interactions button...
				Actions newActions = new Actions(newBrowser);
				
				//WebElement interactionButton = newBrowser.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div/div[5]/div/div[1]"));
				//interactionButton.click();
				
				//Scrolling down till the element is visible...
					//JavascriptExecutor js = (JavascriptExecutor) newBrowser;
					//js.executeScript("window.scrollBy(0,1000)", "");
				
				//Step 3.1 : Automating drag & drop using AUI API and locating the drop and drag elements..
					WebElement source = newBrowser.findElement(By.id("draggable"));
					WebElement destination = newBrowser.findElement(By.id("droppable"));
			
					newActions.dragAndDrop(source, destination).build().perform();		
					
					Thread.sleep(5000);
					System.out.println("Successfully Automated...Great!!!");
				
		} // Closing of try..
		catch (IllegalArgumentException lae) {
			// TODO: handle exception
			System.out.println("IllegalArgumentException...");
		}
		finally {
		newBrowser.quit(); }//Closing all the instances..
		
		return "Success!!";
   }
	
	
	public String automatingSignInUsingDDF() throws IOException, InterruptedException {
		try {
			//Step 1 : Creating the browser instance of our choice...
			newBrowser = GenericServices.getBrowserInstance();
		
			//Step 2 : Launching the browser instance and loading the AUT..
			newBrowser.get("https://www.udemy.com/join/signup-popup/?locale=en_US&response_type=html&next=https%3A%2F%2Fwww.udemy.com%2Fjoin%2Flogin-popup%2F%3Flocale%3Den_US%26response_type%3Dhtml%26next%3Dhttps%253A%252F%252Fwww.udemy.com%252Fjoin%252Flogin-popup%252F%253Flocale%253Den_US%2526next%253Dhttps%25253A%25252F%25252Fwww.udemy.com%25252F%2526response_type%253Dhtml");
			
			//Additional features for browser instance..
			newBrowser.manage().window().maximize();
			newBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Step 3 : Business logic for reading the excel file...
			GenericServices.readingExcelFile();
			
			Thread.sleep(5000);
								
			System.out.println("Successfully Automated...Great!!!");
				
		} // Closing of try..
		catch (IllegalArgumentException lae) {
			// TODO: handle exception
			System.out.println("IllegalArgumentException...");
		}
		finally {
		newBrowser.quit(); }//Closing all the instances..
		
		return "Success!!";
   }
}
