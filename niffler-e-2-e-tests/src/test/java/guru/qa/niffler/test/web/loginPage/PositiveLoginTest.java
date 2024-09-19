package guru.qa.niffler.test.web.loginPage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("[Positive] Тесты на логин пользователя")
public class PositiveLoginTest {

    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Проверка отображения главной страницы после успешного логина")
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345");

        page(MainPage.class)
                .checkLoadingMainPage();
    }
}
