package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;


public class DigitalleagueRuTests extends TestBase {
    final String baseUrl = "https://www.digitalleague.ru";
    final String contactUrl = "https://www.digitalleague.ru/contact";
    final String userName = "Евгений";
    final String userPhone = "9031112233";
    final String message = "Тут какое то Баг сообщение";

    @Test
    @DisplayName("Открытие страницы Главная")
    @Feature("Главная страница")
    @Tags({@Tag("Web"), @Tag("regression")})
    @Story("User should see main page")
    @Link(url = baseUrl, name = "Главная страница")
    @Owner("telepnev")
    @Severity(SeverityLevel.CRITICAL)
    public void openMainPageTest() {
        parameter("Task number", 1101);

        step("Открытие Главной страницы", () ->
                open(baseUrl));
        step("Проверка что нужная страница открылась", () ->
                $("html")
                        .shouldHave(text("Лига Цифровой Экономики")));
    }

    @Test
    @DisplayName("Открытие страницы Контактов")
    @Feature("Issues")
    @Tags({@Tag("Web"), @Tag("regression")})
    @Story("User should see contact page")
    @Link(url = contactUrl, name = "Контакты")
    @Owner("telepnev")
    @Severity(SeverityLevel.CRITICAL)
    public void openContactMainPageTest() {
        parameter("Task number", 1102);

        step("Открытие страницы Контактов", () ->
                open(contactUrl));
        step("Проверка что нужная страница открылась", () ->
                $("h1").shouldHave(text("Мы рады новым знакомствам")));
    }

    @Test
    @DisplayName("Отправка обратной связи")
    @Feature("Свяжитесь с нами")
    @Tags({@Tag("Web"), @Tag("Regression")})
    @Story("User should send message")
    @Link(url = contactUrl, name = "Контакты")
    @Owner("telepnev")
    @Severity(SeverityLevel.CRITICAL)
    public void messageSendingFormTest() {
        parameter("Task number", 1102);

        step("Открытие страницы Контактов", () ->
                open(contactUrl));
        $("#request").shouldBe(Condition.exist);
        step("Заполняем поле Имя", () ->
                $("#name").val(userName).pressTab());
        step("Заполняем поле Телефон или почта", () ->
                $("#contacts").val(userPhone).pressTab());
        step("Заполняем поле Сообщение", () ->
                $("#message").val(message));
        step("Отправить сообщение", () ->
                $(".form__btn").click());
        step("Проверка что сообщение отправленно", () ->
                $("div").shouldHave(text("Ваше сообщение отправлено."), Duration.ofSeconds(10)));
    }
}

