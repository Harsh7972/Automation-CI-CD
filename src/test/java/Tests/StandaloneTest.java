package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) {
        String productName ="ZARA COAT 3";

        //Creating object
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicit wait on global level
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // login page
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("Ethanpotter@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Harsh2204");
        driver.findElement(By.id("login")).click();

        //productCataglouge
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(s->s.findElement(By.xpath(".//b"))
                .getText().equals("ZARA COAT 3")).findFirst().orElse(null);

        assert prod != null;
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // cartPage
        driver.findElement(By.xpath("(//button[contains(@class,'btn btn-custom')])[3]")).click();

        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match, "Product " + productName + " is not found in the cart.");

        driver.findElement(By.xpath("(//button[contains(@class,'btn btn-primary')])[3]")).click();

        //Checkout Page
        Actions action = new Actions(driver);
        //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
        WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
        action.sendKeys(selectCountry , "India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item list-group-item ng-star-inserted')])[2]")).click();

        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

        //confirmation page
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.close();



    }
}
