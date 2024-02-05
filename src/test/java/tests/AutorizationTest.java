package tests;

import Data.User.UserData;
import PageObject.AuthorizationPage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class AutorizationTest extends BaseClass {
    private static final Logger log = Logger.getLogger(AutorizationTest.class.getName());


    @Test(description = "Авторизация существующего пользователя", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void authorization(UserData data) {

        log.info(data.getUser());

        AuthorizationPage authorizationPage = openLoginPage();
        authorizationPage.login(data);
    }

}
