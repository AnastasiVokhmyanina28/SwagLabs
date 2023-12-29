package PageObject.Elements.MainPage;

import Data.User.Users;
import Data.models.ProductPojo;
import PageObject.Elements.ProductCardPage;
import PageObject.Elements.Sorting.SortingElements;
import PageObject.Elements.blocks.ToolBar.ProductsActions;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;


public class HomePage implements ToolBarElements, ProductsActions {

    public final SelenideElement allDeleteButton = $x("//button[contains(@id, 'remove')]").as("Все кнопки удаления");
    public final ElementsCollection allButtons = $$x("//div[@class='inventory_item']//button").as("Кнопки из карточки товара");
    private final ElementsCollection productTableElements = $$(".inventory_item").as("Карточки товаров на главное странице");
    private final SelenideElement productSort = $(".product_sort_container").as("Сортировка товара");
    private final ElementsCollection listOfElementNames = $$(".inventory_item_name").as("Список наименований товаров");
    private final ElementsCollection listOfElementsPrice = $$(".inventory_item_price").as("Список цен товаров");
    /****/
    private final SelenideElement sortAZ = $x("//option[@value='az']").as("Сортировка по алфавиту");
    private final SelenideElement ascendingOrderOfPrice = $x("//option[@value='lohi']").as("Сортировка по возрастанию цены");
    private final SelenideElement productName = $("#item_4_title_link").as("Название товара");
    private final SelenideElement deleteButton = $("#remove-sauce-labs-backpack").as("Кнопка удаления товара");
    private final SelenideElement addButtonBackpack = $("#add-to-cart-sauce-labs-backpack").as("Кнопка добавления товара");
    private final SelenideElement addButtonJacket = $("#add-to-cart-sauce-labs-fleece-jacket").as("Кнопка добавления товара в корзину. Жакет");
    private final SelenideElement price = $x("//div[@class='inventory_item_label']/a[@id='item_4_title_link']/../following-sibling::div//div[@class='inventory_item_price']").as("Цена товара");

    public List<ProductBox> initProducts() {
        List<ProductBox> result = new ArrayList<>();
        if (!productTableElements.isEmpty()) {
            productTableElements.forEach(
                    (element ->
                            result.add(new ProductBox(element))
                    )
            );
        }
        return result;
    }


    @Override
    @Step("Список всех товаров")
    public List<ProductPojo> getAllProducts() {
        List<ProductPojo> result = new ArrayList<>();
        initProducts().forEach(
                productBox -> result.add(productBox.toPojo())
        );
        return result;
    }

    @Override
    @Step("Записать в список товары, которые добавлены в корзину (главная страница)")
    public List<ProductPojo> getProductsInCart() {
        List<ProductPojo> pojoList = new ArrayList<>();
        initProducts().forEach(
                (product) ->
                {
                    if (product
                            .inCart().equals(true)
                    ) {
                        pojoList.add(product.toPojo());

                    }
                }
        );
        return pojoList;
    }

    public HomePage removeFromCart() {
        productTableElements
                .forEach(
                        (product) -> {
                            if (product
                                    .$x(".//button")
                                    .getAttribute("id")
                                    .contains("remove")
                            ) {
                                product
                                        .$x(".//button").click();
                            }
                        }
                );
        return this;
    }

    @Step("Добавление товара в корзину")
    public HomePage addItemToCart() {
        addButtonBackpack.click();
        assertThat(deleteButton.isDisplayed()).as("Кнопка 'Remove' не отображается. Товар не добавлен в корзину").isTrue();
        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();
        assertThat(container.getText()).isEqualTo("1");
        return this;
    }

    @Step("Добавить в корзину несколько товаров")
    public HomePage doAddMultipleItemsToCart() {
        addButtonBackpack.click();
        addButtonJacket.click();
        return this;
    }

    @Step("Сортировать элементы в порядке возрастания по именам. Проверка корректности работы сортировки")
    public void sortElementsInAscendingOrderNames(ElementsCollection elementsCollection) {
        List<String> listOfGoods = new ArrayList();
        List<String> adw = new ArrayList();
        for (int i = 0; i < elementsCollection.size(); i++) {
            listOfGoods.add(elementsCollection.get(i).getText());
        }

        adw =
                listOfGoods
                        .stream()
                        .sorted(
                        )
                        .collect(Collectors.toList()
                        )
        ;

        assertThat(listOfGoods)
                .isEqualTo(adw);
    }

    @Step("Выбор сортировки")
    public HomePage selectSorting(SortingElements element) {
        productSort.click();
        productSort.selectOption(element.getOption());
        this.initProducts();
        return this;
    }

    @Step("Сортировка элеметов по возрастанию стоимости. Проверка корректности работы сортировки (передайтся список после сортировки на сайте)")
    public static List<ProductPojo> sortElementsInAscendingOrderPrice(List<ProductPojo> pojoList) {
        List<ProductPojo> listOfGoods = new ArrayList<>();

        listOfGoods = pojoList
                .stream()
                .sorted(Comparator.comparing(ProductPojo::getProductPrice))
                .collect(Collectors.toList());

        return listOfGoods;
    }

    @Step("Открыть карточку товара")
    public ProductCardPage getCardPage() {
        productName.click();
        return new ProductCardPage();
    }

    @Step("Проверка отображения главной страницы")
    public HomePage homepageIsOpen() {
        assertThat(productTableElements.isEmpty()).as("Карточки товаров не отображаются").isFalse();
        return this;
    }

    @Step("Проверка добавления товаров в корзину с главной страницы (число на бейдже соответствует кол-ву элементов в корзине)")
    public HomePage checkingTheAdditionOfGoods() {
        productsQuantityControl(getProductsInCart().size());
        return this;
    }

    @Step("Проверка кнопки удаления")
    public HomePage checkTheDeleteButton() {
        assertThat(deleteButton.exists()).isFalse();
        return this;
    }

}
