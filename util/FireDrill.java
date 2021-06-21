package bms.util;

import bms.exceptions.FireDrillException;
import bms.room.RoomType;

/**
 * Denotes a class containing a routine to carry out fire drills on rooms
 * of a given type.
 */
public interface FireDrill {
    /**
     * Carries out fire drills for a given room type.
     *
     * @param roomType type of room
     * @throws FireDrillException when trying to call a fire
     * drill on a building with no rooms in it on any floors.
     */
    void fireDrill(RoomType roomType) throws FireDrillException;
}
