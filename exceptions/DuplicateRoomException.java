package bms.exceptions;

/**
 * Exception thrown when a room is added to a floor, but the room number
 * already taken on that floor.
 */
public class DuplicateRoomException extends Exception {
    /**
     * Constructs a normal DuplicateRoomException with no error
     * message or cause.
     */
    public DuplicateRoomException(){
    }

    /**
     * Constructs a DuplicateRoomException that contains a helpful message
     * detailing why the exception occurred.
     *
     * @param message detail message
     */
    public DuplicateRoomException(String message){
        super(message);
    }
}
