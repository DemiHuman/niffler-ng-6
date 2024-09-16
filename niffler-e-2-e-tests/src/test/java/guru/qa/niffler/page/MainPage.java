package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.blocks.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends Header {
  private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
  private final SelenideElement statisticsTitle = $x("//h2[text()='Statistics']");
  private final SelenideElement historyOfSpendingsTitle = $x("//h2[text()='History of Spendings']");

  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  public void checkThatTableContainsSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).should(visible);
  }

  @Step("Главная страница. Проверить открытие главной страницы")
  public MainPage checkLoadingMainPage() {
    statisticsTitle.should(visible);
    historyOfSpendingsTitle.should(visible);

    return this;
  }
}
