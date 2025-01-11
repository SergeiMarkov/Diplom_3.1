package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final SelenideElement signInMainButton = $(byText("Войти в аккаунт"));
    private final SelenideElement createOrderButton = $(byText("Оформить заказ"));
    private final SelenideElement toAccountProfileButton = $(byTagAndText("p", "Личный Кабинет"));
    private final SelenideElement bunsButton = $x("//span[contains(text(), 'Булки')]/..");
    private final SelenideElement saucesButton = $x("//span[contains(text(), 'Соусы')]/..");
    private final SelenideElement fillingsButton = $x("//span[contains(text(), 'Начинки')]/..");
    private final SelenideElement bunsHeaderInMenu = $(byTagAndText("h2", "Булки"));
    private final SelenideElement saucesHeaderInMenu = $(byTagAndText("h2", "Соусы"));
    private final SelenideElement fillingsHeaderInMenu = $(byTagAndText("h2", "Начинки"));

    private final SelenideElement sectionIngredients = $(By.className("tab_tab_type_current__2BEPc"));
    private final SelenideElement constructorContainer = $x("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']");


    @Step("Клик по кнопке войти на главной странице для перехода на страницу входа")
    public SignInPage clickOnSignInMainButton() {
        signInMainButton.click();

        return new SignInPage();
    }

    @Step("Проверка, что кнопка заказа видима")
    public boolean isOrderButtonDisplayed() {
        createOrderButton.shouldBe(visible);

        return createOrderButton.isDisplayed();
    }

    @Step("Клик по кнопке перехода в личный кабинет до авторизации")
    public SignInPage goToAccountProfilePageWhenUnauthorized() {
        toAccountProfileButton.click();

        return new SignInPage();
    }

    @Step("Клик по кнопке перехода в личный кабинет после авторизации")
    public AccountProfilePage goToAccountProfilePageWhenAuthorized() {
        toAccountProfileButton.click();

        return new AccountProfilePage();
    }

    @Step("Клик по заголовку Булки в конструкторе")
    public MainPage clickBunsTab() {
        bunsButton.click();

        return this;
    }

    @Step("Клик по заголовку Соусы в конструкторе")
    public MainPage clickSaucesTab() {
        saucesButton.click();

        return this;
    }

    @Step("Клик по заголовку Начинки в конструкторе")
    public MainPage clickFillingsTab() {
        fillingsButton.click();

        return this;
    }
    @Step("Проверка, что кнопка Булки активна")
    public boolean isBunsTabSelected(){
        String classValue = bunsButton.getAttribute("class");

        return classValue.contains("current");
    }

    @Step("Проверка, что кнопка Соусы активна")
    public boolean isSaucesTabSelected(){
        String classValue = saucesButton.getAttribute("class");

        return classValue.contains("current");
    }

    @Step("Проверка, что кнопка Начинки активна")
    public boolean isFillingsTabSelected(){
        String classValue = fillingsButton.getAttribute("class");

        return classValue.contains("current");
    }

    @Step("Проверка, что заголовок Булки выбран")
    public boolean isBunsIngredientSelected() {
        return sectionIngredients.getText().contentEquals("Булки");
    }

    @Step("Проверка, что заголовок Соусы выбран")
    public boolean isSaucesIngredientSelected() {
        return sectionIngredients.getText().contentEquals("Соусы");
    }

    @Step("Проверка, что заголовок Начинки выбран")
    public boolean isFillingsIngredientSelected() {
        return sectionIngredients.getText().contentEquals("Начинки");
    }

    @Step("Скролл до заголовка Булки")
    public MainPage isScrolledToBunsHeaderInMenu() {
        constructorContainer.click();
        bunsHeaderInMenu.scrollIntoView(true);
        return this;
    }

    @Step("Скролл до заголовка Соусы")
    public MainPage isScrolledToSaucesHeaderInMenu() {
        constructorContainer.click();
        saucesHeaderInMenu.scrollIntoView(true);
        return this;
    }

    @Step("Скролл до заголовка Начинки")
    public MainPage isScrolledToFillingsHeaderInMenu() {
        constructorContainer.click();
        fillingsHeaderInMenu.scrollIntoView(true);
        return this;
    }

}
