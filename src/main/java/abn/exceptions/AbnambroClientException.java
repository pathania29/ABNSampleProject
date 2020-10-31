package abn.exceptions;

/**
 * Custom Exception
 */
public class AbnambroClientException extends RuntimeException {

    public AbnambroClientException(String errorMessage) {
        super(errorMessage);
    }

}
