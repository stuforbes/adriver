package uk.co.stuforbes.asyncdriver.poll;

import uk.co.stuforbes.asyncdriver.probe.Probe;

public interface Poller {

    void doProbe(Probe probe);
}
