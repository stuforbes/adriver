package uk.co.stfo.adriver.integration.form;

public class StaticFormActionIT extends AbstractFormActionIT {

    @Override
    protected String filename() {
        return "form.html";
    }


    @Override
    protected String folder() {
        return "static";
    }


    @Override
    protected long timeout() {
        return 1000;
    }


    @Override
    protected long pollFreq() {
        return 10;
    }
}
