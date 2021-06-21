package bms.building;

import bms.exceptions.DuplicateFloorException;
import bms.exceptions.FireDrillException;
import bms.exceptions.FloorTooSmallException;
import bms.exceptions.NoFloorBelowException;
import bms.floor.Floor;
import bms.room.RoomType;
import bms.util.FireDrill;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a building of floors, which in turn, contain rooms.
 * A building needs to manage and keep track of the floors that make up the
 * building.
 * A building can be evacuated, which causes all rooms on all floors within
 * the building to be evacuated.
 */
public class Building implements FireDrill {
    String name;
    // The list of floors in building
    private List<Floor> floorList;

    /**
     * Creates a new empty building with no rooms.
     *
     * @param name - name of this building, eg. "General Purpose South"
     */
    public Building(String name){
        this.name = name;
        this.floorList = new ArrayList<>();
    }

    /**
     * Returns the name of the building.
     *
     * @return name of this building
     */
    public String getName(){
        return this.name;
    }

    /**
     *Returns a new list containing all the floors in this building.
     * Adding or removing floors from this list should not affect the
     * building's internal list of floors.
     *
     * @return new list containing all floors in the building
     */
    public List<Floor> getFloors(){
        return this.floorList;
    }

    /**
     * Searches for the floor with the specified floor number.
     * Returns the corresponding Floor object, or null if the floor was not
     * found.
     *
     * @param floorNumber floor number of floor to search for
     * @return floor with the given number if found; null if not found
     */
    public Floor getFloorByNumber(int floorNumber){
        for (Floor f: this.floorList) {
            if(f.getFloorNumber() == floorNumber){
                return f;
            }
        }

        return null;
    }

    /**
     *Adds a floor to the building.
     * If the given arguments are invalid, the floor already exists, there is
     * no floor below, or the floor below does not have enough area to
     * support this floor, an exception should be thrown and no action should
     * be taken.
     *
     * @param newFloor object representing the new floor
     * @throws IllegalArgumentException if floor number is <= 0, width < Floor
     * .getMinWidth(), or length < Floor.getMinLength()
     * @throws DuplicateFloorException if a floor at this level already
     * exists in the building
     * @throws NoFloorBelowException if this is at level 2 or
     * above and there is no floor below to support this new floor
     * @throws FloorTooSmallException if this is at level 2 or above
     * and the floor below is not big enough to support this new floor
     */
    public void addFloor(Floor newFloor)throws IllegalArgumentException,
            DuplicateFloorException, NoFloorBelowException,
            FloorTooSmallException {
        if (newFloor.getFloorNumber() <= 0 ||
                newFloor.getWidth() < Floor.getMinWidth() ||
                newFloor.getLength() < Floor.getMinLength()){
            throw new IllegalArgumentException();
        } else if (newFloor.getFloorNumber() >= 2){
            if (getFloorByNumber(newFloor.getFloorNumber() - 1) == null){
                throw new NoFloorBelowException();
            }
            else if (getFloorByNumber(newFloor.getFloorNumber()-1)
                    .calculateArea() < newFloor.calculateArea()){
                throw new FloorTooSmallException();
            }
        }

        for(Floor f: this.floorList){
            if (newFloor == f){
                throw new DuplicateFloorException();
            }
        }
        this.floorList.add(newFloor);
    }

    @Override
    public void fireDrill(RoomType roomType) throws FireDrillException {
        // No floor in building
        if (this.floorList.size() == 0){
            throw new FireDrillException();
        }
        // No room on floor
        for (Floor f: this.floorList){
            if (f.getRooms().size() == 0){
                throw new FireDrillException();
            }
            f.fireDrill(roomType);
        }
    }

    /**
     * public void cancelFireDrill()
     * Cancels any ongoing fire drill in the building.
     * All rooms must have their fire alarm cancelled regardless of room type.
     */
    public void cancelFireDrill(){
        for (Floor f: this.floorList){
            f.cancelFireDrill();
        }
    }

    @Override
    public String toString(){
        return "Building: name=" + "\"" + this.name + "\"" + ", floors=" +
                this.floorList.size();
    }
}
