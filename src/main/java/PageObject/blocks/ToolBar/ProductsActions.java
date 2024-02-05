package PageObject.blocks.ToolBar;

import Data.models.ProductPojo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public interface ProductsActions {

    List<ProductPojo> getAllProducts();

    List<ProductPojo> getProductsInCart();

    default void compareProducts(List<ProductPojo> expectedProducts) {
        assertThat(getAllProducts())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedProducts.toArray(new ProductPojo[0]));
    }

}
