package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {

    private String price;

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        homePage.removeFromCart();
        homePage.addItemToCart();

        price = homePage.getPrice().getText();
        Steps steps = new Steps();
        steps.cartOpeningCheck(price);
    }
}
