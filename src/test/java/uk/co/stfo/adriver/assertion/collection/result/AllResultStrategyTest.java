package uk.co.stfo.adriver.assertion.collection.result;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AllResultStrategyTest {

    private ResultStrategy strategy;


    @Before
    public void initialise() {
        this.strategy = new AllResultStrategy();
    }


    @Test
    public void isSuccessFailsIfNoSuccesses() {
        assertThat(strategy.isSuccess(tallyOf(0, 4)), is(false));
    }


    @Test
    public void isSuccessFailsIfSingleFailure() {
        assertThat(strategy.isSuccess(tallyOf(1, 3)), is(false));
    }


    @Test
    public void isSuccessPassesIfAllSuccesses() {
        assertThat(strategy.isSuccess(tallyOf(4, 0)), is(true));
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
