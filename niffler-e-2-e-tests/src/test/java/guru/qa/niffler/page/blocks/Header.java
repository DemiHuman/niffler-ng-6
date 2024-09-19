package guru.qa.niffler.page.blocks;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.ProfilePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Header {

    private final ProfileMenu profileMenu = new ProfileMenu();

    private final SelenideElement newSpendingButton = $("New spending");
    private final SelenideElement profileMenuButton = $("button[aria-label='Menu']");

    public ProfilePage openProfile() {
        profileMenuButton.click();
        profileMenu.profile.click();

        return page(ProfilePage.class);
    }
}
