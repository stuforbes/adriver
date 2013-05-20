package uk.co.stfo.adriver.integration.collection;

public class StaticCollectionIT extends AbstractCollectionIT {

    @Override
    protected String filename() {
        return "collection.html";
    }


    @Override
    protected String folder() {
        return "static";
    }


    @Override
    protected long timeout() {
        return 50;
    }


    @Override
    protected long pollFreq() {
        return 10;
    }
}
