package ru.practikum.yandex.pageobject;

import io.qameta.allure.Step;
import ru.practikum.yandex.elements.ButtonElement;

public class ForgotPasswordPage {

    //кнопка "войти" на странице восстановления пароля
    private String loginOnTheForgotPasswordPageButtonLocator = ".//a[@class='Auth_link__1fOlj' and text()='Войти']";

    @Step("Клик по кнопке 'Войти' на странице восстановления пароля")
    public void loginOnTheForgotPasswordPageButtonClick() {
        ButtonElement loginOnTheForgotPasswordPageButton = new ButtonElement(loginOnTheForgotPasswordPageButtonLocator);
        loginOnTheForgotPasswordPageButton.clickButton();
    }
}
