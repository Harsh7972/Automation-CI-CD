package HarshalKapse;

import Abstract.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderspage extends Abstract {
    WebDriver driver;
    public Orderspage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tbody /tr /td [2]")
    List<WebElement> orderName;

    public List<WebElement> getorderlist(){
        return  orderName;
    }
    public Boolean verifyOrderHistory(String productName){
        return getorderlist().stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
    }
}
