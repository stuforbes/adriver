package uk.co.stfo.adriver.integration.assertion;

public class StaticAssertionIT extends AbstractAssertionIT {

    @Override
    protected String filename() {
        return "basic.html";
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
