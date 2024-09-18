package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final SelenideElement createNewAccountButton = $(".form__register");

  private final SelenideElement invalidUserCredentialsErrorMessage = $x("//p[text()='Неверные учетные данные пользователя']");

  @Step("Страница логина. Ввести данные для входа и нажать логин")
  public MainPage login(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return new MainPage();
  }

  @Step("Страница логина. Ввести некорректные данные для входа и нажать логин")
  public LoginPage loginWithInvalidUserCredentials(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return this;
  }

  @Step("Страница логина. Нажать на кнопку создания нового аккаунта")
  public RegisterPage clickCreateNewAccountButton() {
    createNewAccountButton.click();

    return new RegisterPage();
  }

  @Step("Страница логина. Проверить отображение сообщения 'Неверные учетные данные пользователя'")
  public LoginPage checkDisplayInvalidUserCredentialsMessage() {
    invalidUserCredentialsErrorMessage.should(visible);

    return this;
  }
}
