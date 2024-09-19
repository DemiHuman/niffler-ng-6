package guru.qa.niffler.test.web.loginPage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("[Negative] Тесты на логин пользователя")
public class NegativeLoginTest {

    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Проверка сообщения, что данные пользователя неверные при вводе незарегистрированного пользователя")
    void checkLoginErrorMessageWithInvalidUsername() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck111", "12345");

        page(LoginPage.class)
                .checkDisplayInvalidUserCredentialsMessage();
    }

    @Test
    @DisplayName("Проверка сообщения, что данные пользователя неверные при вводе неправильного пароля у зарегистрированного пользователя")
    void checkLoginErrorMessageWithInvalidPassword() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "111111");

        page(LoginPage.class)
                .checkDisplayInvalidUserCredentialsMessage();
    }
}
