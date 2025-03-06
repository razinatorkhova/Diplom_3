package ru.practikum.yandex.pageobject;

import io.qameta.allure.Step;
import ru.practikum.yandex.api.Endpoints;
import ru.practikum.yandex.elements.ButtonElement;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    //кнопка "Личный Кабинет"
    private String personalAccountButtonLocator = ".//p[contains(text(), 'Личный Кабинет')]";

    //кнопка "Войти в аккаунт" на главной странице
    private String loginOnTheMainPageButtonLocator = ".//button[text()='Войти в аккаунт']";

    //Кнопка "Оформить заказ"
    public String getOrderLocator = ".//button[contains(@class, 'button_button_type_primary__1O7Bx') and text()='Оформить заказ']";

    //URL главной страницы
    public void openMainPage() {
        open(Endpoints.MAIN_PAGE_URL);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void personalAccountButtonClick() {
        ButtonElement personalAccountButton = new ButtonElement(personalAccountButtonLocator);
        personalAccountButton.clickButton();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void loginOnTheMainPageButtonClick() {
        ButtonElement loginOnTheMainPageButton = new ButtonElement(loginOnTheMainPageButtonLocator);
        loginOnTheMainPageButton.clickButton();
    }

    @Step("Отображение кнопки 'Оформить заказ'")
    public boolean isVisibleGetOrderButton() {
        ButtonElement getOrderButton = new ButtonElement(getOrderLocator);
        return getOrderButton.isVisibleButton();
    }
}





