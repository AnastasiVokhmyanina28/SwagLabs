package tests;

import Data.User.UserData;
import Data.models.ProductPojo;
import PageObject.Elements.AuthorizationPage;
import PageObject.Elements.MainPage.HomePage;
import PageObject.Elements.Sorting.SortingElements;
import Servise.ChromeDriver.BaseClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Function;

public class SortingGoodsTest extends BaseClass {

    @Test(description = "Отсортировать товар по возрастанию цены", dataProvider = "authParamUser", dataProviderClass = AuthorizationPage.class)
    public void sortProductName(UserData userData) {// + enum- список сортировок

        AuthorizationPage authorizationPage = openLoginPage();
        HomePage homePage = authorizationPage.login(userData);

        homePage.sortElementsInAscendingOrderPrice(homePage.getAllProducts());



//                homePage.selectSorting(sortingElements.);    + передаем enum()
        // забираем элементы в pojo




//        List<ProductPojo> awda = sort.apply(asPage - то, что отсортирвано дропауном );  //- , как нам нужно
//сравнить списки pojo





    }

//    public void wadawd(){
//        sortProductName(new UserData("2","2"), (unsortedList) -> unsortedList.stream().sorted().toList());
//    }

}
