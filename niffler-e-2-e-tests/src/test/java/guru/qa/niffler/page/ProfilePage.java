package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ProfilePage {

    private final SelenideElement profileTitle = $x("//h2[text()='Profile']");
    private final SelenideElement uploadNewPictureButton = $(".image__input-label");
    private final SelenideElement saveChangesButton = $("Save changes");
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement showArchivedCheckbox = $x("//span[text()='Show archived']");
    private final SelenideElement newCategoryInput = $("input[name='category']");
    private final SelenideElement archiveCategoryPopupTitle = $x("//h2[text()='Archive category']");
    private final SelenideElement archiveCategoryPopupArchiveButton = $x("//button[text()='Archive']");

    @Step("Страница профиля. Отредактировать имя")
    public ProfilePage editName(String name) {
        nameInput.setValue(name);
        saveChangesButton.click();

        return this;
    }

    @Step("Страница профиля. Нажать на чекбокс показа архивных категорий")
    public ProfilePage clickShowArchivedCheckbox() {
        showArchivedCheckbox.click();

        return this;
    }

    @Step("Страница профиля. Добавить новую категорию '{categoryName}' в список активных категорий")
    public ProfilePage addNewCategory(String categoryName) {
        nameInput.setValue(categoryName);
        saveChangesButton.click();

        return this;
    }

    @Step("Страница профиля. Отредактировать категорию '{categoryName}'")
    public ProfilePage editCategoryByName(String oldNameCategory, String newNameCategory) {
        $x(String.format("//span[text()='%s']/../..//button[@aria-label='Edit category']", oldNameCategory)).click();
        $(String.format("input[value='%s']", oldNameCategory)).setValue(newNameCategory).sendKeys("Enter");

        return this;
    }

    @Step("Страница профиля. Добавить в архив категорию '{categoryName}'")
    public ProfilePage archiveCategoryByName(String categoryName) {
        $x(String.format("//span[text()='%s']/../..//button[@aria-label='Archive category']", categoryName)).click();
        archiveCategoryPopupTitle.shouldBe(Condition.visible, Duration.ofSeconds(2));
        archiveCategoryPopupArchiveButton.click();

        return this;
    }

    @Step("Страница профиля. Проверить, что в поле username отображается '{username}'")
    public ProfilePage checkUsername(String username) {
        usernameInput.shouldHave(value(username));

        return this;
    }

    public ProfilePage checkThatCategoryPresentInCategoriesList(String categoryName, boolean isPresent) {
        if (isPresent) {
            step(String.format("Страница профиля. Проверить, что категория '%s' отображается в списке категорий", categoryName), () ->
                    $$(".MuiChip-label").find(text(categoryName)).should(visible));
        }
         else {
            step(String.format("Страница профиля. Проверить, что категория '%s' не отображается в списке категорий", categoryName), () ->
                    $$(".MuiChip-label").find(text(categoryName)).shouldNot(visible));
        }

        return this;
    }

    @Step("Страница профиля. Проверить открытие страницы профиля")
    public ProfilePage checkLoadingProfilePage() {
        profileTitle.should(visible);

        return this;
    }
}
