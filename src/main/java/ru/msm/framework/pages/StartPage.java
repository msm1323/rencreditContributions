package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class StartPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class,'nav__item_parent')]//span[not(@class)]")
    protected List<WebElement> navSpans;

    @FindBy(xpath = "//li[contains(@class,'nav__item_parent')]//a[@class='nav__link']")
    protected List<WebElement> subAs;

    public void clickOnNavMenuSpan(String span){
        clickOnElByName(navSpans, span);
    }

    public void clickOnNavSubMenuA(String subA) {
        clickOnElByName(subAs, subA);
    }

    private void clickOnElByName(List<WebElement> elements, String name) {
        Optional<WebElement> op = elements.stream()
                .filter(el -> el.getText().contains(name))
                .findFirst();
        if (op.isPresent()) {
            waitUtilElementToBeVisible(
                    waitUntilElementToBeClickable(op.get())).click();
        } else {
            Assertions.fail("Элемент \"" + name + "\" не найден!");
        }
    }

}
