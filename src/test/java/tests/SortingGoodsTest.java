package tests;

import Data.User.UserData;
import Data.User.Users;
import Data.models.ProductPojo;
import PageObject.AuthorizationPage;
import PageObject.MainPage.HomePage;
import PageObject.Sorting.SortingElements;
import Servise.ChromeDriver.BaseClass;
import java.util.logging.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortingGoodsTest extends BaseClass {
    private static final Logger log = Logger.getLogger(SortingGoodsTest.class.getName());


    @DataProvider()
    public static Object[][] sortData() {

        /**сортировка по названию*/
        Comparator<ProductPojo> fromAtoZ = Comparator.comparing(ProductPojo::getProductName);

        Comparator<ProductPojo> fromZtoA = Comparator.comparing(ProductPojo::getProductName).reversed();


        /**сортировка по стоимости*/
        Comparator<ProductPojo> fromLowtoHigh = Comparator.comparing(ProductPojo::getProductPrice);

        Comparator<ProductPojo> fromHightoLow = Comparator.comparing(ProductPojo::getProductPrice).reversed();


        return new Object[][]{

                List.of(
                        Users.STANDARD.getUserData(),
                        SortingElements.A_TO_Z,
                        fromAtoZ
                ).toArray(),

                List.of(
                        Users.STANDARD.getUserData(),
                        SortingElements.Z_TO_A,
                        fromZtoA
                ).toArray(),

                List.of(
                        Users.STANDARD.getUserData(),
                        SortingElements.LOW_TO_HIGH,
                        fromLowtoHigh
                ).toArray(),

                List.of(
                        Users.STANDARD.getUserData(),
                        SortingElements.HIGH_TO_LOW,
                        fromHightoLow
                ).toArray(),

                List.of(
                        Users.VISUAL.getUserData(),
                        SortingElements.A_TO_Z,
                        fromAtoZ
                ).toArray(),

                List.of(
                        Users.VISUAL.getUserData(),
                        SortingElements.Z_TO_A,
                        fromZtoA
                ).toArray(),

                List.of(
                        Users.VISUAL.getUserData(),
                        SortingElements.LOW_TO_HIGH,
                        fromLowtoHigh
                ).toArray(),

                List.of(
                        Users.VISUAL.getUserData(),
                        SortingElements.HIGH_TO_LOW,
                        fromHightoLow
                ).toArray(),

                List.of(
                        Users.LOCKED.getUserData(),
                        SortingElements.A_TO_Z,
                        fromAtoZ
                ).toArray(),

                List.of(
                        Users.LOCKED.getUserData(),
                        SortingElements.Z_TO_A,
                        fromZtoA
                ).toArray(),

                List.of(
                        Users.LOCKED.getUserData(),
                        SortingElements.LOW_TO_HIGH,
                        fromLowtoHigh
                ).toArray(),

                List.of(
                        Users.LOCKED.getUserData(),
                        SortingElements.HIGH_TO_LOW,
                        fromHightoLow
                ).toArray(),

                List.of(
                        Users.PROBLEM.getUserData(),
                        SortingElements.A_TO_Z,
                        fromAtoZ
                ).toArray(),

                List.of(
                        Users.PROBLEM.getUserData(),
                        SortingElements.Z_TO_A,
                        fromZtoA
                ).toArray(),

                List.of(
                        Users.PROBLEM.getUserData(),
                        SortingElements.LOW_TO_HIGH,
                        fromLowtoHigh
                ).toArray(),

                List.of(
                        Users.PROBLEM.getUserData(),
                        SortingElements.HIGH_TO_LOW,
                        fromHightoLow
                ).toArray()
        };

    }

    @Test(description = "Отсортировать товар по убыванию цены", dataProvider = "sortData")
    public void sortProductName(UserData userData, SortingElements sortingElement, Comparator<ProductPojo> comparing) {

        log.info(userData.getUser());

        AuthorizationPage authorizationPage = openLoginPage();
        HomePage homePage = authorizationPage.login(userData);

        homePage.selectSorting(sortingElement);

        assertThat(homePage.getAllProducts())
                .isSortedAccordingTo(comparing);
    }

}
