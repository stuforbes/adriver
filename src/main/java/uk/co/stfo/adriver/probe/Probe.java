package uk.co.stfo.adriver.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.assertion.FailureDescribing;

/**
 * Interface to perform defined probe actions, and determine whether the probe
 * is satisfied
 * 
 * @author sforbes
 * 
 */
public interface Probe extends SelfDescribing, FailureDescribing {

    /**
     * Perform an action on the data.
     */
    void doProbe();


    /**
     * Have we resolved our goal in this probe?
     * 
     * @return True if the probe is satisfied, otherwise false
     */
    boolean isSatisfied();
}
