package ru.practikum.yandex.pageobject;

import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.LinkElement;

public class PersonalAccountPage {

    public static final String PERSONAL_ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //клик Конструктор
    private String constructorLinkLocator = ".//p[contains(@class, 'AppHeader_header__linkText__3q_va') and text()='Конструктор']";

    //клик Stellar Burgers.
    private String stellarBurgersLinkLocator = ".//div[@class='AppHeader_header__logo__2D0X2']/a";

    //кнопка Выход
    private String exitFromPersonalAccountButtonLocator = ".//button[@type='button' and contains(@class, 'Account_button') and text()='Выход']";

    //клик по Конструктору
    public void constructorLinkClick() {
        LinkElement constructorLink = new LinkElement(constructorLinkLocator);
        constructorLink.clickLink();
    }

    //клик по Stellar Burgers
    public void stellarBurgersLinkClick() {
        LinkElement stellarBurgersLink = new LinkElement(stellarBurgersLinkLocator);
        stellarBurgersLink.clickLink();
    }

    //Выход из Личного кабинета
    public void exitFromPersonalAccountButtonClick() {
        ButtonElement exitFromPersonalAccountButton = new ButtonElement(exitFromPersonalAccountButtonLocator);
        exitFromPersonalAccountButton.clickButton();
    }
}


