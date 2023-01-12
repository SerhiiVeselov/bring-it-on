package page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Getter
public class MainPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://textbin.net/";
    public static String TITLE_INPUT = "how to gain dominance among developers";
    public static String CONTENT_INPUT = "git config --global user.name  \"New Sheriff in Town\"\n" +
                    "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                    "git push origin master --force";

    @FindBy(xpath = "//span[text()='New Paste ']")
    private WebElement newPasteTitle;

    @FindBy(xpath = "//button[contains(text(), 'I agree')]")
    private WebElement iAgreeBtn;

    @FindBy(xpath = "//textarea[@name='content']")
    private WebElement contentTextBox;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement pasteTitleField;

    @FindBy(xpath = "//select[@name='expire']")
    private WebElement pasteExpirationDropDown;

    @FindBy(xpath = "//select[@name='syntax']")
    private WebElement syntaxHighlighting;

    @FindBy(xpath = "//button[contains(text(), 'Create New Paste')]")
    private WebElement createNewPasteBtn;

    @FindBy(xpath = "//div[contains(button/following-sibling::text(), 'Paste successfully created')]")
    private WebElement pasteCreatedMsg;

    @FindBy(xpath = "//h5[@class='mt-0']")
    private WebElement createdPasteTitle;

    @FindBy(xpath = "//div[1]/div[@class='ace_line']")
    private WebElement createdPasteTextFirstRow;

    @FindBy(xpath = "//div[2]/div[@class='ace_line']")
    private WebElement createdPasteTextSecondRow;

    @FindBy(xpath = "//div[3]/div[@class='ace_line']")
    private WebElement createdPasteTextThirdRow;

    @FindBy(xpath = "//span[@class='badge badge-light']")
    private WebElement bashSyntaxLabel;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void openPage() {
        driver.get("https://textbin.net/");
    }

    public void closeCookiesPopUp() {
        iAgreeBtn.click();
    }

    public void enterTextContent() {
        contentTextBox.click();
        contentTextBox.sendKeys(CONTENT_INPUT);
    }

    public void enterPasteTitle() {
        pasteTitleField.click();
        pasteTitleField.sendKeys(TITLE_INPUT);
    }

    public void selectSyntaxHighlighting() {
        Select syntaxHighlightingDrpDwn = new Select(syntaxHighlighting);
        syntaxHighlightingDrpDwn.selectByValue("bash");
    }

    public void selectPasteExpiration() {
        Select expirationDrpDwn = new Select(pasteExpirationDropDown);
        expirationDrpDwn.selectByValue("10M");
    }

    public void clickCreateNewPasteBtn() {
        createNewPasteBtn.click();
    }

    public String verifySelectedSyntax() {
        return bashSyntaxLabel.getText();
    }

    public String verifyEnteredText() {
        String text = createdPasteTextFirstRow.getText() + "\n" + createdPasteTextSecondRow.getText()
                + "\n" + createdPasteTextThirdRow.getText();
        return text;
    }
}
