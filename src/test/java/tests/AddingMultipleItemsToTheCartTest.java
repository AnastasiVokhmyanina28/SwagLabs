package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.CardsGoodsInTheCartPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.OrderFormPage;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import Person.Person;
import Servise.ChromeDriver.BaseClass;
import Step.Steps;
import org.testng.annotations.Test;

import java.util.List;

public class AddingMultipleItemsToTheCartTest extends BaseClass implements ToolBarElements {
    private HomePage homePage = new HomePage();
    private CardsGoodsInTheCartPage cartPage = new CardsGoodsInTheCartPage();
    private Person person = Person.randomized();
    private OrderFormPage orderFormPage = new OrderFormPage();
    private Steps step = new Steps();

    @Test(description = "Добавление нескольких товаров в корзину", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void addingMultipleItemsToTheCart(UserData data) {
        /**Авторизация*/
        new AuthorizationPage().fillInFields(data.user, data.getPassword());

        /**Очистка*/
        homePage.removeFromCart();

        /**Добавить несколько товаров в корзину*/
        homePage.doAddMultipleItemsToCart();
        List<ProductPojo> list = homePage.getProductsInCart();
        step.checkingTheAdditionOfGoods();

        /** Перейти в корзину*/
        openContainer();
        cartPage.getAllProducts();
        cartPage.compareProducts(list);

        /**Заполнить данные для заказа*/
        cartPage.doClickButtonCheckout();
        orderFormPage.getFirstName().setValue(person.getName());
        orderFormPage.getLastName().setValue(person.getLastName());
        orderFormPage.getPostalCode().setValue(person.getPostalCode());

    }


}
