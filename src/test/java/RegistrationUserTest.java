import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.practikum.yandex.api.Endpoints;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static org.junit.Assert.assertEquals;

@Feature("User registration")
public class RegistrationUserTest extends BaseUITest {

    private String expectedErrorMessage = "Некорректный пароль";

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
        registrationPage.waitForExpectedUrl(expectedUrl);
        String actualUrl = WebDriverRunner.url();

        assertEquals("Пользователь не зарегистрирован", expectedUrl, actualUrl);
    }

    @DisplayName("Get error for incorrect password")
    @Description("This test checks that an appropriate error message is displayed when attempting to register with an incorrect password that does not meet the minimum requirement of six characters.")
    @Test
    public void getErrorForIncorrectPasswordTest() {

        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        EntrancePage entrancePage = new EntrancePage();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        // Попытка зарегистрировать пользователя с некорректным паролем
        registrationPage.registerNewUserWithIncorrectPassword();
        // Ожидание появления сообщения об ошибке и извлечение текста сообщения
        String actualErrorMessage = registrationPage.waitAndGetErrorMessage();

        // Проверка, что фактическое сообщение об ошибке соответствует ожидаемому
        assertEquals("Ошибка: Сообщение об ошибке не совпадает с ожидаемым", expectedErrorMessage, actualErrorMessage);
    }
}