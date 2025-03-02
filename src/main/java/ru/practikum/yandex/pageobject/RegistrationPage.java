package ru.practikum.yandex.pageobject;

import ru.practikum.yandex.elements.ButtonElement;
import ru.practikum.yandex.elements.InputElement;
import ru.practikum.yandex.model.generator.UserGenerator;
import ru.practikum.yandex.model.lombok.UserDataLombok;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // Поля ввода для регистрации
    private String nameInputLocator = ".//div[label[text()='Имя']]/input[@name='name']";
    private String emailInputLocator = ".//div[label[text()='Email']]/input[@name='name']";
    private String passwordInputLocator = ".//div[label[text()='Пароль']]/input[@name='Пароль']";
    private String registrRegistrationButtonLocator = ".//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']";
    private String entranceOnRegistrationPageButtonLocator = ".//*[@class='Auth_link__1fOlj' and text()='Войти']";

    // Метод для заполнения поля имени
    public void setNameInput(String name) {
        InputElement nameInput = new InputElement(nameInputLocator);
        nameInput.setValue(name);
    }

    // Метод для заполнения поля email
    public void setEmailInput(String email) {
        InputElement emailInput = new InputElement(emailInputLocator);
        emailInput.setValue(email);
    }

    // Метод для заполнения поля пароля
    public void setPasswordInput(String password) {
        InputElement passwordInput = new InputElement(passwordInputLocator);
        passwordInput.setValue(password);
    }

    // Метод для заполнения поля некорректным паролем
    public void setIncorrectPasswordInput() {
        setPasswordInput("zzzz");
    }

    // Метод для нажатия кнопки регистрации
    public void entranceOnRegistrationPageButtonClick() {
        ButtonElement entranceOnRegistrationPageButton = new ButtonElement(entranceOnRegistrationPageButtonLocator);
        entranceOnRegistrationPageButton.clickButton();
    }

    // Метод для нажатия кнопки регистрации
    public void registrRegistrationButtonClick() {
        ButtonElement registrRegistrationButton = new ButtonElement(registrRegistrationButtonLocator);
        registrRegistrationButton.clickButton();
    }

    // Метод для регистрации нового пользователя
    public void registerNewUser() {
        UserDataLombok user = UserGenerator.getRandomUser();
        setNameInput(user.getName());
        setEmailInput(user.getEmail());
        setPasswordInput(user.getPassword());
        registrRegistrationButtonClick();
    }

    // Метод для регистрации нового пользователя с Некорректным Паролем
    public void registerNewUserWithIncorrectPassword() {
        UserDataLombok user = UserGenerator.getRandomUser();
        setNameInput(user.getName());
        setEmailInput(user.getEmail());
        setIncorrectPasswordInput();
        registrRegistrationButtonClick();
    }

    // Проверка отображения сообщения об ошибке
    public boolean isErrorDisplayed() {
        String errorMessageLocator = "p.input__error.text_type_main-default";
        String expectedErrorMessage = "Некорректный пароль";
        // Получаем текст сообщения об ошибке
        String actualErrorMessage = $(errorMessageLocator).getText();
        // Проверяем, отображается ли сообщение и соответствует ли оно ожидаемому
        return !actualErrorMessage.isEmpty() && actualErrorMessage.contains(expectedErrorMessage);
    }
}
   

