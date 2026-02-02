package by.tanyab.task1.exception;

public class CustomArrayException extends Exception {
    public CustomArrayException(String message) {
        super(message);
    }
    public CustomArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}