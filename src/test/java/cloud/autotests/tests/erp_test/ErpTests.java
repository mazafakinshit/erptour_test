package cloud.autotests.tests.erp_test;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Random;

import static cloud.autotests.utils.RandomUtils.getRandomInt;
import static cloud.autotests.utils.RandomUtils.getRandomMessage;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
//import static helpers.Environment.*;


@Epic("QA.GURU automation course")
@Story("Erp-info tests")
@Tag("erptest")
public class ErpTests extends TestBase {

    @Test
    @DisplayName("Вход в учетную запись на портале")
    @Description("Успешный вход в учетную запись на портале")
    void SuccessfulSignIn() {
        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем форму авторизации", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
        });
        step("Успешно авторизуемся на портале", () -> {
            $("html").shouldHave(text("Абрикосов Александр"));
        });

    }

    @Test
    @DisplayName("Вход в учетную запись на портале")
    @Description("Неудачный вход в учетную запись на портале")
    void UnSuccessfulSignIn() {
        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем форму авторизации", () -> {
            $("#loginform-username").setValue("dont");
            $("#loginform-password").setValue("demo866").pressEnter();
        });
        step("Сообщение о неудачной авторизации на портале", () -> {
            $("html").shouldHave(text("Неправильный логин или пароль"));
        });

    }

    @Test
    @DisplayName("Создание тура в системе")
    @Description("Успешное создание тура в системе")
    void SuccessfulTourCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Наименование", () -> {
            $(".sidebar-toggle").click();
            $(byText("Туры")).click();
            $(byText("Добавить")).click();
            $("#tour-name").setValue("автобусный тур в Анапу" + randomInt);
        });
        step("Дата отправки", () -> {
            $("#tour-date_send").setValue("31-12-2020 19:00:00").pressEnter();
        });
        step("Дата возврата", () -> {
            $("#tour-date_return").setValue("08-01-2021 20:00:00").click();
        });
        step("Описание", () -> {
            $("#tour-description").setValue("Тур в Анапу. Проезд, проживание, питание.");
        });
        step("Варианты размещения", () -> {
            $(".select2-search").click();
            $(".select2-results").click();
        });
        step("Автобус", () -> {
            $("#tour-bus_id").scrollTo().click();
            $(byText("Большой автобус - Мест: 45 (017)")).click();
        });
        step("Выбираем точки маршрута", () -> {
            $x("//*[@id=\"w0\"]/div[3]/div[1]/div[2]/div[3]/span[2]/span[1]/span/ul/li/input").setValue("Анапа").pressEnter();
        });
        step("Наценка проживание", () -> {
            $("#tour-accommodation_surcharge_id").click();
            $(byText("наценка за тихих соседей (300.00)")).click();
        });
        step("Наценка проезд", () -> {
            $("#tour-fare_surcharge_id").click();
            $(byText("Наценка за автобус, который едет сам (100000000.00)")).click();
        });
        step("Агентская комиссия", () -> {
            $("#tour-agency_commission_id").click();
            $(byText("комиссия (1500.00)")).click();
        });
        step("Цена аренды автобуса", () -> {
            $("#tour-price_bus").setValue("30000");
        });
        step("Цена трансфера водителя", () -> {
            $("#tour-price_transfer_driver").setValue("3000");
        });
        step("Трансфер сопровождающего", () -> {
            $("#tour-price_transfer_accompanying").setValue("1000");
        });
        step("Расходы на сопровождающего", () -> {
            $("#tour-expenditure_accompanying").setValue("1000");
        });
        step("Расходы на экскурсовода", () -> {
            $("#tour-expenditure_guide").setValue("1500");
        });
        step("Себестоимость проезда взрослый", () -> {
            $("#tour-expenditure_guide").setValue("1500");
        });
        step("Себестоимость проезда ребенок до трёх лет", () -> {
            $("#tour-fare_child_1").setValue("0");
        });
        step("Себестоимость проезда ребенок от трёх до двенадцати лет", () -> {
            $("#tour-fare_child_2").setValue("1500");
        });
        step("Указываем ссылку на страницу тура", () -> {
            $("#tour-link").setValue("www.tourpage.com");
        });
        step("Выбираем статус", () -> {
            $("#tour-status").click();
            $(byText("Новый")).click();
        });
        step("Нажимаем кнопку Сохранить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление тура в базу", () -> {
            $("html").shouldHave(text("Тур в Анапу. Проезд, проживание, питание."));
        });

    }

}