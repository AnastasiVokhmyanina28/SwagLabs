package PageObject.Page;

import PageObject.Elements.HomeElements;
import PageObject.Elements.ToolBarElements;
import lombok.Getter;

public class HomePage {
    //главная страница
    public static ToolBarElements toolBarElements = new ToolBarElements();
    public static HomeElements homeElements = new HomeElements();

}
