package uk.co.stuforbes.asyncdriver.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stuforbes.asyncdriver.assertion.FailureDescribing;

public interface Probe extends SelfDescribing, FailureDescribing {

    void doProbe();


    boolean isSatisfied();
}
