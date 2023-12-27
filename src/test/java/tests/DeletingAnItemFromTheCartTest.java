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
        new AuthorizationPage().login(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        homePage.removeFromCart();
        homePage.addItemToCart();
        List<ProductPojo> list = homePage.getProductsInCart();
        homePage.checkingTheAdditionOfGoods();

        openCart()
                .compareProducts(list);

        CardsGoodsInTheCartPage shoppingContainerPage = new CardsGoodsInTheCartPage();
        shoppingContainerPage.getRemoveButton().click();
        shoppingContainerPage.productRemoval();

        shoppingContainerPage.doClickButtonContinueShopping();
        homePage.checkTheDeleteButton();
    }
}
