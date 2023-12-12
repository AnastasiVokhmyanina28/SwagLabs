package tests;

import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class AutorizationTest extends BaseClass {

    private AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test(description = "Авторизация сществующего пользователя", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void authorization(String name, String password) {
        authorizationPage.elements.fillInFields(name, password);
    }

}
