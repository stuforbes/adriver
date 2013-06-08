package uk.co.stfo.adriver.element.collection.size;

import org.hamcrest.Description;

/**
 * Predefined suite of {@link CollectionSize} implementations, that will cover
 * the majority of scenarios
 * 
 * @author sforbes
 * 
 */
public class CollectionSizes {

    /**
     * Return true if the number of items equals the defined size
     * 
     * @param size
     *            the number of items the collection must equal
     * @return true if the collection size equals size, otherwise false
     */
    public static CollectionSize equalTo(final int size) {
        return new CollectionSize() {

            @Override
            public boolean isSatisfied(final int currentSize) {
                return size == currentSize;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is equal to " + size;
            }
        };
    }


    /**
     * Return true if the number of items does not equal the defined size
     * 
     * @param size
     *            the number of items the collection must not equal
     * @return true if the collection size does not equal size, otherwise false
     */
    public static CollectionSize notEqualTo(final int size) {
        return new CollectionSize() {

            @Override
            public boolean isSatisfied(final int currentSize) {
                return size != currentSize;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(" not ");
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is not equal to " + size;
            }
        };
    }


    /**
     * Return true if the number of items is more than the defined size
     * 
     * @param size
     *            the number of items the collection must be greater than
     * @return true if the collection size is greater than size, otherwise false
     */
    public static CollectionSize greaterThan(final int size) {
        return new CollectionSize() {
            @Override
            public boolean isSatisfied(final int currentSize) {
                return currentSize > size;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(" more than ");
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is greater than " + size;
            }
        };
    }


    /**
     * Return true if the number of items is more than or equal to the defined
     * size
     * 
     * @param size
     *            the number of items the collection must be greater than or
     *            equal to
     * @return true if the collection size is greater than or equal to size,
     *         otherwise false
     */
    public static CollectionSize greaterThanOrEqualTo(final int size) {
        return new CollectionSize() {
            @Override
            public boolean isSatisfied(final int currentSize) {
                return currentSize >= size;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(" more than or equal to ");
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is greater than or equal to " + size;
            }
        };
    }


    /**
     * Return true if the number of items is less than the defined size
     * 
     * @param size
     *            the number of items the collection must be less than
     * @return true if the collection size is less than size, otherwise false
     */
    public static CollectionSize lessThan(final int size) {
        return new CollectionSize() {
            @Override
            public boolean isSatisfied(final int currentSize) {
                return currentSize < size;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(" less than ");
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is less than " + size;
            }
        };
    }


    /**
     * Return true if the number of items is less than or equal to the defined
     * size
     * 
     * @param size
     *            the number of items the collection must be less than or equal
     *            to
     * @return true if the collection size is less than or equal to size,
     *         otherwise false
     */
    public static CollectionSize lessThanOrEqualTo(final int size) {
        return new CollectionSize() {
            @Override
            public boolean isSatisfied(final int currentSize) {
                return currentSize <= size;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText(" less than or equal to ");
                description.appendText(Integer.toString(size));
            }


            @Override
            public String toString() {
                return " is less than or equal to " + size;
            }
        };
    }
}
