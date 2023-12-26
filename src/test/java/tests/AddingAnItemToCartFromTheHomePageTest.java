package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.CardsGoodsInTheCartPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        homePage.removeFromCart();
        homePage.addItemToCart();

        List<ProductPojo> pojoList = homePage.getProductsInCart();

        openContainer();
        CardsGoodsInTheCartPage cartPage = new CardsGoodsInTheCartPage();
        cartPage.cartOpeningCheck();
        cartPage.compareProducts(pojoList);

    }
}
