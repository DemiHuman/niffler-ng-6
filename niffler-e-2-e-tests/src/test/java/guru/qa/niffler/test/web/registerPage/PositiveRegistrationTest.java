package guru.qa.niffler.test.web.registerPage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.niffler.utils.randomMethods.getRandomPassword;
import static guru.qa.niffler.utils.randomMethods.getRandomUsername;

@DisplayName("Позитивные тесты на регистрацию нового пользователя")
public class PositiveRegistrationTest {

    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Регистрация нового пользователя")
    void registerNewUser() {
        String password = getRandomPassword();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .clickCreateNewAccountButton()
                .setUsername(getRandomUsername())
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .checkUserSuccessRegistrationText();
    }
}
