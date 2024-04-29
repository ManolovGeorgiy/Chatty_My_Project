package e2e.tests.post;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.pages.Header;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.post.CreateAPostForm;
import java.util.Locale;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserCanCreateANewPost extends TestBase {

    Faker faker = new Faker(new Locale("ENGLISH"));

    LoginPage loginPage;
    HomeBlogPage homeBlogPage;
    CreateAPostForm createAPostForm;
    Header header;

    // Метод для выбора случайного пути к изображению в указанной папке
    public String selectRandomImagePath(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        List<String> imagePaths = new ArrayList<>();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    imagePaths.add(file.getAbsolutePath());
                }
            }
            if (!imagePaths.isEmpty()) {
                Random random = new Random();
                return imagePaths.get(random.nextInt(imagePaths.size()));
            } else {
                System.err.println("Folder " + folderPath + " не содержит изображений формата .jpg.");
                return null;
            }
        } else {
            System.err.println("Folder " + folderPath + " не существует или не содержит файлов.");
            return null;
        }
    }

    private void checkPostData(CreateAPostForm page, String title, String description, String content) {
        String actualTitle = page.getTitle();
        String actualDescription = page.getDescriptionText();
        String actualContent = page.getContent();
        Assert.assertEquals(actualTitle, title, actualTitle + " is not equal " + title);
        Assert.assertEquals(actualDescription, description, actualDescription + " is not equal " + description);
        Assert.assertEquals(actualContent, content, actualContent + " is not equal " + content);
    }

    @Test(description = "User can create a post")
    public void userCanCreateNewPost() {
        String email = "usercreatepost@abv.bg";
        String password = "Manowar33246";
        String title = "My first post";
        String description = "Pice";
        String content = faker.lorem().sentence(20);
        String folderPath = "/var/jenkins_home/workspace/Chatty/GPower/src/test/java/resources/5204092180870848359_121.jpg";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();

        header = new Header(app.driver);
        header.createAPostClick();
        header.waitForLoading();

        createAPostForm = new CreateAPostForm(app.driver);
        createAPostForm.userCanNotCreateAPost(title, description, content);

        String randomImagePath = selectRandomImagePath(folderPath);
        if (randomImagePath != null) {
            createAPostForm.uploadImage(randomImagePath);
            createAPostForm.waitForLoading();
        } else {
            System.err.println("Не удалось выбрать изображение для публикации.");
        }

        checkPostData(createAPostForm, title, description, content);
        createAPostForm.clickSubmitButton();
        createAPostForm.waitForLoading();


    }
}
