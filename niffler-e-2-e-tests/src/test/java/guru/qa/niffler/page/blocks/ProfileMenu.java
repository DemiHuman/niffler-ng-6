package guru.qa.niffler.page.blocks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProfileMenu {
    protected final SelenideElement profile = $("a[href='/profile']");
    protected final SelenideElement friends = $("a[name='/people/friends']");
    protected final SelenideElement allPeople = $("a[name='/people/all']");
    protected final SelenideElement signOut = $("div[text='Sign out']");
}
