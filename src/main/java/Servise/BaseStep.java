package Servise;

import PageObject.Page.HomePage;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;

public class BaseStep {

    private static HomePage homePage = new HomePage();

    public static void emptyYourCartCollection() {
        for (WebElement element : homePage.homeElements.allButtons.filterBy(text("Remove"))) {
            element.click();
        }
    }

    public static void emptyYourCart() {
        while (homePage.homeElements.allDeleteButton.exists()) {
            homePage.homeElements.allDeleteButton.click();
        }
    }
}
