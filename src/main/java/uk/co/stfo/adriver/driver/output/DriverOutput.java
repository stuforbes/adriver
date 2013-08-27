package uk.co.stfo.adriver.driver.output;

import java.io.IOException;
import java.io.OutputStream;

import uk.co.stfo.adriver.driver.Driver;

/**
 * Collection of methods used to output the current state of the {@link Driver}
 * 
 * @author sforbes
 * 
 */
public interface DriverOutput {

    /**
     * Print the page source to the specified {@link OutputStream}
     * 
     * @param outputStream
     *            Where to print the page source
     */
    void pageSourceTo(OutputStream outputStream) throws IOException;


    /**
     * Take a screenshot of the current page, and output it into the
     * {@link OutputStream}
     * 
     * @param outputStream
     *            Where to output the screenshot
     * @throws IOException
     */
    void screenshotTo(OutputStream outputStream) throws IOException;
}
