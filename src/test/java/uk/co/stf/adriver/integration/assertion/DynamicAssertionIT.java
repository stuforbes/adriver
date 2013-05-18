package uk.co.stf.adriver.integration.assertion;

public class DynamicAssertionIT extends AbstractAssertionIT {

    @Override
    protected String filename() {
        return "basic.html";
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
