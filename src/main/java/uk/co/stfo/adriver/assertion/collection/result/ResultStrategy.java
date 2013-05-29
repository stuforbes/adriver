package uk.co.stfo.adriver.assertion.collection.result;

import java.util.List;

import org.hamcrest.Description;

import uk.co.stfo.adriver.element.Element;

/**
 * Interface to determine what the success state of a collection assertion is,
 * given the number of successes and failures in the {@link ResultTally}
 * 
 * @author sforbes
 * 
 */
public interface ResultStrategy {

    /**
     * Was this collection assertion successful. For example, using
     * {@link AllResultStrategy}, 3 successes and 2 failures would return false
     * here, but using {@link AtLeastOneResultStrategy}, the same tally would
     * return true
     * 
     * @param resultTally
     *            How many successes and failures there were
     * @return True if the tally represents an overall success, false otherwise
     */
    boolean isSuccess(ResultTally resultTally);


    /**
     * The text to show up in a probe description before the 'of parent ...'
     * part. For example, {@link AllResultStrategy} would return the text 'All
     * children', providing the description 'All children of parent ...', where
     * as {@link AtLeastOneResultStrategy} returns 'At least one child',
     * providing the description 'At least one child of parent ...'
     * 
     * @return The description prefix for this strategy
     */
    String descriptionPrefix();


    /**
     * When a collection assertion fails, this method is used to report to the
     * {@link Description} how it failed
     * 
     * @param successElements
     *            The elements that passed the assertion
     * @param failureElements
     *            The elements that failed the assertion
     * @param description
     *            The target of the failure message
     */
    void reportFailureTo(List<Element> successElements, List<Element> failureElements, Description description);
}
