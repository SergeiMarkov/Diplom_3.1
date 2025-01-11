package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    private SelenideElement usernameField = $x("//label[contains(text(), 'Имя')]/../input");
    private SelenideElement emailField = $x("//label[contains(text(), 'Email')]/../input");
    private SelenideElement passwordField = $x("//label[contains(text(), 'Пароль')]/../input");
    private SelenideElement registrationButton = $(byText("Зарегистрироваться"));
    private SelenideElement passwordErrorText = $(byText("Некорректный пароль"));
    private SelenideElement logInButton = $(byLinkText("Войти"));

    @Step("Заполнение полей и клик по кнопке регистрации на странице регистрации")
    public void registrationUser(String name, String email, String pass){
        usernameField.setValue(name);
        emailField.setValue(email);
        passwordField.setValue(pass);
        registrationButton.click();
    }

    @Step("Проверка появления сообщения об ошибке")
    public boolean isPasswordInputErrorTextDisplayed(){
        return passwordErrorText.isDisplayed();
    }

    @Step("Переход на страницу входа со страницы регистрации")
    public SignInPage goToLogInPageFromRegistrationPage(){
        logInButton.click();

        return new SignInPage();
    }

}
