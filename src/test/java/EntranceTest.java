import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.ForgotPasswordPage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static org.junit.Assert.assertTrue;

@Feature("Сhecking login using different buttons ")
public class EntranceTest extends BaseUITest {

    protected String userEmail;
    protected String userPassword;
    MainPage mainPage = new MainPage();
    EntrancePage entrancePage = new EntrancePage();

    @Before
    public void setUp() {

        userEmail = userDataLombok.getEmail();
        userPassword = userDataLombok.getPassword();
    }

    @DisplayName("Check using login button on the main page")
    @Description("This test checks that the user can successfully log into their account by clicking the Log In button on the homepage")
    @Test
    public void checkUsingLoginButtonOnTheMainPageTest() {

        mainPage.loginOnTheMainPageButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
        // Проверка, что кнопка 'Оформить заказ' видна
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }


    @DisplayName("Check login using “Personal Account” button")
    @Description(" This test checks that the user can successfully log into their account by clicking the Personal Account button")
    @Test
    public void checkLoginUsingPersonalAccountButtonTest() {
        mainPage.personalAccountButtonClick();
        entrancePage.loginUser(userEmail, userPassword);
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
        assertTrue("The 'Get Order' button should be visible after login.", mainPage.isVisibleGetOrderButton());
    }
}
