package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ActivityHelper extends PageBase {
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type'][1]")
    WebElement lastRecordInActivity;
    @FindBy(className = "_24AWINHReYjNBf")
    WebElement menuButton;
    @FindBy(xpath = "//span[contains(text(),'Activity')]/..")
    List<WebElement> activityButton;
    @FindBy(xpath = "//li[@class='tabbed-pane-nav-item'][2]")
    WebElement boardActivity;

String tabName;

    public ActivityHelper(WebDriver driver, String tabName) {

        super(driver);
        this.tabName = tabName;
        PageFactory.initElements(driver, this);
    }

    public void clickActivityButton()  {

      waitUntilElementIsClickable(activityButton.get(1), 15);
      activityButton.get(1).click();

    }

    public String activityBoardName(){
        waitUntilElementIsVisible(boardActivity, 15);
        return boardActivity.getText();
    }

    public void openMemberMenuButton() {
        waitUntilElementIsClickable(menuButton, 15);
        menuButton.click();

    }

    public void waitUntilListAdded() {
        waitUntilElementIsPresent(By.xpath("//div[@class='list-header-target js-editing-target']"), 15);
    }

    public String lastRecordActivity() {
        return lastRecordInActivity.getText();
    }

    public void waitUntilMenuActivityIsLoaded() {
        waitUntilElementIsVisible(By.className("tabbed-pane-header"),15);
    }
}
