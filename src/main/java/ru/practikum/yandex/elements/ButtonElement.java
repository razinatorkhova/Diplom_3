package ru.practikum.yandex.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ButtonElement {
    SelenideElement buttonElement;

    public ButtonElement(String locator) {
        buttonElement = $(new By.ByXPath(locator));
    }

    public void clickButton() {
        buttonElement.shouldBe(enabled);
        buttonElement.scrollIntoView(true);
        buttonElement.click();
    }

    public boolean isVisibleButton() {
        return buttonElement.is(visible);
    }
}