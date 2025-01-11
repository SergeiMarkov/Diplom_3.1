import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import user.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AccountProfilePage;
import pageobject.MainPage;
import pageobject.SignInPage;

import static org.junit.Assert.assertTrue;

public class TransitionTest extends BaseTest {

    private User user;

    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createUser();
        addUserToDeleteListTearDown(user);
    }

    @Test
    @DisplayName("Происходит редирект на авторизацию при переходе в личный кабинет если не авторизован")
    public void ifUnauthorizedWhenGoToAccountProfileRedirectsToSignInPageTest(){
        assertTrue(
                new MainPage()
                        .goToAccountProfilePageWhenUnauthorized()
                        .isSignInButtonDisplayed()
        );
    }

    @Test
    @DisplayName("Происходит переход в личный кабинет если авторизован при нажатии на личный кабинет")
    public void ifAuthorizedClickOnAccountProfileButtonGoesToAccountProfilePageTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized();

        Selenide.Wait().until(ExpectedConditions.urlToBe(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL));

        Assert.assertEquals(AccountProfilePage.ACCOUNT_PROFILE_PAGE_URL, WebDriverRunner.url());
    }

    @Test
    @DisplayName("Переход на главную страницу по кнопке Конструктор")
    public void redirectFromAccountProfileToMainPagePageViaConstructorButtonTest(){
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageByClickConstructorButton();

        Assert.assertEquals(MainPage.MAIN_PAGE_URL, WebDriverRunner.url());
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на лого сайта Stellar Burgers")
    public void redirectFromAccountProfileToMainPageViaStellarBurgerLogoTest() {
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .goToMainPageByClickStellarBurgerLogo();

        Assert.assertEquals(MainPage.MAIN_PAGE_URL, WebDriverRunner.url());
    }

    @Test
    @DisplayName("По кнопке выйти клиент выходит из системы")
    public void logOutFromSiteTest(){
        new MainPage()
                .clickOnSignInMainButton()
                .signIn(user)
                .goToAccountProfilePageWhenAuthorized()
                .clickExitButton();

        Selenide.Wait().until(ExpectedConditions.urlToBe(SignInPage.SING_IN_PAGE_URL));

        Assert.assertEquals(SignInPage.SING_IN_PAGE_URL, WebDriverRunner.url());
    }

}