package by.epam.viacom.cc.utils;

import org.testng.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listener implements ITestListener, ISuiteListener {

    public void onStart(ISuite suite) {
        System.out.println("Suite " + suite.getName() + " started!");

    }

    public void onFinish(ISuite suite) {
        System.out.println("Suite " + suite.getName() + " finished!");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getTestClass().getName() + " " + result.getName() + " started!");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getTestClass().getName() + " sucsessfully finished!");
    }

    public void onTestFailure(ITestResult result) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd, hh.mm ss");
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "jpg", new File("D:/SCREENSHOTS/"+formatter.format(calendar.getTime())+".jpg"));
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        System.out.println("SuiteTest " + context.getName() + " started!");
    }

    public void onFinish(ITestContext context) {
        System.out.println("SuiteTest " + context.getName() + " finished!");
    }
}
