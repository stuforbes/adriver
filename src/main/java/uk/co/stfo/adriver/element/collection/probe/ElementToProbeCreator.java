package uk.co.stfo.adriver.element.collection.probe;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.probe.Probe;

public interface ElementToProbeCreator extends SelfDescribing {

    Probe createFor(Element element);
}
