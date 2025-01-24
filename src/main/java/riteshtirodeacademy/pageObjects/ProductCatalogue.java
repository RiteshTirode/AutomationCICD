package riteshtirodeacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import riteshtirodeacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebDriverWait wbwait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//wbwait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));

	By findBy=By.className("mb-3");

	//List<WebElement> products=driver.findElements(By.className("mb-3"));
	@FindBy(className="mb-3")
	List<WebElement> products;
	public List<WebElement> getProductList() throws InterruptedException {
		waitForElementToAppear(findBy);
		return products;
	}

	//WebElement prod=products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("qwerty")).findFirst().orElse(null);
	public WebElement getProductNames(String productName) throws InterruptedException {
		WebElement prod=getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText()
				        .equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}

	//WebElement addToCart=prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	//@FindBy(css=".card-body button:last-of-type")
	By addToCart=By.cssSelector(".card-body button:last-of-type");

	By toastMessage=By.id("toast-container");

	@FindBy(className = "ng-animating")
	WebElement spinner;

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod=getProductNames(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
	}

	@FindBy(css="[routerlink*='myorders']")
	WebElement cartLink;

	public OrderPage goToOrdersPage() {
		cartLink.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}

}
