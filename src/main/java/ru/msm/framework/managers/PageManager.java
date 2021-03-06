package ru.msm.framework.managers;

import ru.msm.framework.pages.*;

public class PageManager {

    private static PageManager PAGE_MANAGER = null;

    private PageManager() {
    }

    public static PageManager getINSTANCE() {
        if (PAGE_MANAGER == null) {
            PAGE_MANAGER = new PageManager();
        }
        return PAGE_MANAGER;
    }

    private StartPage startPage;

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    private ContributionsPage contributionsPage;

    public ContributionsPage getContributionsPage() {
        if (contributionsPage == null) {
            contributionsPage = new ContributionsPage();
        }
        return contributionsPage;
    }

    public void quit(){
        PAGE_MANAGER = null;
    }

}
