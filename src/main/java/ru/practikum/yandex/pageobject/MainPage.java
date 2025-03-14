package ru.practikum.yandex.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.practikum.yandex.api.Endpoints;
import ru.practikum.yandex.elements.ButtonElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    // Локатор кнопки "Личный Кабинет"
    private String personalAccountButtonLocator = ".//p[contains(text(), 'Личный Кабинет')]";

    // Локатор кнопки "Войти в аккаунт" на главной странице
    private String loginOnTheMainPageButtonLocator = ".//button[text()='Войти в аккаунт']";

    // Локатор кнопки "Оформить заказ"
    public String getOrderLocator = ".//button[contains(@class, 'button_button_type_primary__1O7Bx') and text()='Оформить заказ']";

    // URL главной страницы
    public void openMainPage() {
        open(Endpoints.MAIN_PAGE_URL);
    }

    // Локаторы вкладок (Соусы, Начинки, Булки)
    private String inactiveSauceLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]";
    private String activeSauceLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Соусы']]";
    private String inactiveFillingLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]";
    private String activeFillingLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Начинки']]";
    private String inactiveBunLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]";
    private String activeBunLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Булки']]";

    public SelenideElement actualSaucesLink = $(By.xpath(activeSauceLinkLocator));
    public SelenideElement actualFillingsLink = $(By.xpath(activeFillingLinkLocator));
    public SelenideElement actualBunsLink = $(By.xpath(activeBunLinkLocator));

    @Step("Клик по вкладку 'Соусы'")
    public void saucesTabClick() {
        ButtonElement saucesTabClick = new ButtonElement(inactiveSauceLinkLocator);
        saucesTabClick.clickButton();
    }

    @Step("Ожидаем, пока класс 'Соусы' не изменится на активный")
    public void saucesTabVisible() {
        actualSaucesLink.shouldBe(Condition.visible);
    }

    @Step("Клик на вкладку 'Начинки'")
    public void fillingsTabClick() {
        ButtonElement fillingsTabClick = new ButtonElement(inactiveFillingLinkLocator);
        fillingsTabClick.clickButton();
    }

    @Step("Ожидаем, пока класс 'Начинки' не изменится на активный")
    public void fillingsTabVisible() {
        actualFillingsLink.shouldBe(Condition.visible);
    }

    @Step("Клик на вкладку 'Булки'")
    public void bunsTabClick() {
        ButtonElement bunsTabClick = new ButtonElement(inactiveBunLinkLocator);
        bunsTabClick.clickButton();
    }

    @Step("Ожидаем, пока класс 'Булки' не изменится на активный")
    public void bunsTabVisible() {
        actualBunsLink.shouldBe(Condition.visible);
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
        $(By.xpath(getOrderLocator)).shouldBe(Condition.visible);
        return $(By.xpath(getOrderLocator)).isDisplayed();
    }

    @Step("Вход в 'Личный кабинет'")
    public void entranceToPersonalAccount(String email, String password, EntrancePage entrancePage) {
        personalAccountButtonClick();
        entrancePage.loginUser(email, password);
        isVisibleGetOrderButton();
        personalAccountButtonClick();
    }
}