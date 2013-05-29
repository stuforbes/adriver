package uk.co.stfo.adriver.element.collection.probe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.assertion.collection.ProbeElementOperator;
import uk.co.stfo.adriver.assertion.collection.result.ResultStrategy;
import uk.co.stfo.adriver.assertion.collection.result.ResultTally;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.probe.Probe;
import uk.co.stfo.adriver.util.ByUtils;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Locates all elements identified by the {@link Traversable} parent, and
 * {@link By} criteria, and probes them, using the probe that is created by a
 * {@link ElementToProbeCreator}.
 * 
 * Successes and failures are counted up, and depending on the ResultStrategy,
 * the probes satisfaction is determined.
 * 
 * @author sforbes
 * 
 */
public class AssertOnCollectionProbe implements Probe {

    private static final Logger LOG = LoggerFactory.getLogger(AssertOnCollectionProbe.class);

    private final By by;
    private final Traversable parent;
    private final ElementFactory elementFactory;
    private final ProbeElementOperator elementOperator;

    private final CollectionAssertionResult results;

    private final ElementToProbeCreator probeCreator;

    private final ResultStrategy resultStrategy;

    private final int collectionSize;


    public AssertOnCollectionProbe(final int collectionSize, final By by, final Traversable parent,
            final ElementToProbeCreator probeCreator, final ElementFactory elementFactory,
            final ResultStrategy resultStrategy) {
        this.collectionSize = collectionSize;
        this.by = by;
        this.parent = parent;
        this.probeCreator = probeCreator;
        this.resultStrategy = resultStrategy;
        this.elementOperator = new ProbeElementOperator(probeCreator);
        this.elementFactory = elementFactory;
        this.results = new CollectionAssertionResult();
    }


    @Override
    public void doProbe() {
        LOG.debug("About to do probe");
        final List<WebElement> allElements = parent.locateAllWith(by);

        results.reset();
        if (allElements.size() == collectionSize) {
            LOG.debug("Found {} elements, which is the expected collection size", allElements.size());

            doIteration(allElements);
        } else {
            LOG.debug("Expected {} elements, but found {}. Aborting probe execution for this iteration");
        }
    }


    @Override
    public boolean isSatisfied() {
        return results.resultSize() > 0 && resultStrategy.isSuccess(results);
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText(resultStrategy.descriptionPrefix());
        description.appendText(" of parent ");
        description.appendText(parent.toString());
        description.appendText(", matching criteria ");
        description.appendText(ByUtils.asString(by));
        description.appendText(", that ");
        description.appendDescriptionOf(probeCreator);
    }


    @Override
    public void describeFailureTo(final Description description) {
        resultStrategy.reportFailureTo(results.successElements(), results.failureElements(), description);
    }


    private void doIteration(final List<WebElement> allElements) {
        LOG.debug("Iterating through {} web elements", allElements.size());
        for (int i = 0; i < allElements.size(); i++) {
            final Element element = elementFactory.createForPositionInList(i, parent);

            elementOperator.doWith(element);

            final boolean probeSatisfied = elementOperator.isProbeSatisfied();
            results.updateScore(probeSatisfied, element);
            LOG.debug("Updating results with {}. Overall results is now: {}", probeSatisfied, results);
        }
    }

    private static final class CollectionAssertionResult implements ResultTally {

        private final List<Element> successElements;
        private final List<Element> failureElements;


        public CollectionAssertionResult() {
            this.successElements = new ArrayList<Element>();
            this.failureElements = new ArrayList<Element>();
            reset();
        }


        public void updateScore(final boolean result, final Element element) {
            if (result) {
                this.successElements.add(element);
            } else {
                this.failureElements.add(element);
            }
        }


        @Override
        public int successes() {
            return successElements.size();
        }


        @Override
        public int failures() {
            return failureElements.size();
        }


        public int resultSize() {
            return successes() + failures();
        }


        public List<Element> successElements() {
            return Collections.unmodifiableList(successElements);
        }


        public List<Element> failureElements() {
            return Collections.unmodifiableList(failureElements);
        }


        public void reset() {
            this.successElements.clear();
            this.failureElements.clear();
        }


        @Override
        public String toString() {
            return "Successes: " + successElements.size() + "; Failures: " + failureElements.size();
        }
    }
}
