package uk.co.stfo.adriver.assertion.collection;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.assertion.collection.probe.GenericElementMatcherElementToProbeCreator;
import uk.co.stfo.adriver.assertion.collection.probe.HasAttributeElementToProbeCreator;
import uk.co.stfo.adriver.assertion.collection.probe.HasTextElementToProbeCreator;
import uk.co.stfo.adriver.assertion.collection.result.ResultStrategy;
import uk.co.stfo.adriver.assertion.element.BaseElementAssertable;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.probe.AssertOnCollectionProbe;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Implementation of {@link BaseElementAssertable} specifically for handling
 * assertions on collections of elements
 * 
 * @author sforbes
 * 
 */
public class CollectionItemElementAssertable implements BaseElementAssertable {

    private final Poller poller;
    private final Traversable parent;
    private final By by;
    private final ElementFactory elementFactory;
    private final ResultStrategy resultStrategy;
    private final CollectionSize collectionSize;


    public CollectionItemElementAssertable(final CollectionSize collectionSize, final By by, final Traversable parent,
            final Poller poller, final ElementFactory elementFactory, final ResultStrategy resultStrategy) {
        this.collectionSize = collectionSize;
        this.by = by;
        this.parent = parent;
        this.poller = poller;
        this.elementFactory = elementFactory;
        this.resultStrategy = resultStrategy;
    }


    @Override
    public void hasAttribute(final String attributeName, final Matcher<String> valueMatcher) {
        final ElementToProbeCreator probeCreator = new HasAttributeElementToProbeCreator(attributeName, valueMatcher);

        doPollWith(probeCreator);
    }


    @Override
    public void hasText(final Matcher<String> textMatcher) {
        final ElementToProbeCreator probeCreator = new HasTextElementToProbeCreator(textMatcher);

        doPollWith(probeCreator);
    }


    @Override
    public void matches(final Matcher<WebElement> elementMatcher) {
        final ElementToProbeCreator probeCreator = new GenericElementMatcherElementToProbeCreator(elementMatcher);

        doPollWith(probeCreator);
    }


    private void doPollWith(final ElementToProbeCreator probeCreator) {
        poller.doProbe(new AssertOnCollectionProbe(collectionSize, by, parent, probeCreator, elementFactory,
                resultStrategy));
    }
}
