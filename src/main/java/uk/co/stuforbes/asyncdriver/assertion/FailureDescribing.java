package uk.co.stuforbes.asyncdriver.assertion;

import org.hamcrest.Description;

public interface FailureDescribing {

    void describeFailureTo(Description description);
}
