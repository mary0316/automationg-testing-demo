package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pages.LandingPage;
import pages.ProductsPage;
import utilities.ExtentManager;

@Listeners(utilities.ExtentListeners.class)
public class TC003_SearchProduct extends BaseClass {

	LandingPage landP;
	ProductsPage productsP;
	JavascriptExecutor jse;
	
	ExtentReports report;
	ExtentManager extentmanager;
	ExtentTest test;

	@BeforeTest
	public void setup() {
		report = new ExtentReports(
				"C:\\eclipse-workspace\\qaProject1\\resources\\reports\\TC003_SearchProduct.html");
		test = report.startTest("Search Product");
		
	}

	@Test
	public void searchProduct() {

		landP = new LandingPage(driver);
		test.log(LogStatus.INFO, "TC003_SearchProduct");
		landP.doSearch();
//		log.info("Enter Serach keyword");
		test.log(LogStatus.INFO, "Searched products");
		productsP = new ProductsPage(driver);
		test.log(LogStatus.INFO, "Open Products page");
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(250,450)");
		test.log(LogStatus.INFO, "Scroll down");
//		log.info("Scroll down");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		productsP.selectQuickView();
		test.log(LogStatus.INFO, "Display Quick View");
//		log.info("Quick view displayed");

		//landP.pause(3000);
		String actual = driver.getTitle();
		String expected = "Search - My Store";
		Assert.assertEquals(actual, expected);
		test.log(LogStatus.INFO, "Verified Search function");
//		log.info("Verified!!");

		productsP.clzFancyBox();
		test.log(LogStatus.INFO, "Closed Quick view");
//		log.info("Closed Quick View");

		productsP.showList();
		test.log(LogStatus.INFO, "Display List View");
//		log.info("Listview displayed");
		//landP.pause(3000);
		productsP.pDetails();
		test.log(LogStatus.INFO, "Display Product Details");
//		log.info("Shoes Product details");
		//landP.pause(3000);
		actual = driver.getTitle();
		expected = "Printed Dress - My Store";
		Assert.assertEquals(actual, expected);
		test.log(LogStatus.INFO, "TC003_SearchProduct : completed and passed!!!");
//		log.info("Verified TC003");
	}
	
	@AfterTest
	public void tearDown() {
//		log.info("Close browser");
		driver.quit();
//		log.info("Post-condition ");
		report.endTest(test);
//		log.info("erase the previous data on the report");
		report.flush();
	}
}
