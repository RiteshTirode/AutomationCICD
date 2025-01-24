//Video:- https://drive.google.com/file/d/1t93YXEmpNfu0AN5COKRvWUnPs5I0KE-e/view?usp=sharing   (SelectSpinnerLoader.mp4 )(How to find webelement for loader/spinner which hides immediately)
// If element is not selectable then press Ctrl+Shift+P in slector hub, and type focus-and select Emulate a focused page
//All Assertions should accomdate in @TEST class, and actions should accomdate in Page Object class
//All re-usable components are accomdated in AbstractComponent
package CompleteAutoByPageObjectImpl;

import java.io.IOException;
import java.util.HashMap;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import riteshtirodeacademy.TestComponent.BaseTest;
import riteshtirodeacademy.pageObjects.CartPage;
import riteshtirodeacademy.pageObjects.CheckoutPage;
import riteshtirodeacademy.pageObjects.ConfirmationPage;
import riteshtirodeacademy.pageObjects.OrderPage;
import riteshtirodeacademy.pageObjects.ProductCatalogue;

public class StandAloneTest2HashMapImpl extends BaseTest{
	ProductCatalogue prodCat;
	String productName="qwerty";
    
	@Test(dataProvider = "getData",groups = {"PurchaseHashMap"} )
	public void StandAloneEcomApp(HashMap<String, String> input)  throws InterruptedException, IOException {


		prodCat=landingPage.LoginApplication(input.get("email"),input.get("password"));
		prodCat.getProductList();
		prodCat.getProductNames(input.get("productName"));
		prodCat.addProductToCart(input.get("productName"));
		CartPage cartPage=prodCat.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
        AssertJUnit.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
        checkoutPage.SelectCountry("India");

		ConfirmationPage conirmationPage=checkoutPage.goToConfirmationPage();
		String confirmMessage=conirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();


	}

    @Test(dependsOnMethods = {"StandAloneEcomApp"})
    public void OderHistory() {
    	prodCat=landingPage.LoginApplication("ritesht@teleworm.us", "Ritesh@123");
    	OrderPage orderPage=prodCat.goToOrdersPage();
    	AssertJUnit.assertTrue(orderPage.VerifyOrder(productName));
    }
    
    @DataProvider
    public Object[][] getData(){
    	HashMap<String, String> map=new HashMap<>();
    	map.put("email","anshika@gmail.com");
    	map.put("password","Iamking@000");
    	map.put("productName", "qwerty");
    	
    	HashMap<String, String> map1=new HashMap<>();
    	map1.put("email","ritesht@teleworm.us");
    	map1.put("password","Ritesh@123");
    	map1.put("productName", "Banarsi Saree");
    	
    	return new Object[][] {{map},{map1}};
    }
}
