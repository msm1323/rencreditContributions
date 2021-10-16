package ru.msm.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import ru.msm.framework.managers.PageManager;

import java.io.InputStream;
import java.util.stream.Stream;

public class ContributionsPageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    @И("^Выбрать – (.+)$")
    public void selectCurrency(String currency) {
        PAGE_MANAGER.getContributionsPage().selectCurrency(currency);
    }

    @И("^Выбрать открытие вклада - (.+)$")
    public void selectOpeningDepositMode(String mode) {
        PAGE_MANAGER.getContributionsPage().selectOpeningDepositMode(mode);
    }

    @И("^Сумма вклада – (.+)$")
    public void depositAmount(String depAmount) {
        PAGE_MANAGER.getContributionsPage().depositAmount("Сумма вклада", depAmount);
    }

    @И("^Срок – (\\d+) месяцев$")
    public void term(String term) {
        PAGE_MANAGER.getContributionsPage().term(term);
    }

    @И("^Ежемесячное пополнение – (.+)$")
    public void monthlyTopUp(String mTopUp) {
        PAGE_MANAGER.getContributionsPage().monthlyTopUp("Ежемесячное пополнение", mTopUp);
    }

    @И("^Отметить – (.+)$")
    public void selectMonthlyCapitalization(String mCap) {
        PAGE_MANAGER.getContributionsPage().selectMonthlyCapitalization(mCap);
    }

    @Затем("^Проверить расчеты по вкладу:$")
    public void checkSettlementsOnDeposit(DataTable table) {
        PAGE_MANAGER.getContributionsPage().checkSettlementsOnDeposit(table.asLists());
    }
}
