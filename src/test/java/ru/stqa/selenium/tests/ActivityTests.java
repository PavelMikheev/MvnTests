package ru.stqa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class ActivityTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;
    HomePageHelper homePage;
    ActivityHelper activityMenu;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        loginPage = PageFactory.initElements (driver, LoginPageHelper.class);
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
    }


    @Test
    public void isActivityPage() {
      activityMenu.openMemberMenuButton();
      activityMenu.clickActivityButton();
      activityMenu.activityBoardName();
      Assert.assertEquals(activityMenu.activityBoardName(), "Activity");
    }



    @Test
    public void checkLastRecordActivity() {
        qaHaifa7currentBoard.addNewList();
        qaHaifa7currentBoard.nameForNewListTest("name");
        qaHaifa7currentBoard.clickAddListButton();
        activityMenu.waitUntilListAdded();
        qaHaifa7currentBoard.waitUntilListsAreVisible();
        qaHaifa7currentBoard.clickListActionButton();
        qaHaifa7currentBoard.addAddCardButton();
        qaHaifa7currentBoard.enterNameInCard("card");
        activityMenu.openMemberMenuButton();
        activityMenu.clickActivityButton();
        activityMenu.waitUntilMenuActivityIsLoaded();
        Assert.assertEquals(activityMenu.lastRecordActivity(), "P\n" +
                "Pavel added card to test\n" +
                "just now - on board QA Haifa7");

    }




}


