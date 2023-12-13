package tests;

import Data.UserData;
import PageObject.Elements.AuthorizationElements;
import PageObject.Elements.CardsGoodsInTheCartElements;
import PageObject.Page.AuthorizationPage;
import PageObject.Page.HomePage;
import Servise.BaseStep;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddingAnItemToCartTest extends BaseClass {
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private HomePage homePage = new HomePage();
    private BaseStep baseStep = new BaseStep();
    private String price;
    private CardsGoodsInTheCartElements cardsGoodsInTheCartElements = new CardsGoodsInTheCartElements();

    @Test(description = "Добавление товара в корзину с главной страницы", dataProvider = "authParamUser", dataProviderClass = AuthorizationElements.class)
    public void addingAnItemToCartTest(UserData data) {
        authorizationPage.elements.fillInFields(data.getUser(), data.getPassword());
        baseStep.emptyYourCart();
        homePage.homeElements.addItemToCart();
        price = homePage.homeElements.getPrice().getText();
        CardsGoodsInTheCartElements container = homePage.toolBarElements.openContainer();
        assertThat(container.getCards().isEmpty()).as("Корзина товаров пуста").isFalse();
        assertThat(cardsGoodsInTheCartElements.getProductName().getText()).as("Название товара отличается").isEqualTo(homePage.homeElements.getProductName().getText());
        assertThat(cardsGoodsInTheCartElements.getPrice().getText()).as("Стоимость товара отличается").isEqualTo(price);
        assertThat(cardsGoodsInTheCartElements.getRemoveButton().exists()).as("Кнопка 'Remove' не отображается").isTrue();
    }
}
