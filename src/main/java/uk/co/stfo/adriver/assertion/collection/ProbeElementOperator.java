package uk.co.stfo.adriver.assertion.collection;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementOperator;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.Probe;

public class ProbeElementOperator implements ElementOperator {

    private final ElementToProbeCreator probeCreator;

    private boolean isProbeSatisfied;


    public ProbeElementOperator(final ElementToProbeCreator probeCreator) {
        this.probeCreator = probeCreator;
        this.isProbeSatisfied = false;
    }


    @Override
    public void doWith(final Element element) {
        final Probe probe = probeCreator.createFor(element);

        isProbeSatisfied = false;
        probe.doProbe();
        isProbeSatisfied = probe.isSatisfied();
    }


    public boolean isProbeSatisfied() {
        return isProbeSatisfied;
    }
}
