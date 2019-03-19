package listeners;

import static utility.SimpleLogger.logger;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
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
        logger.error("TEST FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("TEST STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("TEST SUCCESS!");
        getScreenshot();
    }

    private String generateScreenshotName() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH.mm:ss");
        Calendar now = Calendar.getInstance();
        return formatter.format(now.getTime());
    }

    private void getScreenshot() {
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            String fileName = generateScreenshotName();
            ImageIO.write(image, "png", new File(String.format(OUTPUT_DIRECTORY + "/%s.png", fileName)));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
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
