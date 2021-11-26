package selenium.starter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Example {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void enterAndSubmitLoginCredentials(String username, String password) {
        driver.get("http://localhost:3000/login");
        WebElement usernameInputElement = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordInputElement = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement submitButtonElement = driver.findElement(By.xpath("//input[@type='submit']"));

        usernameInputElement.sendKeys(username);
        passwordInputElement.sendKeys(password);

        submitButtonElement.submit();
    }

    @Test
    public void loginFailShouldReturnError() throws Exception {
        this.enterAndSubmitLoginCredentials("blablablaNotQxistingUser@gmail.com", "isthisapasswordatall");

        WebElement errorMessageElement = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));

        String expectedErrorMessage = "Invalid username & password.";
        assertEquals(expectedErrorMessage, errorMessageElement.getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}

