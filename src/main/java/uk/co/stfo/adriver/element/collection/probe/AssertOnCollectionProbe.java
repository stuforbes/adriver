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


    public AssertOnCollectionProbe(final By by, final Traversable parent, final ElementToProbeCreator probeCreator,
            final ElementFactory elementFactory) {
        this.by = by;
        this.parent = parent;
        this.probeCreator = probeCreator;
        this.elementOperator = new ProbeElementOperator(probeCreator);
        this.elementFactory = elementFactory;
        this.results = new CollectionAssertionResult();
    }


    @Override
    public void doProbe() {
        LOG.debug("About to do probe");
        final List<WebElement> allElements = parent.locateAllWith(by);

        LOG.debug("Found {} elements", allElements.size());
        results.reset();

        doIteration(allElements);
    }


    @Override
    public boolean isSatisfied() {
        return results.successCount() > 0 && results.failureCount() == 0;
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText("All children of parent ");
        description.appendText(parent.toString());
        description.appendText(", matching criteria ");
        description.appendText(ByUtils.asString(by));
        description.appendText(", that ");
        description.appendDescriptionOf(probeCreator);
    }


    @Override
    public void describeFailureTo(final Description description) {
        description.appendText("The following elements were not valid: ");
        for (final Element element : results.failureElements()) {
            description.appendText("\n\t");
            description.appendDescriptionOf(element);
        }
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

    private static final class CollectionAssertionResult {
        private int successCount;

        private final List<Element> failureElements;


        public CollectionAssertionResult() {
            this.failureElements = new ArrayList<Element>();
            reset();
        }


        public void updateScore(final boolean result, final Element element) {
            if (result) {
                this.successCount++;
            } else {
                this.failureElements.add(element);
            }
        }


        public int successCount() {
            return successCount;
        }


        public int failureCount() {
            return failureElements.size();
        }


        public List<Element> failureElements() {
            return Collections.unmodifiableList(failureElements);
        }


        public void reset() {
            this.successCount = 0;
            this.failureElements.clear();
        }


        @Override
        public String toString() {
            return "Successes: " + successCount + "; Failures: " + failureElements.size();
        }
    }
}
