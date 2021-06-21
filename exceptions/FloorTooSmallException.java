package bms.exceptions;

/**
 * Exception thrown when a floor is added to a building but the area of the
 * floor below is smaller than the area of the new floor.
 * In other words, the floor does not have the required supporting structure
 * underneath (note that no overhanging floors are allowed).
 */
public class FloorTooSmallException extends Exception {
    /**
     * Constructs a normal FloorBelowTooSmallException with no error
     * message or cause.
     */
    public FloorTooSmallException(){
    }

    /**
     * Constructs a FloorTooSmallException that contains a helpful message
     * detailing why the exception occurred.
     *
     * @param message detail message
     */
    public FloorTooSmallException(String message){
        super(message);
    }
}
