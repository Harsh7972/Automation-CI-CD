package Harshalkapse.stepDefinitions;

import HarshalKapse.*;
import Kapse.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionimpli extends BaseTest {

    public LoginPage loginPage;
    public ProductCatalouge productCatalouge;
    public CheckoutPage checkoutPage;
    public confirmationPage confirmationPage;
    public CartPage cartPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        loginPage = launchApplication();
    }

    @Given ("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username , String password){
        productCatalouge = loginPage.setLogin(username,password);
    }

    @When("^I add product (.+) to Cart$")
    public void adding_products_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalouge.getproductList();
        productCatalouge.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_orders(String productName){
        cartPage = productCatalouge.goTOCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckOut();
        checkoutPage.Country();
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_Confirmation(String string) {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed.")
    public void message_Is_Displayed(String string) {
        Assert.assertEquals(string ,loginPage.getErrorMessage());
        driver.close();
    }

}
