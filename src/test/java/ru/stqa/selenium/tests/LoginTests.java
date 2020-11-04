package ru.stqa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

import ru.stqa.selenium.util.DataProviders;


public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;


    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        log4j.info("LoginTest:@BeforeMethod initTests()");
        loginPage = PageFactory.initElements (driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginNegativeLoginEmpty() {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        loginPage.enterLoginAndPasswordPressLoginButton("", PASSWORD);
        log4j.endTestCase();
        Assert.assertEquals(loginPage.getErrorMessage(), "Missing email");


    }


    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderSecond")
    public void loginNegativeLoginEmpty2(String login, String password, String message) {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        log4j.info("Parameter login - "+login);
        log4j.info("Parameter password - "+password);
        log4j.info("Parameter message - "+message);

        loginPage.enterLoginAndPasswordPressLoginButton(login, password);
       Assert.assertEquals(loginPage.getErrorMessage(), message);
        log4j.endTestCase2();


    }

    @Test(groups = {"smoke", "regression"}, dataProviderClass = DataProviders.class,dataProvider = "dataProviderThird")
    public void loginNegativeLoginEmpty3(String login, String password) {
        loginPage.enterLoginAndPasswordPressLoginButton(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this email");
    }




    @Test(groups = {"smoke"})
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

