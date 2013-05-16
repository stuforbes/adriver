package uk.co.stuforbes.asyncdriver.action;

import org.hamcrest.SelfDescribing;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.actions.ClearTextAction;
import uk.co.stuforbes.asyncdriver.action.actions.ClickAction;
import uk.co.stuforbes.asyncdriver.action.actions.DoubleClickAction;
import uk.co.stuforbes.asyncdriver.action.actions.RightClickAction;
import uk.co.stuforbes.asyncdriver.action.actions.SelectOptionAction;
import uk.co.stuforbes.asyncdriver.action.actions.SubmitAction;
import uk.co.stuforbes.asyncdriver.action.actions.TypeKeyAction;
import uk.co.stuforbes.asyncdriver.action.actions.TypeTextAction;
import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.probe.ElementActionProbe;
import uk.co.stuforbes.asyncdriver.webdriver.WebElementLocator;

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


    public void click() {
        LOG.debug("About to click on {}", elementLocator);
        perform(new ClickAction());
    }


    public void doubleClick() {
        LOG.debug("About to double click on {}", elementLocator);
        perform(new DoubleClickAction(actions));
    }


    public void rightClick() {
        LOG.debug("About to right click on {}", elementLocator);
        perform(new RightClickAction(actions));
    }


    public void type(final String text) {
        LOG.debug("About to type text {} on {}", text, elementLocator);
        perform(new TypeTextAction(text));
    }


    public void type(final Keys key) {
        LOG.debug("About to type key {} on {}", key, elementLocator);
        perform(new TypeKeyAction(key));
    }


    public void clear() {
        LOG.debug("About to clear text on {}", elementLocator);
        perform(new ClearTextAction());
    }


    public void select(final String optionText) {
        LOG.debug("About to select {} in element {}", optionText, elementLocator);
        perform(new SelectOptionAction(optionText));
    }


    public void submit() {
        LOG.debug("About to submit {}", elementLocator);
        perform(new SubmitAction());
    }


    public void moveMouseOver() {
        LOG.debug("About to move mouse over {}", elementLocator);
        perform(new MouseOverAction(actions));
    }


    public void perform(final ElementAction action) {
        poller.doProbe(new ElementActionProbe(action, elementLocator, elementDescriber));
    }

}
