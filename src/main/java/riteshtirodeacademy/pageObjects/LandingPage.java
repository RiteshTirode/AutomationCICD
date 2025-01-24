package riteshtirodeacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import riteshtirodeacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	//WebElement userEmail=driver.findElement(By.id("userEmail")).sendKeys("ritesht@teleworm.us");
	//WebElement userPassword=driver.findElement(By.id("userPassword")).sendKeys("Ritesh@123");
	//WebElement loginElement=driver.findElement(By.id("login")).click();

	@FindBy(id="userEmail")
	WebElement userEmail;

    @FindBy(id="userPassword")
	WebElement userPassword;

    @FindBy(id="login")
    WebElement loginElement;

    public ProductCatalogue LoginApplication(String email, String password) {
    	userEmail.sendKeys(email);
    	userPassword.sendKeys(password);
    	loginElement.click();
    	ProductCatalogue prodCat=new ProductCatalogue(driver);
    	return prodCat;
    }

	//div[@class='ng-tns-c4-4 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public String getErrorMessage()
    {
   	return errorMessage.getText();
    }

    public void goToUrl() {
    	driver.get("https://rahulshettyacademy.com/client");
    }

}
