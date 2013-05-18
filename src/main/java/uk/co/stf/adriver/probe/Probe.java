package uk.co.stf.adriver.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stf.adriver.assertion.FailureDescribing;

public interface Probe extends SelfDescribing, FailureDescribing {

    void doProbe();


    boolean isSatisfied();
}
