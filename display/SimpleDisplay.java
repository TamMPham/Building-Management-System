package bms.display;

import bms.building.Building;
import bms.exceptions.*;
import bms.floor.Floor;
import bms.room.Room;
import bms.room.RoomType;
import bms.sensors.*;
/**
 * A class to create and display a building managed by the BMS.
 * <p>
 * This can be used to assist in the debugging and visualisation of your
 * buildings and will <b>not</b> be assessed.
 * <p>
 * It is <b>highly recommended</b> you use this sparingly for visualisation
 * purposes and instead write JUnit tests for the majority of your testing.
 */
public class SimpleDisplay {

    /**
     * Creates a building containing floors, rooms and sensors.
     *
     * @return a populated building
     */
    private static Building createBuilding() {
        /*
        Change the building, floor and sensors details below appropriately to
         get different display when running the program
         */
        Building building = new Building("General Purpose South");

        Floor floor1 = new Floor(1, 10, 10);
        Room room1 = new Room(101, RoomType.STUDY, 20);
        Room room2 = new Room(102, RoomType.STUDY, 20);
        Room room3 = new Room(103, RoomType.STUDY, 15);
        Room room4 = new Room(104, RoomType.LABORATORY, 45);
        Room room5;
        try {
            floor1.addRoom(room1);
            floor1.addRoom(room2);
            floor1.addRoom(room3);
            floor1.addRoom(room4);
        } catch (DuplicateRoomException | InsufficientSpaceException e) {
            e.printStackTrace();
        }
        try {
            room2.addSensor(
                    new OccupancySensor(new int[] {32, 31, 28}, 4, 30));
            room4.addSensor(
                    new CarbonDioxideSensor(new int[] {690, 740}, 5, 700, 150));
        } catch (DuplicateSensorException e) {
            e.printStackTrace();
        }

        Floor floor2 = new Floor(2, 10, 10);
        room1 = new Room(201, RoomType.OFFICE, 50);
        room2 = new Room(202, RoomType.OFFICE, 30);
        try {
            floor2.addRoom(room1);
            floor2.addRoom(room2);
        } catch (DuplicateRoomException | InsufficientSpaceException e) {
            e.printStackTrace();
        }
        try {
            room1.addSensor(
                    new NoiseSensor(new int[] {35, 38}, 3));
        } catch (DuplicateSensorException e) {
            e.printStackTrace();
        }

        Floor floor3 = new Floor(3, 10, 8);
        room1 = new Room(301, RoomType.STUDY, 30);
        room2 = new Room(302, RoomType.LABORATORY, 25);
        room3 = new Room(303, RoomType.LABORATORY, 25);
        try {
            floor3.addRoom(room1);
            floor3.addRoom(room2);
            floor3.addRoom(room3);
        } catch (DuplicateRoomException | InsufficientSpaceException e) {
            e.printStackTrace();
        }
        try {
            room2.addSensor(
                    new TemperatureSensor(new int[] {25, 26, 24}));
            room3.addSensor(
                    new TemperatureSensor(new int[] {24, 21}));
        } catch (DuplicateSensorException e) {
            e.printStackTrace();
        }

        Floor floor4 = new Floor(4, 10, 5);
        room1 = new Room(401, RoomType.OFFICE, 20);
        room2 = new Room(402, RoomType.OFFICE, 10);
        room3 = new Room(403, RoomType.OFFICE, 10);
        try {
            floor4.addRoom(room1);
            floor4.addRoom(room2);
            floor4.addRoom(room3);
        } catch (DuplicateRoomException | InsufficientSpaceException e) {
            e.printStackTrace();
        }

        Floor floor5 = new Floor(5, 8, 5);
        room1 = new Room(501, RoomType.LABORATORY, 30);
        try {
            floor5.addRoom(room1);
        } catch (DuplicateRoomException | InsufficientSpaceException e) {
            e.printStackTrace();
        }
        try {
            room1.addSensor(
                    new TemperatureSensor(new int[] {25, 34, 61, 85}));
            room1.addSensor(
                    new OccupancySensor(new int[] {15, 12, 2, 0}, 1, 20));
        } catch (DuplicateSensorException e) {
            e.printStackTrace();
        }

        try {
            building.addFloor(floor1);
            building.addFloor(floor2);
            building.addFloor(floor3);
            building.addFloor(floor4);
            building.addFloor(floor5);
        } catch (DuplicateFloorException | NoFloorBelowException |
                FloorTooSmallException e) {
            e.printStackTrace();
        }


        return building;
    }

    /**
     * Outputs the given building to the console by calling the toString methods
     * in Building, Floor, Room and TimedSensor.
     *
     * You can modify this method if you wish, but it is not necessary.
     *
     * @param building building to display
     */
    private static void displayBuilding(Building building) {
        System.out.println(building.toString());
        for (Floor floor : building.getFloors()) {
            System.out.println("\t" + floor.toString());
            for (Room room : floor.getRooms()) {
                System.out.println("\t\t" + room.toString());
                for (Sensor sensor : room.getSensors()) {
                    System.out.println("\t\t\t" + sensor.toString());
                }
            }
        }
    }

    /**
     * Uses the code in createBuilding() to instantiate a Building class
     * containing Floors, Rooms and Sensors, then prints those elements to the
     * console.
     *
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        displayBuilding(createBuilding());
    }
}
