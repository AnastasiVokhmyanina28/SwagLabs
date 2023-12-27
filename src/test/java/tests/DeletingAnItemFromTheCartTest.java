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

public class DeletingAnItemFromTheCartTest extends BaseClass implements ToolBarElements {

    @Test(description = "Удаление товара из корзины", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void deletingAnItem(UserData data) {

        AuthorizationPage authorizationPage = openLoginPage();
        HomePage homePage = authorizationPage.login(data);

        homePage
                .removeFromCart()
                .addItemToCart()
                .checkingTheAdditionOfGoods();

        List<ProductPojo> list = homePage.getProductsInCart();

        CardsGoodsInTheCartPage shoppingContainerPage = openCart();

        shoppingContainerPage
                .compareProducts(list);

        shoppingContainerPage
                .deleteProduct()
                .doClickButtonContinueShopping()
                .checkTheDeleteButton();
    }
}
