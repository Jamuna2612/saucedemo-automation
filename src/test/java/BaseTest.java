import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent; // make static (shared across all tests)
    protected ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void setupReport() {
        //  Initialize only once per suite
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications", "--incognito");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            //  Ensure test object is created before logging
            if (test == null && extent != null) {
                test = extent.createTest(result.getMethod().getMethodName());
            }

            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = takeScreenshot(result.getName());
                test.fail(result.getThrowable());
                test.addScreenCaptureFromPath(screenshotPath);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("⚠ Test skipped");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() {
        //  Flush report after all tests
        if (extent != null) {
            extent.flush();
        }
    }

    public String takeScreenshot(String testName) throws IOException {
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        String path = "test-output/screenshots/" + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
