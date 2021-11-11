import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;



public class Authorization
{

    private WebDriver driver;
    private Alert alert;

    @BeforeTest
    public void createDriver(){
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @DataProvider (name = "dataProvider")
    public static Object[][] dbData()
    {
        return new Object[][]{
                {"//","//"},
                {"@@",""},
                {"2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222","123"},
                {"fominaelena", "1P73BP4Z"},
                {"fominaelena", ""},
                {"", "1P73BP4Z"},
                {"fominaelena", "123"},
                {"123", "1P73BP4Z"},
                {"", ""}
        };
    }




    @Test(dataProvider = "dataProvider")
    public void incorrectLoginPassword(String login, String password) throws InterruptedException {
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='user']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password, Keys.ENTER);
        try{
            alert = driver.switchTo().alert();
            String error = alert.getText();
            alert.accept();
            driver.close();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Assert.fail(error);
        }
        catch (NoAlertPresentException ex)
        {
            driver.close();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }


    @AfterTest
    public void quitDriver()
    {
        if(driver!=null)
        {
            driver.quit();
        }
    }

}
