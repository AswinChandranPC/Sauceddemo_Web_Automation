package com.sauceddemo.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.sauceddemo.qa.util.TestUtil.IMPLICIT_WAIT;
import static com.sauceddemo.qa.util.TestUtil.PAGE_LOAD_TIMEOUT;

public class TestBase {
    public static WebDriver driver;
    public static Properties prop;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/java/com/sauceddemo/qa/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialization() {

        switch (prop.getProperty("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "fireFox":
                driver = new FirefoxDriver();
                break;
            case "edgeBrowser":
                driver = new EdgeDriver();
                break;
            case "internetExplorer":
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser specified in configuration!");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.get(prop.getProperty("url"));
    }
}
