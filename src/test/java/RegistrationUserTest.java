import com.codeborne.selenide.Selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.api.Endpoints;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static ru.practikum.yandex.model.generator.UserGenerator.getRandomUser;

@Feature("User registration")
public class RegistrationUserTest extends BaseUITest {
    String errorMessageLocator = "p.input__error.text_type_main-default"; // Локатор для сообщения об ошибке
    String expectedErrorMessage = "Некорректный пароль";

    @Before
    public void createUser() {
        userDataLombok = getRandomUser("Vlad54321", "password54321", "Vlad");
        UserApi userApi = new UserApi();
        ValidatableResponse response = userApi.createUserLombok(userDataLombok);

        userAccessToken = response.extract().path("accessToken");

        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
    }

    @DisplayName("Check successful registration")
    @Description("This test verifies that a user can successfully register an account with valid credentials.")
    @Test
    public void checkSuccessfulRegistrationTest() {
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        EntrancePage entrancePage = new EntrancePage();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.registerNewUser();
        String expectedUrl = Endpoints.LOGIN_PAGE_URL;
        Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl));
        try {
            Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl));
        } catch (Exception e) {
            throw new AssertionError("Пользователь не зарегистрирован: " + e.getMessage());
        }
    }

    @DisplayName("Get error for incorrect password")
    @Description("This test checks that an appropriate error message is displayed when attempting to register with an incorrect password that does not meet the minimum requirement of six characters.")
    @Test
    public void GetErrorForIncorrectPasswordTest() {

        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        EntrancePage entrancePage = new EntrancePage();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        // Попытка зарегистрировать пользователя с некорректным паролем
        registrationPage.registerNewUserWithIncorrectPassword();
        // Ожидание появления сообщения об ошибке и извлечение текста сообщения
        String actualErrorMessage = Selenide.$(errorMessageLocator).getText();
        // Проверка, что фактическое сообщение об ошибке соответствует ожидаемому
        assertEquals("Ошибка: Сообщение об ошибке не совпадает с ожидаемым", expectedErrorMessage, actualErrorMessage);
    }
}