package guru.qa.niffler.test.web.loginPage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Negative] Тесты на логин пользователя")
public class NegativeLoginTest {

    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Проверка отображения главной страницы после успешного логина")
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .checkLoadingMainPage();
    }
}
