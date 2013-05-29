package uk.co.stfo.adriver.integration.collection.assertion;


public class StaticNoneCollectionAssertionIT extends AbstractNoneCollectionAssertionIT {

    @Override
    protected String filename() {
        return "collection-assertion.html";
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
