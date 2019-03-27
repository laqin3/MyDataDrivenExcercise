import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class ParkingLot {
	public static WebDriver driver;

	@BeforeMethod
	public void beforeEachMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\laqin3\\eclipse-workspace\\fromRahul\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterEachMethod() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@Test(enabled = true)
	public void parkCalc() throws InterruptedException, IOException {
		// 1.go to URL
		driver.get("http://adam.goucher.ca/parkcalc/");

		// 2.getTitle and assert
		String websiteTitle = driver.getTitle();
		System.out.println("The title is:" + websiteTitle);
		assertEquals(websiteTitle, "Parking Calculator");

		// 3.Locate on ParkingTimeOption:<<Long-Term Garage Parking>>
		WebElement lotElem = driver.findElement(By.id("Lot"));
		lotElem.click();
		// WebElement selectMenu=lotElem.findElement(By.tagName("select"));
		/*
		 * Select lotOPtions=new Select(selectMenu);
		 * lotOPtions.selectByVisibleText("Long-Term Surface Parking");
		 */

		List<WebElement> lotOPtionList = lotElem.findElements(By.tagName("option"));
		for (WebElement lot : lotOPtionList) {
			if (lot.getText().contains("Long-Term Garage Parking")) {
				lot.click();
				Thread.sleep(3000);
			}
		}

		// 4.entry date and time
		WebElement entryTime = driver.findElement(By.id("EntryTime"));
		entryTime.clear();
		entryTime.sendKeys("11:35");

		List<WebElement> selectEntryAMPM = driver
				.findElements(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/font/input[2]"));
		WebElement selectEntryAM = selectEntryAMPM.get(0);
		selectEntryAM.click();

		WebElement entryDateElem = driver.findElement(By.id("EntryDate"));
		entryDateElem.clear();
		entryDateElem.sendKeys("09/25/2018");
		entryDateElem.sendKeys(Keys.ENTER);

		// 5. choose leaving date and time
		WebElement exitTime = driver.findElement(By.id("ExitTime"));
		exitTime.clear();
		exitTime.sendKeys("06:35");

		List<WebElement> selectLeavingAMPM = driver
				.findElements(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/font/input[2]"));
		WebElement selectLeavingAM = selectLeavingAMPM.get(0);
		selectLeavingAM.click();

		WebElement leavingDateElem = driver.findElement(By.id("ExitDate"));
		leavingDateElem.clear();
		leavingDateElem.sendKeys("11/20/2018");
		leavingDateElem.sendKeys(Keys.ENTER);

		// 6.calculate
		driver.findElement(By.cssSelector("body > form:nth-child(2) > input:nth-child(3)")).click();
		Thread.sleep(500);

		// 7.get value
		WebElement valueBox = driver.findElement(By.cssSelector(
				"body > form:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)"));
		String text1 = valueBox.getText();
		String text2 = driver.findElement(By.tagName("b")).getText();
		System.out.println("text1(the whole text) is:" + text1);
		System.out.println("text2(only price) is:" + text2);

		// 8.take a screenshot
		System.out.println("Step8: Once there is result,teke screenshot");
		String filePath = "c:/screenshot/parkcalc.png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(srcFile, new File(filePath));

	}
}
