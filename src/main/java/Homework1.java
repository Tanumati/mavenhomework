import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Homework1 {
    protected static WebDriver driver;
    String expectedsentmsg= "Your message has been sent.";

    public String genarateEmail(String starvalue)
    {
        String email = starvalue.concat(new Date().toString());
        return email;
    }
    public static String randomeDate()
    {
        DateFormat formate = new SimpleDateFormat("ddMMyyHHMMss");
        return formate.format(new Date());
    }
    @BeforeMethod
     public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Browserdriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for web object
        driver.manage().timeouts().implicitlyWait(59, TimeUnit.SECONDS);
        // open the website
        driver.get("https://demo.nopcommerce.com");
    }
    @AfterMethod
    public void exit()
    {
        //To close the browser
        driver.quit();
    }
    @Test(priority = 0)
    public void UserShouldBeAbleToRegistration(){
       //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        //enter firstname
        driver.findElement(By.id("FirstName")).sendKeys("Tanu");

        //enter lastname
        driver.findElement(By.xpath("//input[@name ='LastName']")).sendKeys("Patel");

        //enter email
        driver.findElement(By.name("Email")).sendKeys(("tanoopatel"+randomeDate()+"@yahoo.co.in"));
        // Enter password
        driver.findElement(By.id("Password")).sendKeys("Sanvi$");
        //Enter Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("Sanvi$");
        // click on register
        driver.findElement(By.name("register-button")).click();
        String expected = "Your registration completed";
        String actualmsg= driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(actualmsg,expected);
    }
    @Test(priority = 1)
    public void UserShouldBeAbleSendAProductEmail(){
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //enter firstname
        driver.findElement(By.id("FirstName")).sendKeys("Tanu");
        //enter lastname
        driver.findElement(By.xpath("//input[@name ='LastName']")).sendKeys("Patel");
        //enter email
        driver.findElement(By.name("Email")).sendKeys(("tanoopatel"+randomeDate()+"@yahoo.co.in"));
        // Enter password
        driver.findElement(By.id("Password")).sendKeys("Sanvi$");
        //Enter Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("Sanvi$");
        // click on register
        driver.findElement(By.name("register-button")).click();
        //navigate homepage
        driver.findElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]")).click();
        //click macbook
        driver.findElement(By.xpath("//img[@alt=\"Picture of Apple MacBook Pro 13-inch\"]")).click();
        // send to friend
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
        //enter friend email
        driver.findElement(By.id("FriendEmail")).sendKeys("tanoost19@gamil.com");
        // click email
        driver.findElement(By.xpath("//input[@placeholder=\"Enter your email address.\"]")).click();
        // send message
        driver.findElement(By.xpath("//textarea[@class=\"your-email\"]")).sendKeys("check the product");
        // click send
        driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
        String actualsentmsg= driver.findElement(By.xpath("//*[@class='result'and contains(text(),'Your message has been sent.')]")).getText();
        Assert.assertEquals(actualsentmsg,expectedsentmsg);
    }
    @Test(priority = 2)
    public void UserShouldBeAbleToNavigateCameraAndPhoto()
    {
        //navigate to eloctonics
        driver.findElement(By.xpath("//h2/a[@title=\"Show products in category Electronics\"]")).click();
        //navigate to photo and camera
        driver.findElement(By.xpath("//h2/a[@title=\"Show products in category Camera & photo\"]")).click();
        String expectedtitle= "Camera & photo";
        String actualtitle= driver.findElement(By.xpath("//div[@class=\"page-title\"]")).getText();
        Assert.assertEquals(actualtitle,expectedtitle);
    }
    @Test(priority = 3)
    public void UserShouldBeAbleToFilterJwellery()
    {
        //navigate to jewelry
        driver.findElement(By.linkText("Jewelry")).click();
        //Select range
        driver.findElement(By.xpath("//a[@href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]")).click();
        //save expectedtitle by locators
        String expectedtitle = "$700.00 - $3,000.00";
        //save actualtitle by locators
        String actualtitle = driver.findElement(By.xpath("//span[@class= 'item']")).getText();
        Assert.assertEquals(actualtitle,expectedtitle);
        //find minimum price
        String minimumrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$700.00']")).getText();
        System.out.println(minimumrange);
        // find Actual price
        String actualrange = driver.findElement(By.xpath("//span[@class=\"price actual-price\" and text() ='$2,100.00']")).getText();
        System.out.println(actualrange);
        //find maximum price
        String maximumrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$3,000.00']")).getText();
        System.out.println(maximumrange);
        // convert string minrange to float
        float minrange = Float.parseFloat(minimumrange.substring(1));
        System.out.println(minrange);
        //convert String actualrange
        float arange = Float.parseFloat(actualrange.replace(",","").substring(1));
        System.out.println(arange);
        //Convert String maxminrange
        float maxrange = Float.parseFloat(maximumrange.replace(",","").substring(1));
        System.out.println(maxrange );
        //checking actual price between minimum and maximum range
        Assert.assertTrue(arange>=minrange && arange<=maxrange);


    }
    @Test(priority = 4)
    public void UserShouldBeAbletoTwoProductInShoppingCart()
    {
        //click on books category
        driver.findElement(By.linkText("Books")).click();
     //click on  book name  'First Prize Pies'
        driver.findElement(By.linkText("First Prize Pies")).click();
        //Click on add to cart
        driver.findElement(By.xpath("//input[@id=\"add-to-cart-button-38\"]")).click();
        // webdriver wait to add product into basket
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        //click on book name 'Fahrenheit 451 by Ray Bradbury'
        driver.findElement(By.linkText("Fahrenheit 451 by Ray Bradbury")).click();
        //click on add to cart
        driver.findElement(By.xpath("//input[@id=\"add-to-cart-button-37\"]")).click();
        // webdriver wait to add product into basket
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        //click on shopping cart
        driver.findElement(By.className("ico-cart")).click();
        //actual  quantity locators
         String actual = driver.findElement(By.className("cart-qty")).getText();
         //actual quantity convert into string
        String actualquantity = actual.replace("("," ").replace(")"," ").trim();
        String expectedquantity ="2";
        //compairing with actual quantity by Expected
        Assert.assertEquals(actualquantity,expectedquantity);


    }

}
