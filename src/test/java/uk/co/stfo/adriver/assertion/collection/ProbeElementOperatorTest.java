package uk.co.stfo.adriver.assertion.collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.Probe;

public class ProbeElementOperatorTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Probe probe;
    private ElementToProbeCreator probeCreator;

    private ProbeElementOperator operator;


    @Before
    public void initialise() {

        this.probe = context.mock(Probe.class);
        this.probeCreator = context.mock(ElementToProbeCreator.class);

        this.operator = new ProbeElementOperator(probeCreator);
    }


    @Test
    public void reportsThatProbeIsNotSatisfiedIfNotRun() {

        assertThat(operator.isProbeSatisfied(), is(false));
    }


    @Test
    public void reportsThatProbeIsNotSatisfiedIfNotSatisfied() {

        final Element element = context.mock(Element.class);

        context.checking(new Expectations() {
            {
                oneOf(probeCreator).createFor(element);
                will(returnValue(probe));

                oneOf(probe).doProbe();

                oneOf(probe).isSatisfied();
                will(returnValue(false));
            }
        });

        operator.doWith(element);

        assertThat(operator.isProbeSatisfied(), is(false));
    }


    @Test
    public void reportsThatProbeIsSatisfiedIfSatisfied() {
        final Element element = context.mock(Element.class);

        context.checking(new Expectations() {
            {
                oneOf(probeCreator).createFor(element);
                will(returnValue(probe));

                oneOf(probe).doProbe();

                oneOf(probe).isSatisfied();
                will(returnValue(true));
            }
        });

        operator.doWith(element);
        assertThat(operator.isProbeSatisfied(), is(true));
    }
}
