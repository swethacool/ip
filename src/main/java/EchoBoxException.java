/**
 * Custom exception class for handling errors specific to the EchoBox application.
 * It extends the built-in `Exception` class to provide more meaningful exception messages.
 */
public class EchoBoxException extends Exception {

    /**
     * Constructs a new EchoBoxException with the specified detail message.
     * @param message The detail message to be passed to the Exception constructor.
     */
    public EchoBoxException(String message) {
        super(message);  // Call the constructor of the Exception class to set the error message
    }
}