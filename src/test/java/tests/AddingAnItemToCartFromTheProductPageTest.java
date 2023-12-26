package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.*;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddingAnItemToCartFromTheProductPageTest extends BaseClass implements ToolBarElements {

    @Test(description = "Добавить товар в корзину из карточки и оформить заказ", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingAnItemToCart(UserData data) {
        /**Авторизация*/
        HomePage homePage = new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        ProductCardPage productCardPage = homePage
                .removeFromCart()
                .getCardPage();

        productCardPage
                .addProduct()
                .checkOfAddingAnItemToTheCart();

        List<ProductPojo> list = productCardPage.getAllProducts();

        CardsGoodsInTheCartPage cardsGoodsInTheCartElements =
                openContainer();

        cardsGoodsInTheCartElements.compareProducts(list);
        cardsGoodsInTheCartElements.cartOpeningCheck();

        OrderFormPage formPage = cardsGoodsInTheCartElements.doClickButtonCheckout();
        formPage
                .dataFillingPerson()
                .assertPageActive();

        CheckoutOverviewPage overviewPage = formPage.doClickButtonContinue();
        overviewPage.orderPlacement();

        CheckoutCompletePage completePage = overviewPage.doClickButtonFinish();
        completePage.orderConfirmation();

        completePage.doClickButtonBackHome()
                .homepageIsOpen();
    }
}
