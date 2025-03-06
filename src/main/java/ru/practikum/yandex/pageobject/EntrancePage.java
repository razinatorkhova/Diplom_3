package ru.practikum.yandex.pageobject;

import io.qameta.allure.Step;
import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.InputElement;

public class EntrancePage {

    //кнопка "Зарегистрироваться" авторизации
    private String registrButtonLocator = ".//a[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']";

    // Поля ввода для авторизации
    private String emailLoginInputLocator = ".//div[@class='input pr-6 pl-6 input_type_text input_size_default']//input[@name='name']";
    private String passwordLoginInputLocator = ".//div[@class='input pr-6 pl-6 input_type_password input_size_default']//input[@name='Пароль']";

    //кнопка "войти" на странице авторизации
    private String loginEntranceButtonLocator = ".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Войти')]";

    //кнопка "Восстановить пароль"
    private String resetPasswordButtonLocator = ".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']";

    @Step("Клик по кнопке 'Зарегистрироваться'на странице авторизации")
    public void registrButtonClick() {
        ButtonElement registrButton = new ButtonElement(registrButtonLocator);
        registrButton.clickButton();
    }

    @Step("Клик по кнопке 'Войти'на странице авторизации")
    public void loginEntranceButtonClick() {
        ButtonElement loginEntranceButton = new ButtonElement(loginEntranceButtonLocator);
        loginEntranceButton.clickButton();
    }

    @Step("Заполнение поля email")
    public void setLoginEmailInput(String email) {
        InputElement emailInput = new InputElement(emailLoginInputLocator);
        emailInput.setValue(email);
    }

    @Step("Заполнение поля пароля")
    public void setLoginPasswordInput(String password) {
        InputElement passwordInput = new InputElement(passwordLoginInputLocator);
        passwordInput.setValue(password);
    }

    @Step("Авторизация пользователя")
    public void loginUser(String email, String password) {
        setLoginEmailInput(email);
        setLoginPasswordInput(password);
        loginEntranceButtonClick();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void resetPasswordButtonClick() {
        ButtonElement resetPasswordButton = new ButtonElement(resetPasswordButtonLocator);
        resetPasswordButton.clickButton();
    }
}
