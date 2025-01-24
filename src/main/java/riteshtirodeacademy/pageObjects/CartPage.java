package riteshtirodeacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import riteshtirodeacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	 WebDriver driver;

		public CartPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(css=".cartSection h3")
		List<WebElement> cartpageElement;

		public boolean VerifyProductDisplay(String productName) {
			boolean match=cartpageElement.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
			return match;

		}

		@FindBy(css=".totalRow button")
		WebElement checkoutButton;
		// driver.findElement(By.cssSelector(".totalRow button")).click();
		public CheckoutPage goToCheckoutPage() {

			checkoutButton.click();
			CheckoutPage checkoutPage=new CheckoutPage(driver);
			return checkoutPage;
		}

}
