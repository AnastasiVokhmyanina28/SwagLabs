package tests;

import Data.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class AutorizationTest extends BaseClass {

    private AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test(description = "Авторизация существующего пользователя", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void authorization(UserData data) {
        authorizationPage.elements.fillInFields(data.getUser(), data.getPassword());
    }

}
