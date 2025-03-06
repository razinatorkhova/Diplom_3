import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.model.lombok.UserDataLombok;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.ForgotPasswordPage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;
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

    @DisplayName("Check using login button on the main page")
    @Description("This test checks that the user can successfully log into their account by clicking the Log In button on the homepage")
    @Test
    public void checkUsingLoginButtonOnTheMainPageTest() {
        mainPage.loginOnTheMainPageButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
        $(By.xpath(mainPage.getOrderLocator)).shouldBe(visible);
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }

    @DisplayName("Check login using “Personal Account” button")
    @Description(" This test checks that the user can successfully log into their account by clicking the Personal Account button")
    @Test
    public void checkLoginUsingPersonalAccountButtonTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
        $(By.xpath(mainPage.getOrderLocator)).shouldBe(visible);
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }

    @DisplayName("Check using login button on the registration page")
    @Description("This test checks that the user can successfully log into their account by clicking the login button on the registration page after navigating through the Personal Account button")
    @Test
    public void checkUsingLoginButtonOnTheRegistrationPageTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.registrButtonClick();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.entranceOnRegistrationPageButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
        $(By.xpath(mainPage.getOrderLocator)).shouldBe(visible);
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }

    @DisplayName("Check using login button on the forgot password page")
    @Description("This test checks that the user can successfully log into their account by clicking the login button on the password recovery page after navigating through the Personal Account button")
    @Test
    public void checkUsingLoginButtonOnTheForgotPasswordPageTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.resetPasswordButtonClick();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.loginOnTheForgotPasswordPageButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
        $(By.xpath(mainPage.getOrderLocator)).shouldBe(visible);
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }
}
