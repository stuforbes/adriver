package uk.co.stuforbes.adriver.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stuforbes.adriver.assertion.FailureDescribing;

public interface Probe extends SelfDescribing, FailureDescribing {

    void doProbe();


    boolean isSatisfied();
}
