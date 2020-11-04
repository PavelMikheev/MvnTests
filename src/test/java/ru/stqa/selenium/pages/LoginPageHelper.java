package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{
    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "user")
    WebElement loginField;

    @FindBy(id = "error")
    WebElement errorMessage;

    @FindBy(id = "password-error")
    WebElement passwordError;

     public LoginPageHelper(WebDriver driver) {
         super(driver);
        this.driver = driver;
    }



    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(passwordField, 15);
        waitUntilElementIsClickable(loginButton,15);
        waitUntilElementIsClickable(loginField,15);
    }

    public String getPasswordErrorMessage(){
        waitUntilElementIsVisible(passwordError, 15);
        return passwordError.getText();
    }


    public String getErrorMessage(){
        waitUntilElementIsVisible(errorMessage, 15);
        return errorMessage.getText();
    }

    public LoginPageHelper pressLoginButton() {
        log4j.info("LoginPageHelper: pressLoginButton()");
        log4j.info("Wait 5 sec ");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
       // waitUntilElementIsClickable(loginButton,15);
        log4j.info("Click on the login button ");
        loginButton.click();
        return this;
    }


    public void enterPassword(String password) {
        log4j.info("LoginPageHelper: not a password(password="+password+")");
        log4j.info("Enter password value to the password field ");
        waitUntilElementIsClickable(passwordField, 15);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void enterLogin(String login) {
        log4j.info("LoginPageHelper: not a login(login="+login+")");
            log4j.info("Enter login value to the login field ");
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void enterLoginAndPasswordPressLoginButton(String login, String password) {
         log4j.info("LoginPageHelper: not a login and password(login="+login+", password="+password+")");
        enterLogin(login);
        enterPassword(password);
        pressLoginButton();
    }
    public void waitUntilPageWithErrorVisible() {
        waitUntilElementIsVisible(errorMessage, 15);
    }
}

