package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import PageObject.Page.HomePage;
import PageObject.Page.ShoppingContainerPage;
import Servise.BaseStep;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class DeletingAnItemFromTheCartTest extends BaseClass implements ToolBarElements {
    private ShoppingContainerPage shoppingContainerPage = new ShoppingContainerPage();

    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();
    private BaseStep baseStep = new BaseStep();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Удаление товара из корзины", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void deletingAnItem(UserData data) {
        authorizationPage.elements.fillInFields(data.getUser(), data.getPassword());
        homePage.homeElements.removeFromCart();
        homePage.homeElements.addItemToCart();
        price = homePage.homeElements.getPrice().getText();
        container.click();
        steps.cartOpeningCheck(price);
        shoppingContainerPage.cardsGoodsInTheCartElements.getRemoveButton().click();
        steps.productRemoval();
        shoppingContainerPage.cardsGoodsInTheCartElements.getContinueShoppingButton().click();
        steps.checkTheDeleteButton();
    }
}
