package ru.practikum.yandex.pageobject;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.InputElement;
import ru.practikum.yandex.model.generator.UserGenerator;
import ru.practikum.yandex.model.lombok.UserDataLombok;

public class RegistrationPage {

    // Поля ввода для регистрации
    private String nameInputLocator = ".//div[label[text()='Имя']]/input[@name='name']";
    private String emailInputLocator = ".//div[label[text()='Email']]/input[@name='name']";
    private String passwordInputLocator = ".//div[label[text()='Пароль']]/input[@name='Пароль']";
    private String registrRegistrationButtonLocator = ".//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']";
    private String entranceOnRegistrationPageButtonLocator = ".//*[@class='Auth_link__1fOlj' and text()='Войти']";
    // Локатор для сообщения об ошибке
    private String errorMessageLocator = "p.input__error.text_type_main-default";

    @Step("Заполнение поля Имя")
    public void setNameInput(String name) {
        InputElement nameInput = new InputElement(nameInputLocator);
        nameInput.setValue(name);
    }

    @Step("Заполнение поля email")
    public void setEmailInput(String email) {
        InputElement emailInput = new InputElement(emailInputLocator);
        emailInput.setValue(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPasswordInput(String password) {
        InputElement passwordInput = new InputElement(passwordInputLocator);
        passwordInput.setValue(password);
    }

    @Step("Заполнение поля Пароль некорректным значением")
    public void setIncorrectPasswordInput() {
        setPasswordInput("zzzz");
    }

    @Step("Клик по кнопке входа на странице регистрации")
    public void entranceOnRegistrationPageButtonClick() {
        ButtonElement entranceOnRegistrationPageButton = new ButtonElement(entranceOnRegistrationPageButtonLocator);
        entranceOnRegistrationPageButton.clickButton();
    }

    @Step("Клик по кнопке регистрации")
    public void registrRegistrationButtonClick() {
        ButtonElement registrRegistrationButton = new ButtonElement(registrRegistrationButtonLocator);
        registrRegistrationButton.clickButton();
    }

    @Step("Регистрация нового пользователя")
    public void registerNewUser() {
        UserDataLombok user = UserGenerator.getRandomUser();
        setNameInput(user.getName());
        setEmailInput(user.getEmail());
        setPasswordInput(user.getPassword());
        registrRegistrationButtonClick();
    }

    @Step("Регистрация нового пользователя с некорректным полем Пароль")
    public void registerNewUserWithIncorrectPassword() {
        UserDataLombok user = UserGenerator.getRandomUser();
        setNameInput(user.getName());
        setEmailInput(user.getEmail());
        setIncorrectPasswordInput();
        registrRegistrationButtonClick();
    }

    @Step("Ожидание появления сообщения об ошибке 'Некорректный пароль' и извлечение текста сообщения")
    public String waitAndGetErrorMessage() {

        return Selenide.$(errorMessageLocator).getText();
    }

    @Step("Метод для ожидания перехода на ожидаемый URL")
    public void waitForExpectedUrl(String expectedUrl) {

        Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl));
    }
}

   

