package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.MainPage.HomePage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class SortingGoodsTest extends BaseClass {

    @Test(description = "Отсортировать товар по алфавиту", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void sortProductName(UserData userData) {

        HomePage homePage = new AuthorizationPage().fillInFields(userData.getUser(), userData.getPassword());

        homePage
                .removeFromCart();


        homePage.getProductSort().click(); //в  step
        homePage.getSortAZ().click();// <-

        homePage.sortElementsInAscendingOrderNames(homePage.getListOfElementNames());
//        homePage.homeElements.sortElementsInAscendingOrderNames(homePage.homeElements.getListOfElementsPrice());

//        homePage.homeElements.getAllProducts().get().getName();
    }

    public void sortProductPrice() {


    }
}
