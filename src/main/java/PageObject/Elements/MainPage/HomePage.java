package PageObject.Elements.MainPage;

import Data.models.ProductPojo;
import PageObject.Elements.ProductCardPage;
import PageObject.Elements.Sorting.SortingElements;
import PageObject.Elements.blocks.ToolBar.ProductsActions;
import PageObject.Elements.blocks.ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;


public class HomePage implements ToolBarElements, ProductsActions {

    private final ElementsCollection productTableElements = $$(By.className("inventory_item")).as("Карточки товаров на главное странице");
    private final SelenideElement productSort = $(By.className("product_sort_container")).as("Сортировка товара");
    private final SelenideElement productName = $(By.id("item_4_title_link")).as("Название товара");
    private final SelenideElement deleteButton = $(By.id("remove-sauce-labs-backpack")).as("Кнопка удаления товара");
    private final SelenideElement addButtonBackpack = $(By.id("add-to-cart-sauce-labs-backpack")).as("Кнопка добавления товара");
    private final SelenideElement addButtonJacket = $(By.id("add-to-cart-sauce-labs-fleece-jacket")).as("Кнопка добавления товара в корзину. Жакет");

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

    @Step("Выбор сортировки")
    public HomePage selectSorting(SortingElements element) {
        productSort.click();
        productSort.selectOption(element.getOption());
        this.initProducts();
        return this;
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
