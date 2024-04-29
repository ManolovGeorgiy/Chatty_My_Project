package e2e.tests.user;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.pages.Header;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.post.CreateAPostForm;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


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

        // Проверяем, что папка существует и содержит файлы
        if (files != null && files.length > 0) {
            // Фильтруем только файлы с расширением .jpg
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    imagePaths.add(file.getAbsolutePath());
                }
            }
            // Если есть хотя бы одно изображение, выбираем случайный путь к нему
            if (!imagePaths.isEmpty()) {
                Random random = new Random();
                return imagePaths.get(random.nextInt(imagePaths.size()));
            } else {
                System.err.println("image" + folderPath + " не содержит изображений формата .jpg.");
                return null;
            }
        } else {
            System.err.println("image" + folderPath + " не существует или не содержит файлов.");
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
    @Epic(value = "User can create a post")
    @Feature(value = "User created post")
    @Description(value = "User can create a post")
    @Severity(SeverityLevel.BLOCKER)
    @AllureId("15")
    @Test(description = "CHATTY-39")
    public void userCanCreateAPost() {
        String email = "tatar@abv.bg";
        String password = "Manowar33246";
        String title = "My first post";
        String description = "Pice";
        String content = faker.lorem().sentence(20);
        String folderPath = "C:\\Users\\PC\\Chatty\\src\\test\\java\\path";

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
        checkPostData(createAPostForm, title,description,content);
        createAPostForm.clickSubmitButton();
        createAPostForm.waitForLoading();
    }
}