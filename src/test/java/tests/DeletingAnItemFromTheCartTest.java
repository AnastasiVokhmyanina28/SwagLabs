package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.CardsGoodsInTheCartPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import org.testng.annotations.Test;

public class DeletingAnItemFromTheCartTest extends BaseClass implements ToolBarElements {
    private String price;

    @Test(description = "Удаление товара из корзины", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void deletingAnItem(UserData data) {
        new AuthorizationPage().fillInFields(data.getUser(), data.getPassword());

        HomePage homePage = new HomePage();
        homePage.removeFromCart();
        homePage.addItemToCart();

        price = homePage.getPrice().getText();

        container.click();

        Steps steps = new Steps();
        steps.cartOpeningCheck(price);

        CardsGoodsInTheCartPage shoppingContainerPage = new CardsGoodsInTheCartPage();
        shoppingContainerPage.getRemoveButton().click();
        steps.productRemoval();
        shoppingContainerPage.doClickButtonContinueShopping();
        steps.checkTheDeleteButton();
    }
}
