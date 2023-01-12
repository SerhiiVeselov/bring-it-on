package test;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.*;
import page.MainPage;
import java.time.Duration;
import static org.testng.Assert.*;

public class TextbinTest {

    public static WebDriver driver;
    public FluentWait<WebDriver> fluentWait;
    MainPage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        driver.manage().window().maximize();
    }

    @Test (description = "Creating a new paste")
    public void CreateNewPaste() {
        mainPage.openPage();
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getIAgreeBtn()));
        mainPage.closeCookiesPopUp();
        mainPage.enterTextContent();
        mainPage.enterPasteTitle();
        mainPage.selectSyntaxHighlighting();
        mainPage.selectPasteExpiration();
        mainPage.clickCreateNewPasteBtn();
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getPasteCreatedMsg()));
        fluentWait.until(ExpectedConditions.visibilityOf(mainPage.getCreatedPasteTextFirstRow()));
        assertEquals(mainPage.getPageTitle(), mainPage.TITLE_INPUT + " - TextBin");
        assertEquals(mainPage.verifySelectedSyntax(), "Bash");
        assertEquals(mainPage.verifyEnteredText(), mainPage.CONTENT_INPUT);
    }

    @AfterMethod(alwaysRun = true)
    public void driverQuit() {
        driver.quit();
        driver = null;
    }

}
