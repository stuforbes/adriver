package uk.co.stfo.adriver.element.collection.size;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CollectionSizesTest {

    @Test
    public void equalToReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.equalTo(3), 3, true);
    }


    @Test
    public void equalToReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.equalTo(3), 2, false);
        checkResultIs(CollectionSizes.equalTo(2), 3, false);
        checkResultIs(CollectionSizes.equalTo(0), 100, false);
    }


    @Test
    public void notEqualToReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.notEqualTo(3), 2, true);
        checkResultIs(CollectionSizes.notEqualTo(2), 3, true);
        checkResultIs(CollectionSizes.notEqualTo(0), 100, true);
    }


    @Test
    public void notEqualToReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.notEqualTo(3), 3, false);
    }


    @Test
    public void greaterThanReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.greaterThan(3), 4, true);
        checkResultIs(CollectionSizes.greaterThan(2), 3, true);
        checkResultIs(CollectionSizes.greaterThan(0), 100, true);
    }


    @Test
    public void greaterThanReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.greaterThan(3), 2, false);
        checkResultIs(CollectionSizes.greaterThan(1), 0, false);
        checkResultIs(CollectionSizes.greaterThan(0), 0, false);
        checkResultIs(CollectionSizes.greaterThan(3), 3, false);
    }


    @Test
    public void greaterThanOrEqualToReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(3), 4, true);
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(2), 3, true);
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(2), 2, true);
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(0), 100, true);
    }


    @Test
    public void greaterThanOrEqualToReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(3), 2, false);
        checkResultIs(CollectionSizes.greaterThanOrEqualTo(1), 0, false);
    }


    @Test
    public void lessThanReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.lessThan(4), 3, true);
        checkResultIs(CollectionSizes.lessThan(3), 2, true);
        checkResultIs(CollectionSizes.lessThan(100), 0, true);
    }


    @Test
    public void lessThanReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.lessThan(2), 3, false);
        checkResultIs(CollectionSizes.lessThan(0), 1, false);
        checkResultIs(CollectionSizes.lessThan(0), 0, false);
        checkResultIs(CollectionSizes.lessThan(3), 3, false);
    }


    @Test
    public void lessThanOrEqualToReturnsTrueForValidSizes() {
        checkResultIs(CollectionSizes.lessThanOrEqualTo(4), 3, true);
        checkResultIs(CollectionSizes.lessThanOrEqualTo(3), 2, true);
        checkResultIs(CollectionSizes.lessThanOrEqualTo(3), 3, true);
        checkResultIs(CollectionSizes.lessThanOrEqualTo(100), 0, true);
    }


    @Test
    public void lessThanOrEqualToReturnsFalseForInvalidSizes() {
        checkResultIs(CollectionSizes.lessThanOrEqualTo(2), 3, false);
        checkResultIs(CollectionSizes.lessThanOrEqualTo(0), 1, false);
    }


    private void checkResultIs(final CollectionSize collectionSize, final int size, final boolean expectedResult) {
        assertThat(collectionSize.isSatisfied(size), is(expectedResult));
    }
}
