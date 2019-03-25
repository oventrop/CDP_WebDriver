package listeners;

import static utility.SimpleLogger.logger;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestListeners implements ITestListener, ISuiteListener {

    public static final File OUTPUT_DIRECTORY = new File("output/screenshots/");

    @Override
    public void onFinish(ISuite suite) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onStart(ISuite suite) {
        logger.info("CLEAR OUTPUT DIRECTORY");
        clearOutputDirectory();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test FAILURE: " + result.getName());
        saveScreenshot(getScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test class STARTED: " + result.getTestClass().getName());
        logger.info("Test STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test SUCCESS: " + result.getName());
        logOutput(Reporter.getOutput(result));
        saveScreenshot(getScreenshot());
    }

    private String generateScreenshotName() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH.mm:ss");
        Calendar now = Calendar.getInstance();
        return formatter.format(now.getTime());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(File imageFile) {
        try {
            if (imageFile.exists()) {
                return com.google.common.io.Files.toByteArray(imageFile);
            }
        } catch (Exception e) {
            logger.error("FAILED TO GET FILE");
        }
        throw new RuntimeException();
    }

    public File getScreenshot() {
        BufferedImage image = null;
        File f = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            String fileName = generateScreenshotName();
            f = new File(String.format(OUTPUT_DIRECTORY + "/%s.png", fileName));
            ImageIO.write(image, "png", f);
            return f;
        } catch (AWTException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Attachment
    public String logOutput(List<String> outputList) {
        String output = "";
        for (String o : outputList)
            output += o + "<br/>";
        return output;
    }

    private void clearOutputDirectory() {
        try {
            FileUtils.cleanDirectory(OUTPUT_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteLogFile() {
        File file = new File("output/log.out");
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (RuntimeException rEx) {
            rEx.printStackTrace();
        }
    }
}
