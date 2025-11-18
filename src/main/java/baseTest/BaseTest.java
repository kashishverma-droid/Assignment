package baseTest;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void startReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("AutomationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    public String takeScreenshot(String testName) {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String folderPath = System.getProperty("user.dir") + "/screenshots/";
        String fullPath = folderPath + testName + "_" + date + ".png";


        File directory = new File(folderPath);
        if (!directory.exists()) directory.mkdirs();   // create folder automatically

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(fullPath);

        try {
            org.openqa.selenium.io.FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullPath;
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
        System.out.println("Extent Report Generated");
    }
}
