package guru.qa.niffler.test.web.profilePage;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                .login("duck", "12345")
                .checkLoadingMainPage()
                .openProfile()
                .checkLoadingProfilePage()
                .checkUsername(category.username());
    }

    @Category(
            username = "duck"
    )
    @Test
    void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .checkLoadingMainPage()
                .openProfile()
                .checkLoadingProfilePage()
                .checkUsername(category.username())
                .checkThatActiveCategoryPresentInCategoriesList(category.name());
    }
}
