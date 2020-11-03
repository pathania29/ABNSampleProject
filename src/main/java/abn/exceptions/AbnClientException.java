package abn.exceptions;

/**
 * Custom Exception
 */
public class AbnClientException extends RuntimeException {

    public AbnClientException(String message) {
        super(message);
    }

    public AbnClientException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
