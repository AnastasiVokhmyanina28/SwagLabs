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
import java.util.logging.Logger;

public class DeletingAnItemFromTheCartTest extends BaseClass implements ToolBarElements {
    private static final Logger log = Logger.getLogger(DeletingAnItemFromTheCartTest.class.getName());


    @Test(description = "Удаление товара из корзины( Очистить корзину полностью)", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void deletingAnItem(UserData data) {

        log.info(data.getUser());

        AuthorizationPage authorizationPage = openLoginPage();
        HomePage homePage = authorizationPage.login(data);

        homePage
                .removeFromCart()
                .doAddMultipleItemsToCart(3)
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
