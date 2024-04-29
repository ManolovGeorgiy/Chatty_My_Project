package e2e.tests.post;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.pages.Header;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.post.CreateAPostForm;
import e2e.pages.post.EditAPostForm;
import e2e.pages.post.EditPostPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCanCreateEditAndDeletePostTest extends TestBase {

    Faker faker = new Faker();

    LoginPage loginPage;
    AdminPanelPage adminPanelPage;
    HomeBlogPage homeBlogPage;
    CreateAPostForm createAPostForm;
    Header header;
    EditPostPage editPostPage;
    EditAPostForm editAPostForm;

    private void checkPostData(CreateAPostForm page, String title, String description, String content) {
        String actualTitle = page.getTitle();
        String actualDescription = page.getDescriptionText();
        String actualContent = page.getContent();
        Assert.assertEquals(actualTitle, title, actualTitle + " is not equal " + title);
        Assert.assertEquals(actualDescription, description, actualDescription + " is not equal " + description);
        Assert.assertEquals(actualContent, content, actualContent + " is not equal " + content);
    }

    private void checkEditPostData(EditAPostForm page, String editTitle, String editDescription, String editContent) {
        String actualTitle = page.getEditTitle();
        String actualDescription = page.getEditDescriptionText();
        String actualContent = page.getEditContent();
        Assert.assertEquals(actualTitle, editTitle, actualTitle + " is not equal " + editTitle);
        Assert.assertEquals(actualDescription, editDescription, actualDescription + " is not equal " + editDescription);
        Assert.assertEquals(actualContent, editContent, actualContent + " is not equal " + editContent);
    }

    @Test
    public void adminCanCreateAPost() throws InterruptedException {
        String email = "g.power@gmail.com";
        String password = "GPower3333";
        String title = faker.lorem().sentence(1);
        String description = faker.lorem().sentence(1);
        String content = faker.lorem().sentence(50);
        String imagePath = "uploadReferences/adminCanCreateAPost_IT.jpg";

        String editTitle = "IT";
        String editDescription = "QA Engineer";
        String editContent = "HALLO WORLD";
        String editImagePath = "uploadReferences/adminCanCreateAPost_edit.jpg";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        adminPanelPage = new AdminPanelPage(app.driver);
        adminPanelPage.waitForLoading();

        header = new Header(app.driver);
        header.clickHome();
        header.createAPostClick();
        createAPostForm = new CreateAPostForm(app.driver);
        createAPostForm.setPostForm(title, description, content, imagePath);
        createAPostForm.waitForLoading();
        createAPostForm.uploadImage(imagePath);
        createAPostForm.waitForLoading();

        checkPostData(createAPostForm, title, description, content);
        createAPostForm.clickSubmitButton();
        createAPostForm.waitForLoading();
        Thread.sleep(3000);
        header = new Header(app.driver);
        header.clickHome();
        header.myPostClick();
        header.waitForLoading();
        header.setMyPostTab();

        editPostPage = new EditPostPage(app.driver);
        editPostPage.waitForLoading();
        editPostPage.editPostButtonClick();
        editPostPage.waitForLoading();

        editAPostForm = new EditAPostForm(app.driver);
        editAPostForm.waitForLoading();
        createAPostForm.uploadImage(editImagePath);
        editAPostForm.fillEditPostForm(editTitle, editDescription, editContent);
        checkEditPostData(editAPostForm, editTitle, editDescription, editContent);
        editAPostForm.clickEditSubmitButton();

        editPostPage = new EditPostPage(app.driver);
        editPostPage.waitForLoading();
        editPostPage.deletePostButtonClick();

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();
    }
}
