package CustomException;

public class TooManyElementsException extends RuntimeException {

    public TooManyElementsException(String message) {
        super(message);
    }
}
