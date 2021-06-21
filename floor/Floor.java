package bms.floor;

import bms.exceptions.DuplicateRoomException;
import bms.exceptions.InsufficientSpaceException;
import bms.room.Room;
import bms.room.RoomType;
import bms.util.FireDrill;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a floor of a building.
 * All floors have a floor number (ground floor is floor 1), a list of rooms,
 * and a width and length.
 *
 * A floor can be evacuated, which causes all rooms on the floor to be
 * evacuated.
 */
public class Floor implements FireDrill {
    int floorNumber;
    double width;
    double length;

    // Two decimal places format
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    // List of rooms on the floor
    private List<Room> roomList;

    /**
     *Creates a new floor with the given floor number.

     * @param  floorNumber a unique floor number, corresponds to how many floors
     * above ground floor (inclusive)
     * @param width the width of the floor in metres
     * @param length the length of the floor in metres
     */
    public Floor(int floorNumber, double width, double length) {
        this.floorNumber = floorNumber;
        this.width = width;
        this.length = length;
        this.roomList = new ArrayList<>();
    }

    /**
     * Returns the floor number of this floor.
     *
     * @return floor number
     */
    public int getFloorNumber() {
        return this.floorNumber;
    }

    /**
     * Returns the minimum width for all floors.
     *
     * @return 5
     */
    public static int getMinWidth() {
        final int MIN_WIDTH = 5;
        return MIN_WIDTH;
    }

    /**
     * Returns the minimum length for all floors.
     *
     * @return 5
     */
    public static int getMinLength() {
        final int MIN_LENGTH = 5;
        return MIN_LENGTH;
    }

    /**
     * Returns a new list containing all the rooms on this floor.
     * Adding or removing rooms from this list should not affect the floor's
     * internal list of rooms.
     *
     * @return new list containing all rooms on the floor
     */
    public List<Room> getRooms() {
        return new ArrayList<>(this.roomList);
    }

    /**
     * Returns width of the floor.
     *
     * @return floor width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns length of the floor.
     *
     * @return floor length
     */
    public double getLength() {
        return this.length;
    }

    /**
     *Search for the room with the specified room number.
     * Returns the corresponding Room object, or null if the room was not found.
     *
     * @param roomNumber - room number of room to search for
     *
     * @return room with the given number if found; null if not found
     */
    public Room getRoomByNumber(int roomNumber) {
        // Check if roomNumber matches any existing rooms
        for (Room r : this.roomList) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }

        return null;
    }

    /**
     * Calculates the area of the floor in square metres.
     * The area should be calculated as getWidth() multiplied by getLength().
     *
     * For example, a floor with a length of 20.5 and width of 35.2, would be
     * 721.6 square metres.
     *
     * @return area of the floor in square metres
     */
    public double calculateArea() {
        return getWidth() * getLength();
    }

    /**
     *Calculates the area of the floor which is currently occupied by all the
     *  rooms on the floor.
     *
     * @return area of the floor that is currently occupied, in square metres
     */
    public float occupiedArea() {
        float totalRoomArea = 0;

        for (Room r : this.roomList) {
            totalRoomArea += r.getArea();
        }
        return totalRoomArea;
    }

    /**
     *Adds a room to the floor.
     * The dimensions of the room are managed automatically. The length and
     * width of the room do not need to be specified, only the required space.
     *
     * @param newRoom object representing the new room
     * @throws IllegalArgumentException if area is less than Room.getMinArea()
     * @throws DuplicateRoomException if the room number on this
     * floor is already taken
     * @throws InsufficientSpaceException if there is insufficient space
     * available on the floor to be able to add the room
     */
    public void addRoom(Room newRoom) throws DuplicateRoomException,
            InsufficientSpaceException {
        if (newRoom.getArea() < Room.getMinArea()) {
            throw new IllegalArgumentException();
        } else if ((calculateArea() - occupiedArea()) < newRoom.getArea()) {
            throw new InsufficientSpaceException();
        }

        for (Room r : this.roomList) {
            if (r.getRoomNumber() == newRoom.getRoomNumber()) {
                throw new DuplicateRoomException();
            }
        }

        this.roomList.add(newRoom);
    }

    @Override
    public void fireDrill(RoomType roomType) {
        // Starts fire drill in all rooms
        if (roomType == null) {
            for (Room r : this.roomList) {
                r.setFireDrill(true);
            }
        // Starts fire drill in given room type
        } else {
            for (Room r : this.roomList) {
                if (roomType == r.getType()) {
                    r.setFireDrill(true);
                }
            }
        }
    }

    /**
     * Cancels any ongoing fire drill in rooms on the floor.
     * All rooms must have their fire alarm cancelled regardless of room type.
     */
    public void cancelFireDrill() {
        for (Room r : this.roomList) {
            r.setFireDrill(false);
        }
    }

    @Override
    public String toString(){
        String widthRound = DECIMAL_FORMAT.format(this.width);
        String lengthRound = DECIMAL_FORMAT.format(this.length);

        return "Floor #" + this.floorNumber + ": width=" + widthRound + "m," +
                " length=" + lengthRound + "m, rooms=" + this.roomList.size();
    }

}
