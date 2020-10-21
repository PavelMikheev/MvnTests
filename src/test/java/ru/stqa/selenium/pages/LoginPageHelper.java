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

    public void pressLoginButton() {
        //press login button
        waitUntilElementIsClickable(loginButton,15);
       // WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
    }


    public void enterPassword(String password) {
        waitUntilElementIsClickable(passwordField, 15);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void enterLogin(String login) {
        //WebElement loginField = driver.findElement(By.id("user"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void enterLoginAndPasswordPressLoginButton(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        pressLoginButton();
    }
    public void waitUntilPageWithErrorVisible() {
        waitUntilElementIsVisible(errorMessage, 15);
    }
}

