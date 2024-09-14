package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {
    private final SelenideElement logInLink = $(".form__link");
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement passwordSubmitInput = $("input[name='passwordSubmit']");
    private final SelenideElement signInButton = $(".form_sign-in");
    private final SelenideElement signUpButton = $(".form__submit");

    private final SelenideElement successRegistrationText = $(".form__paragraph_success");
    private final SelenideElement UsernameAlreadyExistsText = $(".form__error");

    @Step("Страница регистрации. Ввести username '{username}'")
    public RegisterPage setUsername(String username) {
        usernameInput.setValue(username);

        return this;
    }

    @Step("Страница регистрации. Ввести пароль")
    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    @Step("Страница регистрации. Ввести пароль повторно для подтверждения")
    public RegisterPage setPasswordSubmit(String password) {
        passwordSubmitInput.setValue(password);

        return this;
    }

    @Step("Страница регистрации. Нажать кнопку 'SignUp' для создания нового пользователя")
    public RegisterPage submitRegistration() {
        signUpButton.click();

        return this;
    }

    @Step("Страница регистрации. Нажать на ссылку 'Log in' для перехода на страницу логина")
    public LoginPage goToLoginPageByLogInLink() {
        logInLink.click();

        return page(LoginPage.class);
    }

    @Step("Страница регистрации. Нажать кнопку 'SignIn' для перехода на страницу логина")
    public LoginPage goToLoginPageBySignInButton() {
        signInButton.click();

        return page(LoginPage.class);
    }

    public RegisterPage registerUser(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        passwordSubmitInput.setValue(password);
        signUpButton.click();

        return this;
    }

    @Step("Страница регистрации. Проверить сообщение об успешной регистрации нового пользователя")
    public RegisterPage checkUserSuccessRegistrationText() {
        successRegistrationText
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .shouldHave(Condition.text("Congratulations! You've registered!"));

        return this;
    }

    @Step("Страница регистрации. Проверить сообщение, что пользователь уже существует")
    public RegisterPage checkUsernameAlreadyExistsText(String username) {
        UsernameAlreadyExistsText
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .shouldHave(Condition.text(String.format("Username `%s` already exists", username)));

        return this;
    }
}
