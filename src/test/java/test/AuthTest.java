package test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;


public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulRegistration(){
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $(".button").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void shouldUnsuccessfulRegistration(){
        var registeredUser = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldReturnIfInvalidLogin(){
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue("pppp");
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldReturnIfInvalidPassword(){
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue("yhgfv");
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldGetReturnIfRegistrationUser(){
        var registeredUser = getUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

}


