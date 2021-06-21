package bms.exceptions;

/**
 * Exception thrown when a floor is added to a building but the floor is at level
 * 2 or above (i.e. not ground level) and there is no supporting floor below that
 * is already in place.
 */
public class NoFloorBelowException extends Exception {
    /**
     * Constructs a normal DuplicateFloorException with no error
     * message or cause.
     */
    public NoFloorBelowException(){
    }

    /**
     * Constructs a NoFloorBelowException that contains a helpful message
     * detailing why the exception occurred.
     *
     * @param message detail message
     */
    public NoFloorBelowException(String message){
        super(message);
    }
}
