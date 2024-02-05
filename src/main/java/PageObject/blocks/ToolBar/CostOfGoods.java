package PageObject.blocks.ToolBar;

import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface CostOfGoods {

    @Step("Получить стоимость товара")
    default BigDecimal getCostOfGoods(String price) {
        return new BigDecimal(Double.parseDouble(price.split("\\$")[1])).setScale(2, RoundingMode.HALF_DOWN);
    }
}