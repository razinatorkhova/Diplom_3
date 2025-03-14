import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import com.codeborne.selenide.Configuration;
import ru.practikum.yandex.api.UserApi;
import ru.practikum.yandex.model.lombok.UserDataLombok;
import ru.practikum.yandex.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ru.practikum.yandex.browser.Browser.initDriver;
import static ru.practikum.yandex.model.generator.UserGenerator.getRandomUser;

public class BaseUITest {
    protected String userAccessToken;
    protected UserDataLombok userDataLombok;

    @Before
    public void startUp() throws IOException {
        initDriver();
        Configuration.timeout = 4000;
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        createUser();
    }

    public void createUser() {
        userDataLombok = getRandomUser("Vlad54321", "password54321", "Vlad");
        UserApi userApi = new UserApi();
        ValidatableResponse response = userApi.createUserLombok(userDataLombok);

        userAccessToken = response.extract().path("accessToken");

    }

    @After
    public void tearDown() {
        // Удаляем пользователя после теста
        cleanUp();
        closeWebDriver();
    }

    public void cleanUp() {
        if (userAccessToken != null) {
            UserApi userApi = new UserApi();
            userApi.deleteUser(userAccessToken);
        }
    }
}
