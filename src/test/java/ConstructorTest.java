import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.yandex.pageobject.MainPage;

import static org.junit.Assert.assertTrue;

@Feature("Сheck tab switching")
public class ConstructorTest extends BaseUITest {

    MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        mainPage.openMainPage();
    }

    @DisplayName("Checking the transition to the sauce tab")
    @Description("This test checks that clicking on the 'Sauces' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheSauceTab() {

        // Кликаем на вкладку "Соусы"
        mainPage.saucesTabClick();

        // Ожидаем, пока класс изменится на активный (содержит tab_tab_type_current__2BEPc class)
        mainPage.saucesTabVisible();

        // Проверяем, что вкладка "Соусы" активна
        assertTrue("Вкладка 'Соусы' не активирована", mainPage.actualSaucesLink.isDisplayed());
    }

    @DisplayName("Checking the transition to the filling tab")
    @Description("This test checks that clicking on the 'Filling' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheFillingTab() {

        mainPage.fillingsTabClick();
        mainPage.fillingsTabVisible();
        assertTrue("Вкладка 'Соусы' не активирована", mainPage.actualFillingsLink.isDisplayed());
    }

    @DisplayName("Checking the transition to the bun tab")
    @Description("This test checks that clicking on the 'Bun' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheBunTab() {

        mainPage.saucesTabClick();
        mainPage.bunsTabClick();
        mainPage.bunsTabVisible();
        assertTrue("Вкладка 'Соусы' не активирована", mainPage.actualBunsLink.isDisplayed());
    }
}
