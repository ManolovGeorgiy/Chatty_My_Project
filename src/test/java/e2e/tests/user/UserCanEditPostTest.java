package e2e.tests.user;

import e2e.TestBase;
import e2e.pages.Header;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.post.CreateAPostForm;
import e2e.pages.post.EditAPostForm;
import e2e.pages.post.EditPostPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanEditPostTest extends TestBase {

    LoginPage loginPage;
    HomeBlogPage homeBlogPage;
    Header header;
    EditPostPage editPostPage;
    EditAPostForm editAPostForm;
    private void checkEditPostData(EditAPostForm page, String editTitle, String editDescription, String editContent) {
        String actualTitle = page.getEditTitle();
        String actualDescription = page.getEditDescriptionText();
        String actualContent = page.getEditContent();
        Assert.assertEquals(actualTitle, editTitle, actualTitle + " is not equal " + editTitle);
        Assert.assertEquals(actualDescription, editDescription, actualDescription + " is not equal " + editDescription);
        Assert.assertEquals(actualContent, editContent, actualContent + " is not equal " + editContent);
    }
    @Epic(value = "User can edit post")
    @Feature(value = "User edited post")
    @Description(value = "User can edit post")
    @Severity(SeverityLevel.BLOCKER)
    @AllureId("16")
    @Test(description = "CHATTY-40")
    public void userCanEditAPost() {

        String email = "tatar@abv.bg";
        String password = "Manowar33246";

        String editTitle = "IT";
        String editDescription = "QA Engineer";
        String editContent = "HALLO WORLD";
        String imagePath = "C:\\Users\\PC\\Chatty\\src\\test\\java\\path\\5204092180870848376_121.jpg";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        homeBlogPage = new HomeBlogPage(app.driver);

        header = new Header(app.driver);
        header.waitForLoading();
        header.myPostClick();
        header.waitForLoading();
        header.setMyPostTab();

        editPostPage = new EditPostPage(app.driver);
        editPostPage.waitForLoading();
        editPostPage.editPostButtonClick();
        editPostPage.waitForLoading();

        editAPostForm = new EditAPostForm(app.driver);
        editAPostForm.waitForLoading();
        editAPostForm.uploadImageLoading(imagePath);
        editAPostForm.fillEditPostForm(editTitle,editDescription,editContent);
        checkEditPostData(editAPostForm,editTitle,editDescription,editContent);
        editAPostForm.clickEditSubmitButton();

        editPostPage = new EditPostPage(app.driver);
        editPostPage.waitForLoading();
    }
}
