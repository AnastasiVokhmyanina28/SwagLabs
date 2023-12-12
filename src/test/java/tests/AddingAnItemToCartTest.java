package tests;

import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

public class AddingAnItemToCartTest extends BaseClass {
    private AuthorizationPage page = new AuthorizationPage();

    @Test(description = "Добавление товара в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void addingAnItemToCartTest(String user, String pass) {
        page.elements.fillInFields(user, pass);
    }
}
