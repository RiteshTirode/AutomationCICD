//Video:- https://drive.google.com/file/d/1t93YXEmpNfu0AN5COKRvWUnPs5I0KE-e/view?usp=sharing   (SelectSpinnerLoader.mp4 )(How to find webelement for loader/spinner which hides immediately)
// If element is not selectable then press Ctrl+Shift+P in slector hub, and type focus-and select Emulate a focused page
package CompleteAutoByPageObjectImpl;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import riteshtirodeacademy.TestComponent.BaseTest;
import riteshtirodeacademy.pageObjects.CartPage;
import riteshtirodeacademy.pageObjects.ProductCatalogue;

public class ErrorValidation extends BaseTest{

    @Test(groups={"ErrorHandling"},retryAnalyzer = riteshtirodeacademy.TestComponent.Retry.class )
	public void LoginValidationError()  throws InterruptedException, IOException {


		ProductCatalogue prodCat=landingPage.LoginApplication("ritesht@teleworm.u", "Ritesh@123");
    	AssertJUnit.assertEquals("Incorrec email or password.", landingPage.getErrorMessage());

	}

    @Test
    public void ProductErrorValidation() throws InterruptedException {
    	String productName="qwerty";
		ProductCatalogue prodCat=landingPage.LoginApplication("ritesht@teleworm.us", "Ritesh@123");
		prodCat.getProductList();
		prodCat.getProductNames(productName);
		prodCat.addProductToCart(productName);
		CartPage cartPage=prodCat.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay("qwerty245");
        AssertJUnit.assertTrue(match);
    }

}
