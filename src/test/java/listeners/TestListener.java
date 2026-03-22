package listeners;

import base.DriverFactory;
import com.aventstack.extentreports.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                result.getName()
        );

        test.addScreenCaptureFromPath(screenshotPath);

        System.out.println("Screenshot captured for failed test: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}