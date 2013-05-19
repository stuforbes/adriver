package uk.co.stf.adriver.action;

import org.hamcrest.SelfDescribing;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.action.actions.ClearTextAction;
import uk.co.stf.adriver.action.actions.ClickAction;
import uk.co.stf.adriver.action.actions.DoubleClickAction;
import uk.co.stf.adriver.action.actions.MouseOverAction;
import uk.co.stf.adriver.action.actions.RightClickAction;
import uk.co.stf.adriver.action.actions.SelectOptionAction;
import uk.co.stf.adriver.action.actions.SubmitAction;
import uk.co.stf.adriver.action.actions.TypeKeyAction;
import uk.co.stf.adriver.action.actions.TypeTextAction;
import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.probe.ElementActionProbe;
import uk.co.stf.adriver.webdriver.WebElementLocator;

public class AsyncElementActions implements ElementActions {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncElementActions.class);

    private final Poller poller;
    private final WebElementLocator elementLocator;

    private final Actions actions;

    private final SelfDescribing elementDescriber;


    public AsyncElementActions(final Poller poller, final WebElementLocator elementLocator,
            final SelfDescribing elementDescriber, final Actions actions) {
        this.poller = poller;
        this.elementLocator = elementLocator;
        this.elementDescriber = elementDescriber;
        this.actions = actions;
    }


    @Override
    public void click() {
        LOG.debug("About to click on {}", elementLocator);
        perform(new ClickAction());
    }


    @Override
    public void doubleClick() {
        LOG.debug("About to double click on {}", elementLocator);
        perform(new DoubleClickAction(actions));
    }


    @Override
    public void rightClick() {
        LOG.debug("About to right click on {}", elementLocator);
        perform(new RightClickAction(actions));
    }


    @Override
    public void type(final String text) {
        LOG.debug("About to type text {} on {}", text, elementLocator);
        perform(new TypeTextAction(text));
    }


    @Override
    public void type(final Keys key) {
        LOG.debug("About to type key {} on {}", key, elementLocator);
        perform(new TypeKeyAction(key));
    }


    @Override
    public void clear() {
        LOG.debug("About to clear text on {}", elementLocator);
        perform(new ClearTextAction());
    }


    @Override
    public void select(final String optionText) {
        LOG.debug("About to select {} in element {}", optionText, elementLocator);
        perform(new SelectOptionAction(optionText));
    }


    @Override
    public void submit() {
        LOG.debug("About to submit {}", elementLocator);
        perform(new SubmitAction());
    }


    @Override
    public void moveMouseOver() {
        LOG.debug("About to move mouse over {}", elementLocator);
        perform(new MouseOverAction(actions));
    }


    @Override
    public void perform(final ElementAction action) {
        poller.doProbe(new ElementActionProbe(action, elementLocator, elementDescriber));
    }

}
