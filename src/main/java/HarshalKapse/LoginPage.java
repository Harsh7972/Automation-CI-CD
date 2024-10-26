package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Abstract {

    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
   // driver.findElement().sendKeys("Ethanpotter@gmail.com");

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalouge setLogin(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        return new ProductCatalouge(driver);
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }

}
