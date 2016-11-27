package myinjector.Exceptions;

public class BindingNotFoundException extends RuntimeException {
    public BindingNotFoundException(String message) {
        super(message);
    }
}
