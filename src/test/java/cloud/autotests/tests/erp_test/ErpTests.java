package cloud.autotests.tests.erp_test;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
//import static helpers.Environment.*;


@Epic("QA.GURU automation course")
@Story("Erp-info tests")
@Tag("erptest")
public class ErpTests extends TestBase {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        if (System.getProperty("selenoid_url") != null) {
            Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub";
        }

    }

    @Test
    @DisplayName("Вход в учетную запись на портале")
    @Description("Успешный вход в учетную запись на портале")
    void SuccessfulSignIn() {
        step("Заходим на сайт", () -> {
            open("https://erp.erptour.ru/enter");
        });
        step("Заполняем поля", () -> {
            $("#loginform-username").setValue("want");
            $("#loginform-password").setValue("demo866").pressEnter();
        });
            $("html").shouldHave(text("Абрикосов Александр"));

    }

}
