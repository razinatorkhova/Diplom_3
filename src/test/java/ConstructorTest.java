import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.practikum.yandex.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

@Feature("Сheck tab switching")
public class ConstructorTest extends BaseUITest {
    //локатор неактивной и активной вкладки Соусы
    private String inactiveSauceLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]";
    private String activeSauceLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Соусы']]";
    //локатор неактивной и активной вкладки Начинки
    private String inactiveFillingLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]";
    private String activeFillingLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Начинки']]";
    //локатор активной вкладки Булки
    private String inactiveBunLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]";
    private String activeBunLinkLocator = ".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect') and .//span[text()='Булки']]";

    @DisplayName("Checking the transition to the sauce tab")
    @Description("This test checks that clicking on the 'Sauces' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheSauceTab() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();

        // Кликаем на вкладку "Соусы"
        $(By.xpath(inactiveSauceLinkLocator)).click();

        // Ожидаем, пока класс не изменится на активный (содержит tab_tab_type_current__2BEPc class)
        $(By.xpath(activeSauceLinkLocator)).shouldBe(Condition.visible);

        // Проверяем, что вкладка "Соусы" активна
        assertTrue($(By.xpath(activeSauceLinkLocator)).isDisplayed());
    }

    @DisplayName("Checking the transition to the filling tab")
    @Description("This test checks that clicking on the 'Filling' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheFillingTab() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();

        // Кликаем на вкладку "Начинки"
        $(By.xpath(inactiveFillingLinkLocator)).click();

        // Ожидаем, пока класс не изменится на активный (содержит tab_tab_type_current__2BEPc class)
        $(By.xpath(activeFillingLinkLocator)).shouldBe(Condition.visible);

        // Проверяем, что вкладка "Начинки" активна
        assertTrue($(By.xpath(activeFillingLinkLocator)).isDisplayed());
    }

    @DisplayName("Checking the transition to the bun tab")
    @Description("This test checks that clicking on the 'Bun' tab correctly activates the tab and displays it as active.")
    @Test
    public void checkingTheTransitionToTheBunTab() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();

        // Кликаем на вкладку "Соусы"
        $(By.xpath(inactiveSauceLinkLocator)).click();

        // Кликаем на вкладку "Булки"
        $(By.xpath(inactiveBunLinkLocator)).click();

        /// Ожидаем, пока класс не изменится на активный (содержит tab_tab_type_current__2BEPc class)
        $(By.xpath(activeBunLinkLocator)).shouldBe(Condition.visible);

        // Проверяем, что вкладка "Булки" активна
        assertTrue($(By.xpath(activeBunLinkLocator)).isDisplayed());
    }
}
