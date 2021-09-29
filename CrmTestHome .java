package lesson3;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class CrmTestHome {
    private static WebDriver driver;
    private static final String CRM_URL = "https://crm.geekbrains.space";


    public static void main(String[] args) throws InterruptedException {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

            driver.get(CRM_URL);

            login();
            newCost();
            newContact();
            driver.quit();
            }

    public static void login() {
            WebElement element = driver.findElement(By.id("prependedInput"));
            element.sendKeys("Applanatest1");
            driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
            driver.findElement(By.id("_submit")).click();
        }
        /** новый хозяйственный договор **/

    public static void newCost() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        List<WebElement> navMenu = driver.findElements(By.xpath("//div[@id='main-menu']/ul/li/a"));
        WebElement navMenuElement = navMenu.stream().filter(e -> e.getText().equals("Расходы")).findFirst().get();
        Actions actions = new Actions(driver);
        actions.moveToElement(navMenuElement).perform();
        driver.findElement(By.xpath("//a/span[text()='Хоз. договоры']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Новый хозяйственный договор']")));
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Новый хозяйственный договор']"))));
        driver.findElement(By.xpath("//a[text()='Новый хозяйственный договор']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'crm_domcontract_name-uid')]")));
        driver.findElement(By.xpath("//input[contains(@id,'crm_domcontract_name-uid')]")).sendKeys("Договор #1");

        driver.findElement(By.xpath("//span[@class='select2-arrow']")).click();
        driver.findElement(By.xpath("//li/div[(text()='ИП Чебурашка Иван Иванович')]")).click();
        driver.findElement(By.xpath("//select[contains(@id,'crm_domcontract_expenditure-uid')]")).click();
        driver.findElement(By.xpath("//*[text()='04101 - Программное обеспечение']")).click();

        driver.findElement(By.xpath("//div/button[contains(@class,'btn-success action-button')]")).click();
        Thread.sleep(6000);
    }
        /** Новое контактное лицо **/

    public static void newContact() throws InterruptedException {
        WebDriverWait webDriverWait1 = new WebDriverWait(driver, 10);

        Actions actions1 = new Actions(driver);
        WebElement navMenu1 = driver.findElement(By.xpath("//a/span[text()='Контрагенты']"));
        actions1.moveToElement(navMenu1).perform();

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/span[text()='Контактные лица']")));
        driver.findElement(By.xpath("//a/span[text()='Контактные лица']")).click();

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[contains(.,'Создать контактное лицо')]")));
        driver.findElement(By.xpath("//div/a[contains(.,'Создать контактное лицо')]")).click();

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[contains(@id,'crm_contact_lastName-uid')]")));
        driver.findElement(By.xpath("//div/input[contains(@id,'crm_contact_lastName-uid')]")).sendKeys("Ch");

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[contains(@id,'crm_contact_firstName-uid')]")));
        driver.findElement(By.xpath("//div/input[contains(@id,'crm_contact_firstName-uid')]")).sendKeys("Irin");

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='select2-arrow']")));
        driver.findElement(By.xpath("//span[@class='select2-arrow']")).click();

        webDriverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/div[text()='123test']")));
        driver.findElement(By.xpath("//li/div[text()='123test']")).click();

        driver.findElement(By.xpath("//div/input[contains(@id,'crm_contact_jobTitle-uid')]")).sendKeys("QA engineer");

        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        Thread.sleep(4000);

    }
}
