package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Abstract {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//button[contains(@class,'btn btn-primary')])[3]")
    WebElement checkOutEle;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productTitles;

    public Boolean verifyProductDisplay(String productName){
        return productTitles.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckOut(){
        checkOutEle.click();
        return new CheckoutPage(driver);
    }

}
