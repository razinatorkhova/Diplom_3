import com.codeborne.selenide.Selenide;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static ru.practikum.yandex.model.generator.UserGenerator.getRandomUser;
import static ru.practikum.yandex.pageobject.EntrancePage.LOGIN_PAGE_URL;

@Feature("User registration")
public class RegistrationUserTest extends BaseUITest {

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
    @Test
    public void checkSuccessfulRegistrationTest() {
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        EntrancePage entrancePage = new EntrancePage();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.registerNewUser();
        String expectedUrl = LOGIN_PAGE_URL;
        Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl));
        try {
            Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl));
        } catch (Exception e) {
            throw new AssertionError("Пользователь не зарегистрирован: " + e.getMessage());
        }
       }

    @DisplayName("Get error for incorrect password")
    @Test
    public void GetErrorForIncorrectPasswordTest() {
        String errorMessageLocator = "p.input__error.text_type_main-default";
        String expectedErrorMessage = "Некорректный пароль";

        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        EntrancePage entrancePage = new EntrancePage();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        // Попытка зарегистрировать пользователя с некорректным паролем
        registrationPage.registerNewUserWithIncorrectPassword();
        registrationPage.isErrorDisplayed();
    }}