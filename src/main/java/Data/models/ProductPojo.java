package Data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductPojo {
    private String productName;
    private BigDecimal productPrice;
    private Boolean inCart;
}
