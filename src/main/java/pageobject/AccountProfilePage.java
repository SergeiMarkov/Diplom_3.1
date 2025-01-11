package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class AccountProfilePage {

    public final static String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final SelenideElement toConstructorButton = $(byTagAndText("p", "Конструктор"));
    private final SelenideElement stellarBurgerLogo = $("a[href='/']");
    private final SelenideElement logOutButton = $(byTagAndText("button", "Выход"));

    @Step("Переход на главную страницу сайта из личного кабинета по клику на кнопку конктруктора")
    public MainPage goToMainPageByClickConstructorButton(){
        toConstructorButton.click();

        return new MainPage();
    }

    @Step("Переход на главную страницу сайта по клику на лого сайта из личного кабинета")
    public MainPage goToMainPageByClickStellarBurgerLogo(){
        stellarBurgerLogo.click();

        return new MainPage();
    }

    @Step("Клик по кнопке для выйти в личном кабинете")
    public SignInPage clickExitButton(){
        logOutButton.click();

        return new SignInPage();
    }
}