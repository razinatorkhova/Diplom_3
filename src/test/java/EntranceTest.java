import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.model.lombok.UserDataLombok;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.ForgotPasswordPage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static ru.practikum.yandex.model.generator.UserGenerator.getRandomUser;

@Feature("Сhecking login using different buttons ")
public class EntranceTest extends BaseUITest {

    private String userEmail;
    private String userPassword;
    private MainPage mainPage;
    private EntrancePage entrancePage;

    @Before
    public void setUp() {
        UserDataLombok userDataLombok = getRandomUser("Vlad54321", "password54321", "Vlad");
        UserApi userApi = new UserApi();
        ValidatableResponse response = userApi.createUserLombok(userDataLombok);

        userAccessToken = response.extract().path("accessToken");
        userEmail = userDataLombok.getEmail();
        userPassword = userDataLombok.getPassword();

        mainPage = new MainPage();
        entrancePage = new EntrancePage();
        mainPage.openMainPage();
    }

    private void performLogin() {
        entrancePage.loginUser(userEmail, userPassword);
        mainPage.isVisibleGetOrderButton();
    }

    @DisplayName("Check using login button on the main page")
    @Test
    public void checkUsingLoginButtonOnTheMainPageTest() {
        mainPage.loginOnTheMainPageButtonClick();
        performLogin();
    }

    @DisplayName("Check login using “Personal Account” button")
    @Test
    public void checkLoginUsingPersonalAccountButtonTest() {
        mainPage.personalAccountButtonClick();
        performLogin();
    }

    @DisplayName("Check using login button on the registration page")
    @Test
    public void checkUsingLoginButtonOnTheRegistrationPageTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.entranceOnRegistrationPageButtonClick();
        performLogin();
    }

    @DisplayName("Check using login button on the forgot password page")
    @Test
    public void checkUsingLoginButtonOnTheForgotPasswordPageTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.resetPasswordButtonClick();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.loginOnTheForgotPasswordPageButtonClick();
        performLogin();
    }
}
