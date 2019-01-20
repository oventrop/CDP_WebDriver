import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.DraftsPage;
import pageobjects.MailBoxPage;
import pageobjects.MainPage;
import pageobjects.NewEmailPage;

public class SimpleTest extends BaseTest {

    private static final String MAIL_RU = "http://www.mail.ru";
    private static final String LOGIN = "cdp.testing";
    private static final String PASSWORD = "password1234";
    private static final String RECIPIENT = "cdp.testing@mail.ru";
    private static final String SUBJECT = "test email";

    @Test
    public void test() throws InterruptedException {
        openPage(MAIL_RU);

        MainPage mainPage = new MainPage(wd);
        Assert.assertTrue(mainPage.isPageLoaded());

        MailBoxPage mailBoxPage = mainPage.loginToMailbox(LOGIN, PASSWORD);
        Assert.assertTrue(mailBoxPage.isPageLoaded());

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
