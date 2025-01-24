package riteshtirodeacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import riteshtirodeacademy.pageObjects.CartPage;

public class AbstractComponent {

	//WebDriverWait wbwait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//wbwait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
	WebDriver driver;
	@FindBy(css="[routerlink*='cart']")
	WebElement goCart;

	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) throws InterruptedException {
		//Thread.sleep(1000);

		  WebDriverWait wbwait=new WebDriverWait(driver, Duration.ofSeconds(10));
		  wbwait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForElementToDisAppear(WebElement webEle) {
		WebDriverWait wbwait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wbwait.until(ExpectedConditions.invisibilityOf(webEle));
	}

	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	public CartPage goToCartPage() {
		goCart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}

}
