import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.api.Endpoints;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.PersonalAccountPage;
import ru.practikum.yandex.pageobject.RegistrationPage;

import static org.junit.Assert.assertEquals;

@Feature("Check personal account")
public class PersonalAccountTest extends BaseUITest {
    protected String userEmail;
    protected String userPassword;
    RegistrationPage registrationPage = new RegistrationPage();
    MainPage mainPage = new MainPage();
    EntrancePage entrancePage = new EntrancePage();

    @Before
    public void setUp() {

        userEmail = userDataLombok.getEmail();
        userPassword = userDataLombok.getPassword();
    }


    @DisplayName("Check open personal account")
    @Description("Verify the transition to the personal account by clicking on the 'Personal Account' button.")
    @Test
    public void checkOpenPersonalAccountTest() {

        mainPage.entranceToPersonalAccount(userEmail, userPassword, entrancePage);
        String expectedUrl = Endpoints.PERSONAL_ACCOUNT_PAGE_URL;
        registrationPage.waitForExpectedUrl(expectedUrl);
        String actualUrl = WebDriverRunner.url();

        assertEquals("'Личный кабинет' не отобразился", expectedUrl, actualUrl);
    }

    @DisplayName("Check open constructor from personal account page by clicking on “Constructor")
    @Description("Verify the transition from the personal account page to the constructor by clicking on the 'Constructor' link")
    @Test
    public void checkOpenConstructorFromPersonalAccountPageTestByConstructorLink() {
        mainPage.entranceToPersonalAccount(userEmail, userPassword, entrancePage);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        personalAccountPage.constructorLinkClick();
        String expectedUrl = Endpoints.MAIN_PAGE_URL;
        registrationPage.waitForExpectedUrl(expectedUrl);
        String actualUrl = WebDriverRunner.url();

        assertEquals("Переход по клику 'Конструктор' не произведен", expectedUrl, actualUrl);
    }

    @DisplayName("Check open stellar burgers from personal account page by clicking on “Stellar Burgers")
    @Description("Verify the transition from the personal account page to the constructor by clicking on the logo 'Stellar Burgers'")
    @Test
    public void checkOpenStellarBurgersFromPersonalAccountPageTestByStellarBurgers() {
        mainPage.entranceToPersonalAccount(userEmail, userPassword, entrancePage);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        personalAccountPage.stellarBurgersLinkClick();
        String expectedUrl = Endpoints.MAIN_PAGE_URL;
        registrationPage.waitForExpectedUrl(expectedUrl);
        String actualUrl = WebDriverRunner.url();
        assertEquals("Переход по клику 'Stellar Burgers' не произведен", expectedUrl, actualUrl);
    }

    @DisplayName("Check exit from personal account")
    @Description("Verify the logout functionality by clicking on the 'Logout' button in the personal account.")
    @Test
    public void checkExitFromPersonalAccountTest() {
        mainPage.entranceToPersonalAccount(userEmail, userPassword, entrancePage);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        personalAccountPage.exitFromPersonalAccountButtonClick();
        String expectedUrl = Endpoints.LOGIN_PAGE_URL;
        registrationPage.waitForExpectedUrl(expectedUrl);
        String actualUrl = WebDriverRunner.url();
        assertEquals("Выход из 'Личного кабинета' не произведен", expectedUrl, actualUrl);
    }
}
