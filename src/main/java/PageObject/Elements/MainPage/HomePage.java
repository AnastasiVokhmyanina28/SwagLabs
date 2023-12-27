package PageObject.Elements.MainPage;

import Data.models.ProductPojo;
import PageObject.Elements.ProductCardPage;
import PageObject.Elements.blocks.ToolBar.ProductsActions;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
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

    @Step("Сортировать элементы в порядке возрастания по именам")
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

    @Step("Сортировка элеметов по возрастанию стоимости")
    public void sortElementsInAscendingOrderPrice(ElementsCollection collection) {
        List<Integer> listOfGoods = new ArrayList<>();
        List<Integer> price = new ArrayList<>();

        for (int i = 0; i < collection.size(); i++) {
            listOfGoods.add(Integer.parseInt(collection.get(i).getText().split("\\$")[1]));
        }

        price = listOfGoods
                .stream()
                .sorted()
                .collect(Collectors.toList());

        assertThat(listOfGoods).isEqualTo(price);
    }

    @Step("Открыть карточку товара")
    public ProductCardPage getCardPage() {
        productName.click();
        return new ProductCardPage();
    }

    @Step("Проверка отображения главной страницы")
    public void homepageIsOpen() {
        assertThat(getProductTableElements().isEmpty()).as("Карточки товаров не отображаются").isFalse();
    }

    @Step("Проверка добавления товаров в корзину с главной страницы (число на бейдже соответствует кол-ву элементов в корзине)")
    public HomePage checkingTheAdditionOfGoods() {
        productsQuantityControl(getProductsInCart().size());
        return this;
    }

    @Step("Проверка кнопки удаления")
    public void checkTheDeleteButton() {
        assertThat(getDeleteButton().exists()).isFalse();
    }

}
