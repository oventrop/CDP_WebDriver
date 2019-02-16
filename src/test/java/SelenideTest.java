import business.UserFactory;
import business.users.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    private static final String USER_NAME = "test1";
    private static final User USER = getTestUser(USER_NAME);

    @Test
    public void loginToMailBox() {
        open("http://www.mail.ru");

        //fill username / pass
        $(By.id("mailbox:login")).setValue(USER.login);
        $(By.id("mailbox:password")).setValue(USER.password);

        //submit
        $(By.xpath("//*[@class=\"o-control\" and @type = \"submit\"]")).click();

        //wait for mailbox opens
        Assert.assertTrue($(By.xpath("//*[@data-name=\"compose\"]")).should(appear).isDisplayed(), "Mail page didn't open");
    }

    private static User getTestUser(String name){
        UserFactory factory = new UserFactory();
        return factory.getUser(name);
    }
}