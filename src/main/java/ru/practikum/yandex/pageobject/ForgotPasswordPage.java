package ru.practikum.yandex.pageobject;

import ru.practikum.yandex.elements.ButtonElement;

public class ForgotPasswordPage {

    //кнопка "войти" на странице восстановления пароля
    private String loginOnTheForgotPasswordPageButtonLocator = ".//a[@class='Auth_link__1fOlj' and text()='Войти']";

    public void loginOnTheForgotPasswordPageButtonClick() {
        ButtonElement loginOnTheForgotPasswordPageButton = new ButtonElement(loginOnTheForgotPasswordPageButtonLocator);
        loginOnTheForgotPasswordPageButton.clickButton();
    }
}
