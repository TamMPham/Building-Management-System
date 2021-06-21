package bms.room;

import bms.exceptions.DuplicateSensorException;
import bms.sensors.Sensor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room on a floor of a building.
 * Each room has a room number (unique for this floor, ie. no two rooms on
 * the same floor can have the same room number), a type to indicate its
 * intended purpose, and a total area occupied by the room in square metres.
 *
 * Rooms also need to record whether a fire drill is currently taking place
 * in the room.
 *
 * Rooms can have one or more sensors to monitor hazard levels in the room.
 */
public class Room {
    int roomNumber;
    RoomType type;
    double area;

    // Two decimal places format
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    // List of sensors in the room
    private List<Sensor> sensorList;

    // Whether the fire drill is on or not
    private boolean fireDrillState;

    /**
     * Creates a new room with the given room number.
     *
     * @param roomNumber the unique room number of the room on this floor
     * @param type the type of room
     * @param area the area of the room in square metres
     */
    public Room(int roomNumber, RoomType type, double area){
        this.roomNumber = roomNumber;
        this.type = type;
        this.area = area;
        this.sensorList = new ArrayList<>();
    }

    /**
     * Returns room number of the room.
     *
     * @return the room number on the floor
     */
    public int getRoomNumber(){
        return this.roomNumber;
    }

    /**
     *Returns area of the room.
     *
     * @return the room area in square metres
     */
    public double getArea(){
        return this.area;
    }

    /**
     *Returns the minimum area for all rooms.
     * Rooms must be at least 5 square metres in area.
     *
     * @return the minimum room area in square metres
     */
    public static int getMinArea(){
        final int MIN_AREA = 5;
        return MIN_AREA;
    }

    /**
     * Returns the type of the room.
     *
     * @return the room type
     */
    public RoomType getType(){
    return this.type;
    }

    /**
     * Returns whether there is currently a fire drill in progress.
     *
     * @return current status of fire drill
     */
    public boolean fireDrillOngoing(){
        return fireDrillState;
    }

    /**
     * Returns the list of sensors in the room.
     * The list of sensors stored by the room should always be in
     * alphabetical order, by the sensor's class name.
     *
     * Adding or removing sensors from this list should not affect the room's
     * internal list of sensors.
     *
     * @return list of all sensors in alphabetical order of class name
     */
    public List<Sensor> getSensors(){
        List<Sensor> sensorListCopy = new ArrayList<>(this.sensorList);
        // Comparing class name to sort alphabetically
        sensorListCopy.sort((sensor1, sensor2) -> {
            if (sensor1.getClass().getSimpleName().equals
                    (sensor2.getClass().getSimpleName())){
                return 0;
            }
            return sensor1.getClass().getSimpleName().compareTo
                    (sensor2.getClass().getSimpleName());
        });

        return sensorListCopy;
    }

    /**
     * Change the status of the fire drill to the given value.
     *
     * @param fireDrill - whether there is a fire drill ongoing
     */
    public void setFireDrill(boolean fireDrill){
        fireDrillState = fireDrill;
    }

    /**
     * Return the given type of sensor if there is one in the list of
     * sensors; return null otherwise.
     *
     * @param sensorType - the type of sensor which matches the class name
     * returned by the getSimpleName() method, e.g. "NoiseSensor" (no quotes)
     *
     * @return the sensor in this room of the given type; null if none found
     */
    public Sensor getSensor(String sensorType){
        // Check for matching sensor type in list
        for (Sensor s: this.sensorList){
            if (sensorType.equals(s.getClass().getSimpleName())){
                return s;
            }
        }

        return null;
    }

    /**
     * Adds a sensor to the room if a sensor of the same type is not already
     * in the room.
     * The list of sensors should be sorted after adding the new sensor, in
     * alphabetical order by simple class name (Class.getSimpleName()).
     *
     *
     * @param sensor - the sensor to add to the room
     *
     * @throws DuplicateSensorException - if the sensor to add is of the same
     * type as a sensor already in this room
     */
    public void addSensor(Sensor sensor) throws DuplicateSensorException {
        for (Sensor s: this.sensorList){
            if (sensor == s){
                throw new DuplicateSensorException();
            }
        }

        this.sensorList.add(sensor);
        // Comparing class names to sort alphabetically
        this.sensorList.sort((sensor1, sensor2) -> {
        if (sensor1.getClass().getSimpleName().equals
                (sensor2.getClass().getSimpleName())){
            return 0;
        }
        return sensor1.getClass().getSimpleName().compareTo
                (sensor2.getClass().getSimpleName());
    });
    }

    @Override
    public String toString(){
        String areaRound = DECIMAL_FORMAT.format(this.area);

        return "Room #" + this.roomNumber + ": type=" + this.type + ", area=" +
                areaRound + "m^2, sensors=" + this.sensorList.size();
    }
}

