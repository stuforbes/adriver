package uk.co.stfo.adriver.driver.output;

import java.io.IOException;
import java.io.OutputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverOutput implements DriverOutput {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverOutput.class);

    private final WebDriver webDriver;


    public WebDriverOutput(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    @Override
    public void pageSourceTo(final OutputStream outputStream) throws IOException {
        outputStream.write(webDriver.getPageSource().getBytes());

    }


    @Override
    public void screenshotTo(final OutputStream outputStream) throws IOException {
        if (!(webDriver instanceof TakesScreenshot)) {
            LOG.warn("The WebDriver implementation must have been augmented with a TakesScreenshot interface. See org.openqa.selenium.remote.Augmenter for more details");
            return;
        }

        final TakesScreenshot takesScreenshot = ((TakesScreenshot) webDriver);
        final byte[] screenshotData = takesScreenshot.getScreenshotAs(OutputType.BYTES);

        outputStream.write(screenshotData);
    }

}
