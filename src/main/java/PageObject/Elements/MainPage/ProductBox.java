package PageObject.Elements.MainPage;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.NonNull;


public class ProductBox {

    SelenideElement container;

    public ProductBox(@NonNull SelenideElement container) {
        this.container = container;
    }

    SelenideElement itemName = container.$(".inventory_item_name");
    SelenideElement itemPrice = container.$(".inventory_item_price");

    SelenideElement addButton = container.$x("//button[@class='btn btn_primary btn_small btn_inventory ']");


    public String getName(){
        return itemName.getText();
    }

    public Double getPrice(){
        return
                Double.valueOf(itemPrice.getText().replace("$",""));
    }
//это фто
    public Boolean inCart(){

    }

    public void addToCart(){

    }

    public void  removeFromCart(){

    }

}
