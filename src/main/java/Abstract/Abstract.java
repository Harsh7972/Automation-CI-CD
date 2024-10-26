package Abstract;

import HarshalKapse.CartPage;
import HarshalKapse.Orderspage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Abstract {

WebDriver driver;
    public Abstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//button[contains(@class,'btn btn-custom')])[3]")
    WebElement cartHeader;


    @FindBy(xpath = "(//button[contains(@class,'btn btn-custom')])[2]")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBy){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        }

    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

        public CartPage goTOCartPage(){
        cartHeader.click();
        return new CartPage(driver);
        }

    public Orderspage goToOrderPage(){
        orderHeader.click();
        return new Orderspage(driver);
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
    }


}

