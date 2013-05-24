package uk.co.stfo.adriver.assertion;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

/**
 * Similar to {@link SelfDescribing}. However, the intention is that
 * implementations of this will be used to describe an assertion failure
 * 
 * @author sforbes
 * 
 */
public interface FailureDescribing {

    /**
     * Explain within the {@link Description} how the assertion failed
     * 
     * @param description
     *            What the failure is reported to.
     */
    void describeFailureTo(Description description);
}
