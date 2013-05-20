package uk.co.stfo.adriver.integration.event;

public class StaticEventActionIT extends AbstractEventActionIT {

    @Override
    protected String filename() {
        return "events.html";
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
