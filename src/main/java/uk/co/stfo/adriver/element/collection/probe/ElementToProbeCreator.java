package uk.co.stfo.adriver.element.collection.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.probe.Probe;

/**
 * Creates a {@link Probe} relative to the {@link Element}. Useful for
 * performing assertions on items in a collection, where every item needs to be
 * probed.
 * 
 * @author sforbes
 * 
 */
public interface ElementToProbeCreator extends SelfDescribing {

    /**
     * Create a {@link Probe} relative to the {@link Element}
     * 
     * @param element
     *            The {@link Element} to be probed
     * @return The {@link Probe} for the {@link Element}
     */
    Probe createFor(Element element);
}
