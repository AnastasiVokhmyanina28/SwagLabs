package PageObject.Elements.MainPage;

import static com.codeborne.selenide.Selenide.*;

import ToolBar.ToolBarElements;
import com.codeborne.selenide.ElementsCollection;

import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HomeElements implements ToolBarElements {

    List<ProductBox> allProducts = new ArrayList<>();

    private final ElementsCollection productTableElements = $$(".inventory_item").as("Карточки товаров на главное странице");
    private final SelenideElement productSort = $(".active_option").as("Сортировка товара");
    //private final ElementsCollection listOfElementNames = $$(".inventory_item_name").as("Список наименований товаров");


    private final SelenideElement productName = $("#item_4_title_link").as("Название товара");
    private final SelenideElement deleteButton = $("#remove-sauce-labs-backpack").as("Кнопка удаления товара");
    public final SelenideElement allDeleteButton = $x("//button[contains(@id, 'remove')]").as("Все кнопки удаления");


    public final ElementsCollection allButtons = $$x("//div[@class='inventory_item']//button").as("Кнопки из карточки товара");
    private final SelenideElement addButton = $("#add-to-cart-sauce-labs-backpack").as("Кнопка добавления товара");
    private final SelenideElement price = $x("//div[@class='inventory_item_label']/a[@id='item_4_title_link']/../following-sibling::div//div[@class='inventory_item_price']").as("Цена товара");

    //todo
    private List<ProductBox> initProducts(){
        productTableElements.forEach(
                (element ->
                        allProducts.add(new ProductBox(element))
                )
        );
    }

    public void removeFromCart() {
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
    }

    @Step("Добавление товара в корзину")
    public void addItemToCart() {

        addButton.click();
        assertThat(deleteButton.isDisplayed()).as("Кнопка 'Remove' не отображается. Товар не добавлен в корзину").isTrue();
        assertThat(badge.isDisplayed()).as("При добавлении товара, на корзине не отображается уведомляющий знак").isTrue();
        assertThat(container.getText()).isEqualTo("1");
    }

    @Step("Сортировать элементы по имени")
    public void sortByName(ElementsCollection elementsCollection) {
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
                .containsAll(adw);
    }



}
