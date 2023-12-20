package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.CardsGoodsInTheCartPage;
import PageObject.Elements.MainPage.HomePage;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class DeletingAnItemFromTheCartTest extends BaseClass implements ToolBarElements {
    private CardsGoodsInTheCartPage shoppingContainerPage = new CardsGoodsInTheCartPage();

    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Удаление товара из корзины", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void deletingAnItem(UserData data) {
        authorizationPage.fillInFields(data.getUser(), data.getPassword());
        homePage.removeFromCart();
        homePage.addItemToCart();
        price = homePage.getPrice().getText();
        container.click();
        steps.cartOpeningCheck(price);
        shoppingContainerPage.getRemoveButton().click();
        steps.productRemoval();
        shoppingContainerPage.doClickButtonContinueShopping();
        steps.checkTheDeleteButton();
    }
}
