package bms.exceptions;

/**
 * Exception thrown when a room is added to a floor where there is insufficient
 * space available to be able to add the room.
 */
public class InsufficientSpaceException extends Exception {
    /**
     * Constructs a normal InsufficientSpaceException with no error
     * message or cause.
     */
    public InsufficientSpaceException(){
    }

    /**
     * Constructs a InsufficientSpaceException that contains a helpful
     * message detailing why the exception occurred.
     *
     * @param message detail message
     */
    public InsufficientSpaceException(String message){
        super(message);
    }
}
