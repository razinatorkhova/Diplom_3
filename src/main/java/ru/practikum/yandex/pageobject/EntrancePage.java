package ru.practikum.yandex.pageobject;

import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.InputElement;

public class EntrancePage {

    //URL страницы авторизации
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //кнопка "Зарегистрироваться" авторизации
    private String registrButtonLocator = ".//a[contains(@class, 'Auth_link__1fOlj') and text()='Зарегистрироваться']";

    // Поля ввода для авторизации
    private String emailLoginInputLocator = ".//div[@class='input pr-6 pl-6 input_type_text input_size_default']//input[@name='name']";
    private String passwordLoginInputLocator = ".//div[@class='input pr-6 pl-6 input_type_password input_size_default']//input[@name='Пароль']";

    //кнопка "войти" на странице авторизации
    private String loginEntranceButtonLocator = ".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Войти')]";

    //Кнопка "Оформить заказ"
    private String getOrderLocator = ".//button[contains(@class, 'button_button_type_primary__1O7Bx') and text()='Оформить заказ']";

    //кнопка "Восстановить пароль"
    private String resetPasswordButtonLocator = ".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']";

    //Метод клика по кновке Зарегистрироваться на странице авторизации
    public void registrButtonClick() {
        ButtonElement registrButton = new ButtonElement(registrButtonLocator);
        registrButton.clickButton();
    }

    //Метод клика по кновке Войти на странице авторизации
    public void loginEntranceButtonClick() {
        ButtonElement loginEntranceButton = new ButtonElement(loginEntranceButtonLocator);
        loginEntranceButton.clickButton();
    }

    // Метод для заполнения поля email
    public void setLoginEmailInput(String email) {
        InputElement emailInput = new InputElement(emailLoginInputLocator);
        emailInput.setValue(email);
    }

    // Метод для заполнения поля пароля
    public void setLoginPasswordInput(String password) {
        InputElement passwordInput = new InputElement(passwordLoginInputLocator);
        passwordInput.setValue(password);
    }

    // Метод для авторизации
    public void loginUser(String email, String password) {
        setLoginEmailInput(email);
        setLoginPasswordInput(password);
        loginEntranceButtonClick();
    }

    //Метод клика по кнопке Восстановить пароль
    public void resetPasswordButtonClick() {
        ButtonElement resetPasswordButton = new ButtonElement(resetPasswordButtonLocator);
        resetPasswordButton.clickButton();
    }
}
