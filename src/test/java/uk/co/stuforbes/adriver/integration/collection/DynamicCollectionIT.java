package uk.co.stuforbes.adriver.integration.collection;

public class DynamicCollectionIT extends AbstractCollectionIT {

    @Override
    protected String filename() {
        return "collection.html";
    }


    @Override
    protected String folder() {
        return "dynamic";
    }


    @Override
    protected long timeout() {
        return 1200;
    }


    @Override
    protected long pollFreq() {
        return 100;
    }
}
