package uk.co.stfo.adriver.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.assertion.FailureDescribing;

public interface Probe extends SelfDescribing, FailureDescribing {

    void doProbe();


    boolean isSatisfied();
}
