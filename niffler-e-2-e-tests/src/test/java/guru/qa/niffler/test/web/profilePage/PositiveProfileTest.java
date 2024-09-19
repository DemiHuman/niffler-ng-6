package guru.qa.niffler.test.web.profilePage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("[Positive] Тесты на страницу профиля")
public class PositiveProfileTest {

    private static final Config CFG = Config.getInstance();

    @Category(
            username = "duck",
            archived = true
    )
    @Test
    void archivedCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345");

        page(MainPage.class)
                .checkLoadingMainPage()
                .openProfile()
                .checkLoadingProfilePage()
                .checkUsername(category.username())
                .checkThatCategoryNotPresentInCategoriesList(category.name())
                .clickShowArchivedCheckbox()
                .checkThatCategoryPresentInCategoriesList(category.name());
    }

    @Category(
            username = "duck"
    )
    @Test
    void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345");

        page(MainPage.class)
                .checkLoadingMainPage()
                .openProfile()
                .checkLoadingProfilePage()
                .checkUsername(category.username())
                .checkThatCategoryPresentInCategoriesList(category.name());
    }
}
