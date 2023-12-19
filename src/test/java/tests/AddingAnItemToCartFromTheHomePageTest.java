package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.MainPage.HomePage;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        authorizationPage.fillInFields(data.getUser(), data.getPassword());
        homePage.removeFromCart();
        homePage.addItemToCart();
        price = homePage.getPrice().getText();
        steps.cartOpeningCheck(price);
    }
}
