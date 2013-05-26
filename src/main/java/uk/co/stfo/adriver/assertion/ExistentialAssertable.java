package uk.co.stfo.adriver.assertion;

/**
 * Interface used to assert the existence, or absence of items
 * 
 * @author sforbes
 * 
 */
public interface ExistentialAssertable {

    /**
     * Does the item under test exist
     */
    void doesExist();


    /**
     * Is the item under test absent
     */
    void doesNotExist();
}
