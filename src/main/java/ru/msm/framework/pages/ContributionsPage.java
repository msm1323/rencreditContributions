package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ContributionsPage extends BasePage {

    @FindBy(xpath = "//input[contains(@name,'calc-currency')]") //пара
    protected List<WebElement> currencies;

    @FindBy(xpath = "//span[contains(@class,'calculator__currency-field-text')]")
    protected List<WebElement> spans;

    @FindBy(xpath = "//span[contains(@class,'check-block-text')]") //пара
    protected List<WebElement> checkBlockText;

    @FindBy(xpath = "//div[contains(@class,'jq-checkbox calculator__check')]")
    protected List<WebElement> checkBoxInput;

    @FindBy(xpath = "//input[contains(@class,'currency-input')]/../../..//label[contains(@class,'input-label')]") //пара
    protected List<WebElement> labels;

    @FindBy(xpath = "//input[contains(@class,'currency-input')]")
    protected List<WebElement> inputs;

    @FindBy(xpath = "//select[@name='period']")
    protected WebElement selectBox;

    @FindBy(xpath = "//tr[contains(@class,'result-table-row')]")
    protected List<WebElement> resTable;

    @FindBy(xpath = "//div[@class='calculator__dep-result']")
    protected WebElement depResult;

    private void selectElFromPair(String key, String msg) {
        for (int i = 0; i < checkBlockText.size(); i++) {
            if (checkBlockText.get(i).getText().contains(key)) {
                if (!checkBoxInput.get(i).getAttribute("class").contains("checked")) {
                    try {
                        waitUntilElementToBeClickable(checkBlockText.get(i)).click();
                        wait.until(ExpectedConditions.attributeContains(checkBoxInput.get(i), "class", "checked"));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        Assertions.fail(msg);
                    }
                }
                break;
            }
        }
    }

    public void selectCurrency(String currency) {
        for (int i = 0; i < spans.size(); i++) {
            if (spans.get(i).getText().contains(currency)) {
                if (currencies.get(i).getAttribute("checked") == null) {    //or false?
                    try {
                        waitUntilElementToBeClickable(spans.get(i)).click();
                        wait.until(ExpectedConditions.attributeToBe(currencies.get(i), "checked", "true"));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        Assertions.fail("Нужная валюта не выбрана!");
                    }
                }
                break;
            }
        }
    }

    public void selectOpeningDepositMode(String mode) {
        selectElFromPair(mode, "Нужный способ открытия банка не выбран!");
    }

    private void selectAndFillField(String fieldName, String value) {
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getText().contains(fieldName)) {
                inputs.get(i).clear();
                inputs.get(i).sendKeys(value);
                Assertions.assertEquals(value, inputs.get(i).getAttribute("value"),
                        "Поле \"" + fieldName + "\" не содержит заданное значение!");
                break;
            }
        }
    }

    public void depositAmount(String fieldName, String depAmount) {
        selectAndFillField(fieldName, depAmount);
    }

    public void term(String term) {
        Select termSelect = new Select(selectBox);
        termSelect.selectByValue(term);
        try {
            wait.until(ExpectedConditions.attributeContains(selectBox, "value", term));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Assertions.fail("Требуемый срок не выбран!");
        }
    }

    public void monthlyTopUp(String fieldName, String mTopUp) {
        selectAndFillField(fieldName, mTopUp);
    }

    public void selectMonthlyCapitalization(String mCap) {
        selectElFromPair(mCap, "\"" + mCap + "\" не выбрано!");
    }

    public void checkSettlementsOnDeposit(List<List<String>> table) {
        for (List<String> arg : table) {
            if (depResult.getText().contains(arg.get(0))) {
                try {
                    wait.until(ExpectedConditions.attributeContains(depResult, "outerText", arg.get(1)));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    Assertions.fail("Рассчет \"" + arg.get(0) + "\" не соответствует ожидаемому!");
                }
            }
            for (WebElement element : resTable) {
                if (element.getText().contains(arg.get(0))) {
                    try {
                        wait.until(ExpectedConditions.attributeContains(element, "outerText", arg.get(1)));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        Assertions.fail("Рассчет \"" + arg.get(0) + "\" не соответствует ожидаемому!");
                    }
                }
            }
        }
    }

}
