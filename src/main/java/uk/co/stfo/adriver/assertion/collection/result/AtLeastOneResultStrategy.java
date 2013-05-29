package uk.co.stfo.adriver.assertion.collection.result;

import java.util.List;

import org.hamcrest.Description;

import uk.co.stfo.adriver.element.Element;

public class AtLeastOneResultStrategy implements ResultStrategy {

    @Override
    public boolean isSuccess(final ResultTally resultTally) {
        return resultTally.successes() > 0;
    }


    @Override
    public String descriptionPrefix() {
        return "At least one child";
    }


    @Override
    public void reportFailureTo(final List<Element> successElements, final List<Element> failureElements,
            final Description description) {
        description.appendText("The following elements were not valid: ");
        for (final Element element : failureElements) {
            description.appendText("\n\t");
            description.appendDescriptionOf(element);
        }
    }
}
