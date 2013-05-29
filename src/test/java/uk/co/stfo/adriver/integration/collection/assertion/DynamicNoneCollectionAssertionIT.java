package uk.co.stfo.adriver.integration.collection.assertion;

import org.junit.Ignore;
import org.junit.Test;

public class DynamicNoneCollectionAssertionIT extends AbstractNoneCollectionAssertionIT {

    @Override
    protected String filename() {
        return "collection-assertion.html";
    }


    @Override
    protected String folder() {
        return "dynamic";
    }


    @Override
    @Test
    @Ignore("This is difficult to test a failure on, as it passes immediately")
    public void noneHasTextFailsIfOneElementIsValid() {
        // No-op
    }


    @Override
    @Test
    @Ignore("This is difficult to test a failure on, as it passes immediately")
    public void noneHasTextFailsIfAlltemsAreValid() {
        // No-op
    }


    @Override
    @Test
    @Ignore("This is difficult to test a failure on, as it passes immediately")
    public void noneFailsIfOneElementIsValid() {
        // No-op
    }


    @Override
    @Test
    @Ignore("This is difficult to test a failure on, as it passes immediately")
    public void noneFailsIfAllItemsAreValid() {
        // No-op
    }


    @Override
    protected long timeout() {
        return 1600;
    }


    @Override
    protected long pollFreq() {
        return 100;
    }
}
