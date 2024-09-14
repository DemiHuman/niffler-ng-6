package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement uploadNewPictureButton = $(".image__input-label");
    private final SelenideElement saveChangesButton = $("Save changes");
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement showArchivedCheckbox = $("input[type='checkbox']");
    private final SelenideElement newCategoryInput = $("input[name='category']");
    private final SelenideElement archiveCategoryPopupTitle = $("h2[text()='Archive category']");
    private final SelenideElement archiveCategoryPopupArchiveButton = $("button[text()='Archive']");



    public ProfilePage addOrEditName(String name) {
        nameInput.setValue(name);
        saveChangesButton.click();

        return this;
    }

    public ProfilePage addNewCategory(String categoryName) {
        nameInput.setValue(categoryName);
        saveChangesButton.click();

        return this;
    }

    public ProfilePage editCategoryByName(String oldNameCategory, String newNameCategory) {
        $x(String.format("//span[text()='%s']/../..//button[@aria-label='Edit category']", oldNameCategory)).click();
        $(String.format("input[value='%s']", oldNameCategory)).setValue(newNameCategory).sendKeys("Enter");

        return this;
    }

    public ProfilePage archiveCategoryByName(String categoryName) {
        $x(String.format("//span[text()='%s']/../..//button[@aria-label='Archive category']", categoryName)).click();
        archiveCategoryPopupTitle.shouldBe(Condition.visible, Duration.ofSeconds(2));
        archiveCategoryPopupArchiveButton.click();

        return this;
    }
}
