package ru.practikum.yandex.pageobject;

import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.LinkElement;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //кнопка "Личный Кабинет"
    private String personalAccountButtonLocator = ".//p[contains(text(), 'Личный Кабинет')]";

    //кнопка "Войти в аккаунт" на главной странице
    private String loginOnTheMainPageButtonLocator = ".//button[text()='Войти в аккаунт']";

    //Кнопка "Оформить заказ"
    private String getOrderLocator = ".//button[contains(@class, 'button_button_type_primary__1O7Bx') and text()='Оформить заказ']";

    // Локатор неактивной вкладки Соусы
    private String inactiveSauceLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]";
    // Локатор неактивной вкладки Начинки
    private String inactiveFillingLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]";
    // Локатор неактивной вкладки Булки
    private String inactiveBunLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]";

    // Локатор активной вкладки Соусы
    private String activeSauceLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]";
    // Локатор активной вкладки Начинки
    private String activeFillingLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]";
    // Локатор активной вкладки Булки
    private String activeBunLinkLocator = "//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]";

    //URL главной страницы
    public void openMainPage() {
        open(MAIN_PAGE_URL);
    }

    //Клик по кнопке "Личный кабинет"
    public void personalAccountButtonClick() {
        ButtonElement personalAccountButton = new ButtonElement(personalAccountButtonLocator);
        personalAccountButton.clickButton();
    }

    //Клик по кнопке "Войти в аккаунт"
    public void loginOnTheMainPageButtonClick() {
        ButtonElement loginOnTheMainPageButton = new ButtonElement(loginOnTheMainPageButtonLocator);
        loginOnTheMainPageButton.clickButton();
    }

    //Проверка отображения кнопки "Оформить заказ"
    public boolean isVisibleGetOrderButton() {
        ButtonElement getOrderButton = new ButtonElement(getOrderLocator);
        return getOrderButton.isVisibleButton();
    }

    //Клик по неактивной вкладке Соусы
    public void inactiveSauceLinkClick() {
        LinkElement inactiveSauceLink = new LinkElement(inactiveSauceLinkLocator);
        inactiveSauceLink.clickLink();
    }

    //Клик по неактивной вкладке Начинки
    public void inactiveFillingLinkClick() {
        LinkElement inactiveFillingLink = new LinkElement(inactiveFillingLinkLocator);
        inactiveFillingLink.clickLink();
    }

    //Клик по неактивной вкладке Булки
    public void inactiveBunLinkClick() {
        LinkElement inactiveBunLink = new LinkElement(inactiveBunLinkLocator);
        inactiveBunLink.clickLink();
    }

    //Клик по неактивной вкладке Соусы
    public void activeSauceLinkLocatorClick() {
        LinkElement activeSauceLink = new LinkElement(activeSauceLinkLocator);
        activeSauceLink.clickLink();
    }

    //Клик по локатору активной вкладке Начинки
    public void activeFillingLinkClick() {
        LinkElement activeFillingLink = new LinkElement(activeFillingLinkLocator);
        activeFillingLink.clickLink();
    }

    //Клик по неактивной вкладке Булки
    public void activeBunLinkClick() {
        LinkElement activeBunLink = new LinkElement(activeBunLinkLocator);
        activeBunLink.clickLink();
    }
}




