package ru.stqa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import org.example.util.DataProviders;



public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;


    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements (driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();

    }


    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderSecond")
    public void loginNegativeLoginEmpty(String login, String password, String message) {
        loginPage.enterLoginAndPasswordPressLoginButton(login, password);
       Assert.assertEquals(loginPage.getErrorMessage(), message);


    }

    @Test
    public void loginPasswordIncorrect() {
        loginPage.enterLoginAndPasswordPressLoginButton("user77","password1");
        loginPage.waitUntilPageWithErrorVisible();
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this username",
                "The error message is not 'There isn't an account for this username'");
    }


    @Test
    public void PasswordIncorrect() {

        loginPage.enterLoginAndPasswordPressLoginButton(LOGIN,"password1");
        loginPage.waitUntilPageWithErrorVisible();
        Assert.assertTrue(loginPage.getPasswordErrorMessage().contains("Incorrect email address and"),
                "The error message is not contains the text 'Incorrect email address and'");

    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void LoginPositiveTest(String login, String password) {

        loginPage.enterLoginAndPasswordPressLoginButton(login, password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.getBoardIconName().equals("Boards"), "The text button is not BOARDS");
    }

}

