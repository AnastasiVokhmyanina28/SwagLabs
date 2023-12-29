package PageObject.Elements.Sorting;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortingElements {
    A_TO_Z("Name (A to Z)"),
    Z_TO_A("Name (Z to A)"),
    LOW_TO_HIGH("Price (low to high)"),
    HIGH_TO_LOW("Price (high to low)");


    String option;
}
