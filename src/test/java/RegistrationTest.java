import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import pageobject.SignInPage;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void checkUserCouldRegistrationOnWebSiteWithValidDataTest(){
        User user = User.generateUser();

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registrationUser(user.getName(),
                        user.getEmail(),
                        user.getPassword()
                );

        addUserToDeleteListTearDown(user);

        Assert.assertEquals(user.getEmail(), new SignInPage().getTextFromEmailField());
    }


    @Test
    @DisplayName("Проверка отображения ошибки при вводе невалидного пароля на странице регистрации")
    public void checkIfPasswordIncorrectErrorTextDisplayedDuringRegistrationTest(){
        User user = User.generateUncorrectUser();

        new MainPage()
                .clickOnSignInMainButton()
                .clickButtonToRedirectOnRegistrationPage()
                .registrationUser(user.getName(),
                        user.getEmail(),
                        user.getPassword()
                );

        Assert.assertTrue(new RegistrationPage().isPasswordInputErrorTextDisplayed());
    }
}