package uk.co.stfo.adriver.element.collection;

import uk.co.stfo.adriver.element.Element;

/**
 * Factory methods that create various common use {@link ElementOperator}s
 * 
 * @author sforbes
 * 
 */
public class ElementOperators {

    /**
     * Clicks on any element that is invoked.
     * 
     * @return An {@link ElementOperator} that clicks on any {@link Element} it
     *         is called with
     */
    public static ElementOperator click() {
        return new ElementOperator() {

            @Override
            public void doWith(final Element element) {
                element.perform().click();
            }
        };
    }
}
