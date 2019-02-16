import business.users.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DraftsPage;
import pageobjects.MailBoxPage;
import pageobjects.MainPage;
import pageobjects.NewEmailPage;

public class SimpleTest extends BaseTest {

    private static final String MAIL_RU = "http://www.mail.ru";
    private static final String SUBJECT = "test email";
    private static final String USER_NAME = "test1";
    private static final User USER = getTestUser(USER_NAME);
    private static final String RECIPIENT = USER.login;

    @Test
    public void test() throws InterruptedException {
        openPage(MAIL_RU);

        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.isPageLoaded());

        mainPage.loginToMailbox(USER.login, USER.password);

        //Runtime page selection
        Assert.assertTrue(getCurrentPage().getClass().isInstance(new MailBoxPage(webDriver)));
        Assert.assertTrue(getCurrentPage().isPageLoaded());

        MailBoxPage mailBoxPage = getCurrentPage();
        NewEmailPage newEmailPage = mailBoxPage.writeNewEmail();
        Assert.assertTrue(newEmailPage.isPageLoaded());

        newEmailPage.fillAddress(RECIPIENT);
        newEmailPage.fillSubject(SUBJECT);
        newEmailPage.fillEmailBody("some body");
        newEmailPage.saveDraft();

        DraftsPage draftsPage = newEmailPage.openDrafts();
        Assert.assertTrue(draftsPage.isPageLoaded());
        Assert.assertTrue(draftsPage.isDraftMessagePresent(RECIPIENT, SUBJECT));

        newEmailPage = draftsPage.openSavedEmail(RECIPIENT, SUBJECT);
        Assert.assertTrue(newEmailPage.isPageLoaded());
        Assert.assertTrue(newEmailPage.isAddressFieldFilled(RECIPIENT));

        newEmailPage.sendEmail();
        draftsPage = newEmailPage.openDrafts();
        Assert.assertTrue(draftsPage.isPageLoaded());
        Assert.assertFalse(draftsPage.isDraftMessagePresent(RECIPIENT, SUBJECT));
    }
}
