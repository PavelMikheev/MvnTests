package ru.stqa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;
//import org.example.util.DataProviders;


public class CurrentBoardTests extends TestBase {
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

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
       loginPage.enterLoginAndPasswordPressLoginButton(LOGIN, PASSWORD);
       boardsPage.waitUntilPageIsLoaded();
       boardsPage.openCurrentBoardPage("QA Haifa7");
       qaHaifa7currentBoard.waitUntilPageIsLoaded();
    }

    @Test (groups = {"smoke"})
    public void  isCorrectCurrentBoard(){
        Assert.assertEquals(qaHaifa7currentBoard.getCurrentBoardHeader(),
                "QA Haifa7", "The header of the script is not 'QA Haifa7'");
    }

    @Test (groups = {"regression"})
    public void  isCorrectCurrentBoard2(){
        Assert.assertTrue(qaHaifa7currentBoard.isCorrectCurrentBoard(),
                 "The header of the script is not 'QA Haifa7'");
    }

    @Test(groups = {"smoke", "regressopn"}, dataProviderClass = DataProviders.class,dataProvider = "dataProviderCreateList")
    public void NewListPositiveTest(String name) {

        int qtyListsBeforeAdding = qaHaifa7currentBoard.getQtyOfLists();
        qaHaifa7currentBoard.addNewListInBoard(name);

        int qtyListAfterAdding = qaHaifa7currentBoard.getQtyOfLists();
        Assert.assertEquals(qtyListAfterAdding, qtyListsBeforeAdding+1);
    }




    @Test
    public void DeletingListPositiveTest(String name) {

        if (qaHaifa7currentBoard.getListQtyBeforeDeleting() == 0){
            //if there are no lists add one more list
            qaHaifa7currentBoard.addNewListInBoard(name);
            qaHaifa7currentBoard.finishAddingNewList();

        }

        int listQtyBeforeDeleting = qaHaifa7currentBoard.getListQtyBeforeDeleting();
        qaHaifa7currentBoard.waitUntilListsAreVisible();
        qaHaifa7currentBoard.clickListActionButton();
        qaHaifa7currentBoard.clickArchiveListButton();
        int listQtyAfterDeleting = driver.findElements(By.xpath("//div[@class='list-header-target js-editing-target']")).size();
        Assert.assertEquals(listQtyAfterDeleting, listQtyBeforeDeleting-1);
    }




    @Test
    public void addingListAndCardToList(String name) {
        qaHaifa7currentBoard.addNewListInBoard(name);
        activityMenu.waitUntilListAdded();
        qaHaifa7currentBoard.waitUntilListsAreVisible();
        qaHaifa7currentBoard.clickListActionButton();
        qaHaifa7currentBoard.addAddCardButton();
        qaHaifa7currentBoard.enterNameInCard("card");


    }




    @Test
    public void UserNameOfAccountInProfileAndVisibilityIsSame(){
        qaHaifa7currentBoard.getNameFromUserNameIcon();
        qaHaifa7currentBoard.openMemberMenu();
        qaHaifa7currentBoard.clickProfileAndVisibilityButton();
        //check if userNameIcon contains userNameInProfile
        Assert.assertTrue(qaHaifa7currentBoard.getNameFromUserNameIcon().contains(qaHaifa7currentBoard.userNameInProfile()));

    }




}

