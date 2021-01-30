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

}