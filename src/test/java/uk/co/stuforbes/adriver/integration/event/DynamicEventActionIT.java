package uk.co.stuforbes.adriver.integration.event;

public class DynamicEventActionIT extends AbstractEventActionIT {

    @Override
    protected String filename() {
        return "events.html";
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
