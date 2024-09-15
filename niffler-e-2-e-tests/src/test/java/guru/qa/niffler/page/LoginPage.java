package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final SelenideElement createNewAccountButton = $(".form__register");

  @Step("Страница логина. Ввести данные для входа и нажать логин")
  public MainPage login(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return new MainPage();
  }

  @Step("Страница логина. Нажать на кнопку создания нового аккаунта")
  public RegisterPage clickCreateNewAccountButton() {
    createNewAccountButton.click();

    return new RegisterPage();
  }
}
