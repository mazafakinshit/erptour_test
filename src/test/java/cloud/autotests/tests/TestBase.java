package cloud.autotests.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static cloud.autotests.helpers.AttachmentsHelper.*;
import static cloud.autotests.helpers.BrowserstackHelper.getBSPublicLink;
import static cloud.autotests.helpers.DriverHelper.*;
import static cloud.autotests.helpers.EnvironmentHelper.*;
import static cloud.autotests.utils.RandomUtils.getRandomInt;
import static cloud.autotests.utils.RandomUtils.getRandomMessage;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestBase {

    public static String
            DEFAULT_LOGIN = "Alex",
            DEFAULT_PASSWORD = "12345";

    public static int randomInt;
    public static String randomMessage;

    @BeforeAll
    public static void beforeAll() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
        randomInt = getRandomInt(0001, 1001);
        randomMessage = getRandomMessage(1, 6);

    }

    @AfterEach
    public void afterEach(){
        String sessionId = getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachNetwork(); // todo
        attachAsText("Browser console logs", getConsoleLogs());

        closeWebDriver();

        if (isVideoOn) attachVideo(sessionId); // in browserstack video url generates after driver close
    }
}



