package uk.co.stfo.adriver.integration.collection.assertion;

public class DynamicAtLeastOneCollectionAssertionIT extends AbstractAtLeastOneCollectionAssertionIT {

    @Override
    protected String filename() {
        return "collection-assertion.html";
    }


    @Override
    protected String folder() {
        return "dynamic";
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
