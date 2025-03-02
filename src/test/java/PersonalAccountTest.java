import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.model.lombok.UserDataLombok;
import ru.practikum.yandex.pageobject.EntrancePage;
import ru.practikum.yandex.pageobject.MainPage;
import ru.practikum.yandex.pageobject.PersonalAccountPage;
import static org.junit.Assert.assertTrue;
import static ru.practikum.yandex.model.generator.UserGenerator.getRandomUser;
import static ru.practikum.yandex.pageobject.EntrancePage.LOGIN_PAGE_URL;
import static ru.practikum.yandex.pageobject.MainPage.MAIN_PAGE_URL;
import static ru.practikum.yandex.pageobject.PersonalAccountPage.PERSONAL_ACCOUNT_PAGE_URL;

@Feature("Check personal account")
public class PersonalAccountTest extends BaseUITest {

    private String userEmail;
    private String userPassword;

    @Before
    public void setUp() {
        UserDataLombok userDataLombok = getRandomUser("Vlad54321", "password54321", "Vlad");
        new UserApi().createUserLombok(userDataLombok); // Создание пользователя
        userEmail = userDataLombok.getEmail();
        userPassword = userDataLombok.getPassword();
        new MainPage().openMainPage();
    }

    private void loginUser() {
        new MainPage().personalAccountButtonClick();
        new EntrancePage().loginUser(userEmail, userPassword);
        new MainPage().isVisibleGetOrderButton();
    }

    private void assertCurrentUrl(String expectedUrl) {
        assertTrue("Не удалось перейти на ожидаемую страницу",
                Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().equals(expectedUrl)));
    }

    @DisplayName("Check open personal account")
    @Test
    public void checkOpenPersonalAccountTest() {
        loginUser();
        new MainPage().personalAccountButtonClick();
        assertCurrentUrl(PERSONAL_ACCOUNT_PAGE_URL);
    }

    @DisplayName("Check open constructor from personal account page")
    @Test
    public void checkOpenConstructorFromPersonalAccountPageTest() {
        loginUser();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();

        personalAccountPage.constructorLinkClick();
        assertCurrentUrl(MAIN_PAGE_URL);

        personalAccountPage.stellarBurgersLinkClick();
        assertCurrentUrl(MAIN_PAGE_URL);
    }

    @DisplayName("Check exit from personal account")
    @Test
    public void checkExitFromPersonalAccountTest() {
        loginUser();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        MainPage mainPage = new MainPage();
        mainPage.personalAccountButtonClick();
        personalAccountPage.exitFromPersonalAccountButtonClick();
        assertCurrentUrl(LOGIN_PAGE_URL);
    }
}
