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


    @Test
    @DisplayName("Создание автобуса в системе")
    @Description("Успешное добавление моделей автобусов в базу данных")
    void SuccessfulBusCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем модель автобуса", () -> {
            $(".sidebar-toggle").click();
            $(byText("Транспорт")).click();
            $(byText("Модели")).click();
            $(byLinkText("Добавить")).click();
            $("html").shouldHave(text("Модель автобуса"));
        });
        step("Заполняем марку автобуса", () -> {
            $("#busmodel-mark").setValue("Ford");
        });
        step("Заполняем модель автобуса", () -> {
            $("#busmodel-model").setValue("Tourneo " + randomInt);
        });
        step("Выбираем тип расположения сидений", () -> {
            $("#busmodel-type_seat").click();
            $(byText("2 - 1")).click();
        });
        step("Задаем количество мест", () -> {
            $("#busmodel-number_seats").setValue("8");
        });
        step("Задаем количество мест заднего ряда", () -> {
            $("#busmodel-number_back_seats").click();
            $(byText("3")).click();
        });
        step("Задаем начальный номер", () -> {
            $("#busmodel-start_number_seats").setValue("21");
        });
        step("Оставляем комментарий", () -> {
            $("#busmodel-comment").setValue("Ford Tourneo - прекрасный выбор для взыскательных господ " + randomInt);
        });
        step("Нажимаем на кнопку Добавить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление автобуса в базу", () -> {
            $("html").shouldHave(text("Ford / Tourneo"));
        });

    }

    @Test
    @DisplayName("Создание варианта размещения в системе")
    @Description("Успешное добавление вариантов размещения в базу данных")
    void SuccessfulAccomodationCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем вариант размещения", () -> {
            $(".sidebar-toggle").click();
            $(byText("Места размещения")).click();
            $(byLinkText("Добавить")).click();
            $("html").shouldHave(text("Вариант размещения"));
        });
        step("Вводим название отеля", () -> {
            $("#accommodation-name").setValue("Анапа " + randomMessage);
        });
        step("Вводим название номера", () -> {
            $("#accommodation-name_room").setValue(randomMessage);
        });
        step("Вводим себестоимость проживания в сутки", () -> {
            $("#accommodation-price").setValue("1000");
        });
        step("Вводим себестоимость питания в сутки", () -> {
            $("#accommodation-food_price").setValue("350");
        });
        step("Оставляем комментарий", () -> {
            $("#accommodation-comment").setValue("Все номера оборудованы кондиционером и прочими благами");
        });
        step("Нажимаем кнопку Добавить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление варианта размещения в базу", () -> {
            $("html").shouldHave(text("Варианты размещения"));
        });
    }

    @Test
    @DisplayName("Создание точек маршрута в системе")
    @Description("Успешное добавление точек маршрута в базу данных")
    void SuccessfulMapMarkerCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем точку маршрута", () -> {
            $(".sidebar-toggle").click();
            $(byText("Точки маршрута")).click();
            $(byLinkText("Добавить")).click();
            $("html").shouldHave(text("Точка маршрута"));
        });
        step("Проводим поиск геоданных", () -> {
            $("#address").setValue("Анапа").pressEnter();
            $(byText("Анапа 353444, Anapa, Russia")).click();
        });
        step("Оставляем комментарий", () -> {
            $("#location-comment").setValue("Очередной шикарный курорт в нашем списке");
        });
        step("Нажимаем кнопку Добавить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление точки маршрута в базу", () -> {
            $("html").shouldHave(text("Точки маршрута"));
        });
    }

    @Test
    @DisplayName("Создание комиссий в системе")
    @Description("Успешное добавление агентских комиссий в базу данных")
    void SuccessfulCommissionCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем Агентскую комиссию", () -> {
            $(".sidebar-toggle").click();
            $(byText("Агентские комиссии")).click();
            $(byLinkText("Добавить")).click();
            $("html").shouldHave(text("Агентская комиссия"));
        });
        step("Вводим название комиссии", () -> {
            $("#commission-name").setValue("Комиссия " + randomInt);
        });
        step("Вводим сумму комиссии", () -> {
            $("#commission-sum").setValue(" " + randomInt);
        });
        step("Выбираем тип комиссии", () -> {
            $("#commission-type").click();
            $(byText("Агенская комиссия")).click();
        });
        step("Оставляем комментарий", () -> {
            $("#commission-comment").setValue("Первая в мире рандомная комиссия");
        });
        step("Нажимаем кнопку Добавить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление агентской комиссии в базу", () -> {
            $("html").shouldHave(text("Агентские коммиссии"));
        });
    }

    @Test
    @DisplayName("Создание наценок в системе")
    @Description("Успешное добавление наценок в базу данных")
    void SuccessfulSurchargeCreation() {

        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем наценку", () -> {
            $(".sidebar-toggle").click();
            $(byText("Наценки")).click();
            $(byLinkText("Добавить")).click();
            $("html").shouldHave(text("Вариант наценки"));
        });
        step("Вводим название наценки", () -> {
            $("#surcharge-name").setValue("Наценка " + randomInt);
        });
        step("Вводим сумму наценки", () -> {
            $("#surcharge-sum").setValue(" " + randomInt);
        });
        step("Выбираем тип наценки", () -> {
            $("#surcharge-type").click();
            $(byText("Проживание (+ к проживанию/день)")).click();
        });
        step("Оставляем комментарий", () -> {
            $("#surcharge-comment").setValue("Первая в мире рандомная наценка");
        });
        step("Нажимаем кнопку Добавить", () -> {
            $(".btn-success").click();
        });
        step("Проверяем успешное добавление варианта наценки в базу", () -> {
            $("html").shouldHave(text("Варианты наценок"));
        });
    }

    @Test
    @DisplayName("Создание заказа в системе")
    @Description("Успешное создание заказа тура в базе данных")
    void SuccessfulOrderCreation() {

        int randomInt1 = getRandomInt(1, 11);
        int randomInt2 = getRandomInt(1, 5);


        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
            $("html").shouldHave(text("Абрикосов Александр"));
        });
        step("Добавляем заказ", () -> {
            $(".sidebar-toggle").click();
            $(byText("Туры")).click();
            $(byText("Заказы")).click();
            $$(byLinkText("Добавить")).get(1).click();
            $("html").shouldHave(text("Добавить тур"));
            $(byLinkText("автобусный тур в Анапу399")).click();
            $(byLinkText("Заказы")).click();
            $(".btn-success").click();
            $("html").shouldHave(text("Опции заказа тура"));
        });
        step("Выбираем место в автобусе", () -> {
            $(byId(randomInt1 + "_" + randomInt2)).click();
            //        $(byId("1_5")).click();
        });
        step("Выбираем размещение", () -> {
            $("#order-accommodation_id").click();
            $(byText("Анапа Soho Rooms - Standart (1000.00/350.00)")).click();
        });
        step("Выбираем количество дней", () -> {
            $("#order-count_day").setValue("10");
        });
        step("Устанавливаем размер скидки", () -> {
            $("#order-price_discount").setValue("0");
        });
        step("Устанавливаем размер надбавки", () -> {
            $("#order-price_surcharge").setValue("" + randomInt);
        });
        step("Вводим данные пассажира", () -> {
            $("#order-fio").setValue("Клиент " + randomInt);
            $("#order-phone").setValue("0000000220");
            $("#order-passport").setValue("0550000555");
            $("#order-age").click();
            $(byText("Взрослый")).click();
            sleep(2000);
            $("#order-self_arrival input").click();// Нет
//            $("#order-self_arrival input", 1).click();// Да
            $x("//*[@id=\"order-one_way\"]/label[1]/input").click();
            $x("//*[@id=\"order-only_direction\"]/label[1]/input").click();
            $x("//*[@id=\"order-without_food\"]/label[1]/input").click();
            $("#order-price_dop_food").setValue("0");
        });
        step("Заполняем дополнительные поля", () -> {
            $("#order-comment").setValue("Примечание менеджера " + randomInt);
            $("#order-message").setValue("Сообщение клиента " + randomInt);
        });
        step("Указываем статус заказа и нажимаем Забронировать", () -> {
            $("#order-status").click();
            $(byText("Оплачен")).click();
            $(byText("Забронировать")).click();
        });
        step("Проверяем успешное создание заказа в базе данных", () -> {
            $("html").shouldHave(text("Изменения успешно сохранены"));
        });
    }

}