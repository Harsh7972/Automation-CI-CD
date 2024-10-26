package Tests;


import HarshalKapse.*;
import Kapse.BaseTest;
import Kapse.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitTest extends BaseTest {
    String productName ="ZARA COAT 3";
    @Test(dataProvider="getData", groups = {"Purchase"},retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws InterruptedException {


        ProductCatalouge productCatalouge = loginPage.setLogin(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalouge.getproductList();
        productCatalouge.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalouge.goTOCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.Country();
        confirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
    }
    @Test (dependsOnMethods = {"submitOrder"})
    public void orderHistory(){
        ProductCatalouge productCatalouge = loginPage.setLogin("Ethanpotter@gmail.com","Harsh2204");
        Orderspage orderspage =productCatalouge.goToOrderPage();
        Assert.assertTrue(orderspage.verifyOrderHistory(productName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\datapackage\\PurchaseOrder.json");
        return new Object [][] {{data.get(0)},{data.get(1)} };
    }
    //        HashMap<String,String> map = new HashMap<>();
//        map.put("email","Ethanpotter@gmail.com");
//        map.put("password","Harsh2204");
//        map.put("product","ZARA COAT 3");
//
//
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","bittubits@gmail.com");
//        map1.put("password","Harsh2204");
//        map1.put("product","IPHONE 13 PRO");
}
