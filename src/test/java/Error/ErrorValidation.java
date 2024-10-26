package Error;


import HarshalKapse.CartPage;
import HarshalKapse.ProductCatalouge;
import Kapse.BaseTest;
import Kapse.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() {

        ProductCatalouge productCatalouge = loginPage.setLogin("Etanpotter@gmail.com", "Harsh204");
        Assert.assertEquals("Incorrect email or password." ,loginPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException {

        String productName ="ZARA COAT 3";
        ProductCatalouge productCatalouge = loginPage.setLogin("Ethanpotter@gmail.com","Harsh2204");
        List<WebElement> products = productCatalouge.getproductList();
        productCatalouge.addProductToCart(productName);
        CartPage cartPage = productCatalouge.goTOCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}


