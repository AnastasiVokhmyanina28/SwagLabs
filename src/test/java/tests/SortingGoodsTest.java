package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import PageObject.Page.HomePage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class SortingGoodsTest extends BaseClass {
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();

    @Test(description = "Отсортировать товар по алфавиту", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void sortProduct(UserData userData){
        authorizationPage.elements.fillInFields(userData.getUser(), userData.getPassword());
        //todo
    }
}
