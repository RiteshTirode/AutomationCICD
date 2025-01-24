package riteshtirodeacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import riteshtirodeacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.cssSelector("input[placeholder='Select Country']")). sendKeys("India");
	 //wbwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item:nth-child(3)")));
	// driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryOption;

	By visibilityByCountry=By.cssSelector(".ta-item:nth-child(3)");

	@FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickDropOfCountry;


	public void SelectCountry(String country) throws InterruptedException{
		countryOption.sendKeys(country);
		waitForElementToAppear(visibilityByCountry);
		clickDropOfCountry.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000000)");
		Thread.sleep(100000);
	}

	//driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css =".action__submit")
	WebElement goToConfirmationPage;


	public ConfirmationPage goToConfirmationPage() {
		goToConfirmationPage.click();
	    ConfirmationPage confirmationPage=new ConfirmationPage(driver);
	    return confirmationPage;
	}

}
