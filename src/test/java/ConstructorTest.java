import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import ru.practikum.yandex.pageobject.MainPage;

@Feature("Сheck tab switching")
public class ConstructorTest extends BaseUITest {

    @DisplayName("checking switching between tabs")
    @Test
    public void testSelectTabs() throws InterruptedException {

        MainPage mainPage = new MainPage();
        mainPage.openMainPage();

        // Кликаем на вкладку "Соусы" используя активный и неактивный локатор
        mainPage.inactiveSauceLinkClick();
        mainPage.activeSauceLinkLocatorClick();

        // Кликаем на вкладку "Начинки" используя активный и неактивный локатор
        mainPage.inactiveFillingLinkClick();
        mainPage.activeFillingLinkClick();

        // Кликаем на вкладку "Булки" используя активный и неактивный локатор
        mainPage.inactiveBunLinkClick();
        mainPage.activeBunLinkClick();
    }
}





