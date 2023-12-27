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
        HomePage homePage = new AuthorizationPage().login(data.getUser(), data.getPassword())
                .removeFromCart()
                .addItemToCart()
                .checkingTheAdditionOfGoods();
        List<ProductPojo> list = homePage.getProductsInCart();

        CardsGoodsInTheCartPage shoppingContainerPage = openContainer();
        shoppingContainerPage.compareProducts(list);


        shoppingContainerPage.deleteProduct();

        shoppingContainerPage
                .doClickButtonContinueShopping()
                .checkTheDeleteButton();
    }
}
