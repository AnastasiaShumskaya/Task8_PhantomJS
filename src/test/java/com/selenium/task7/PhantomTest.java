package com.selenium.task7;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class PhantomTest {

    private static final String fileName = "src\\main\\resources\\image2.png";
    private static final String url = "https://tut.by";
    private static final String EXPECTED_TITLE = "Белорусский портал TUT.BY";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("phantomjs.binary.path",
                "src/main/resources/phantomjs-2.1.1-windows/bin/phantomjs.exe");
        driver = new PhantomJSDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearsDown() {

        driver.close();
    }
    @Test
    public void loginPhantomJsTest() throws IOException {

        driver.get(url);

        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1, new File(fileName));
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(fileName));

        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE);
    }
}

