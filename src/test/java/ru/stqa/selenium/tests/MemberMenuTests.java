package ru.stqa.selenium.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class MemberMenuTests extends TestBase{

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    HomePageHelper homePage;
    ActivityHelper activityMenu;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        qaHaifa7currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa7");
        activityMenu = new ActivityHelper(driver, "Activity");

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginAndPasswordPressLoginButton(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHaifa7currentBoard.waitUntilPageIsLoaded();
        activityMenu.openMemberMenuButton();
    }

        @Test
        public void CheckLinksInHelpMenu(){
        WebElement helpButton = driver.findElement(By.xpath("//span[contains(text(),'Help')]"));
        helpButton.click();
        boardsPage.waitUntilElementIsVisible(By.className("vsc7lMp7MQFsrC"), 10);
        driver.switchTo().frame(driver.findElement(By.className("vsc7lMp7MQFsrC")));

       Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Getting Started Guide')]")).getText(),
               "Getting Started Guide");
       Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Getting started with Trello video Demo')]")).getText(),
                    "Getting started with Trello video Demo");
       Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Troubleshooting browser issues with Trello')]")).getText(),
                    "Troubleshooting browser issues with Trello");
       Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'What are teams?')]")).getText(),
                    "What are teams?");
       Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'How to use Trello like a pro')]")).getText(),
                    "How to use Trello like a pro");
    }



}
