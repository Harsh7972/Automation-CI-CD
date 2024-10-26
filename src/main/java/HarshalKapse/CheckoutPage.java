package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Abstract {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//input[@placeholder='Select Country']" )
    WebElement selectCountry;

    @FindBy(xpath ="(//button[contains(@class,'ta-item list-group-item ng-star-inserted')])[2]" )
    WebElement selectCountryName;

    @FindBy(css =".btnn.action__submit.ng-star-inserted")
    WebElement placeOrder;

    By results = By.cssSelector(".ta-results");


    public void Country(){
        Actions action = new Actions(driver);
        action.sendKeys(selectCountry,"India").build().perform();
        waitForElementToAppear(results);
        selectCountryName.click();

    }
    public confirmationPage submitOrder(){

        placeOrder.click();
        return new confirmationPage(driver);
    }
}
