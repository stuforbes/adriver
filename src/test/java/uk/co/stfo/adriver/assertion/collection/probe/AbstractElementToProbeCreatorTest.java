package uk.co.stfo.adriver.assertion.collection.probe;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.Probe;

@Ignore
public abstract class AbstractElementToProbeCreatorTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private ElementToProbeCreator probeCreator;


    @Before
    public void initialise() {
        createDependencies(context);

        this.probeCreator = initialiseProbeCreator();
    }


    @Test
    public void createForCreatesAHasAttributeProbe() {
        final Element element = context.mock(Element.class);

        final Probe probe = probeCreator.createFor(element);

        assertThat(probe, is(instanceOf(requiredProbeClass())));
    }


    @Test
    public void describeToUpdatesTheDescriptionWithAppropriateText() {

        final Description description = new StringDescription();

        setDescriptionExpectations(context, description);

        probeCreator.describeTo(description);

        assertThat(description.toString(), is(expectedDescription()));
    }


    protected abstract ElementToProbeCreator initialiseProbeCreator();


    protected abstract Class<? extends Probe> requiredProbeClass();


    protected abstract String expectedDescription();


    protected void createDependencies(final JUnitRuleMockery context) {
        // No-op
    }


    protected void setDescriptionExpectations(final JUnitRuleMockery context, final Description description) {
        // No-op
    }
}
