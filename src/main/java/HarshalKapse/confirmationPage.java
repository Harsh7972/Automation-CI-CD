package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends Abstract {
    WebDriver driver;
    public confirmationPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
//    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
//
//        driver.close();
    @FindBy(css = ".hero-primary")
    WebElement confirmMessage;

    public String getConfirmationMessage(){
        return confirmMessage.getText();
    }

}
