package uk.co.stfo.adriver.assertion.collection.result;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NoneResultStrategyTest {

    private ResultStrategy strategy;


    @Before
    public void initialise() {
        this.strategy = new NoneResultStrategy();
    }


    @Test
    public void isSuccessPassesIfNoSuccesses() {
        assertThat(strategy.isSuccess(tallyOf(0, 4)), is(true));
    }


    @Test
    public void isSuccessFailsIfSingleFailure() {
        assertThat(strategy.isSuccess(tallyOf(1, 3)), is(false));
    }


    @Test
    public void isSuccessFailsIfAllSuccesses() {
        assertThat(strategy.isSuccess(tallyOf(4, 0)), is(false));
    }


    private ResultTally tallyOf(final int success, final int failure) {
        return new ResultTally() {

            @Override
            public int successes() {
                return success;
            }


            @Override
            public int failures() {
                return failure;
            }
        };
    }
}
