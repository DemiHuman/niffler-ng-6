package guru.qa.niffler.test.web.registerPage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.niffler.utils.randomMethods.getRandomPassword;
import static guru.qa.niffler.utils.randomMethods.getRandomUsername;
import static io.qameta.allure.Allure.step;

@DisplayName("[Negative] Тесты на регистрацию нового пользователя")
public class NegativeRegistrationTest {

    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Повторная регистрация имеющегося пользователя")
    void shouldNotRegisterUserWithExistingUsername() {
        String username = getRandomUsername();
        String password = getRandomPassword();

        step("Регистрируем нового пользователя", () ->
                Selenide.open(CFG.frontUrl(), LoginPage.class)
                    .clickCreateNewAccountButton()
                    .registerUser(username, password)
                    .goToLoginPageBySignInButton()
        );

        step("Регистрируем пользователя повторно", () ->
                Selenide.page(LoginPage.class)
                        .clickCreateNewAccountButton()
                        .registerUser(username, password)
                        .checkUsernameAlreadyExistsText(username)
        );
    }
}
