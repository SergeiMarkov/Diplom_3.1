package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class ResetPasswordPage {
    private final SelenideElement signInButton = $(byLinkText("Войти"));

    @Step("Переход на страницу входа через кнопку в форме восстановления пароля")
    public SignInPage goToSignInPageFromResetPasswordPage(){
        signInButton.click();
        return new SignInPage();
    }
}
