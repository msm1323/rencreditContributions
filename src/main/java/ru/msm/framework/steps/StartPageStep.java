package ru.msm.framework.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.msm.framework.managers.PageManager;

public class StartPageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    @Когда("^Перейти по ссылке - https://rencredit.ru и перейти в меню – (.+)$")
    public void clickOnNavMenuSpan(String span) {
        PAGE_MANAGER.getStartPage().clickOnNavMenuSpan(span);
    }

    @Тогда("^Выбрать подменю – (.+)$")
    public void clickOnNavSubMenuA(String subA) {
        PAGE_MANAGER.getStartPage().clickOnNavSubMenuA(subA);
    }
}
