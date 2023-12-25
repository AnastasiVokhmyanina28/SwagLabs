package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class AutorizationTest extends BaseClass {

    @Test(description = "Авторизация существующего пользователя", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void authorization(UserData data) {
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());
    }

}
