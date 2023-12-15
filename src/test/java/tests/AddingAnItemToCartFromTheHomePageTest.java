package tests;

import Data.User.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Page.AuthorizationPage;
import PageObject.Page.HomePage;
import Servise.BaseStep;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import ToolBar.ToolBarElements;
import org.testng.annotations.Test;

public class AddingAnItemToCartFromTheHomePageTest extends BaseClass implements ToolBarElements {
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();
    private BaseStep baseStep = new BaseStep();
    private Steps steps = new Steps();
    private String price;

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void addingAnItemToCart(UserData data) {
        authorizationPage.elements.fillInFields(data.getUser(), data.getPassword());
        baseStep.emptyYourCart();
        homePage.homeElements.addItemToCart();
        price = homePage.homeElements.getPrice().getText();
        steps.cartOpeningCheck(price);
    }
}
