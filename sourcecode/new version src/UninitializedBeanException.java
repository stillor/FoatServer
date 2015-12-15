package custom.beans;

/**
 * This exception is thrown when trying to call some methods on an
 * uninitialized instance of a Value Bean that need initialization(done all setter methods)
 */
public class UninitializedBeanException extends Exception {

    public UninitializedBeanException(String message) {
        super(message);
    }
}
