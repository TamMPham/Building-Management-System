package bms.floor;

import bms.exceptions.DuplicateRoomException;
import bms.exceptions.InsufficientSpaceException;
import bms.room.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static bms.room.RoomType.OFFICE;
import static bms.room.RoomType.STUDY;
import static org.hamcrest.CoreMatchers.hasItems;

public class FloorTest {
    private Floor floor;

    @Before
    public void setUp() {
        floor = new Floor(2, 9, 6);
    }

    @Test
    public void floorConstructorTest(){
        int expectedFloorNumber = 2;
        double expectedWidth = 9;
        double expectedLength = 6;

        Assert.assertEquals(expectedFloorNumber, floor.getFloorNumber());
        Assert.assertEquals(expectedWidth, floor.getWidth(),0);
        Assert.assertEquals(expectedLength, floor.getLength(),0);
    }

    @Test
    public void getFloorNumberTest(){
        int floorNumber = 2;

        Assert.assertEquals(floorNumber, floor.getFloorNumber());
    }

    @Test
    public void getMinWidthTest(){
        int minWidth = 5;

        Assert.assertEquals(minWidth, Floor.getMinWidth());
    }

    @Test
    public void getMinLengthTest(){
        int minLength = 5;

        Assert.assertEquals(minLength, Floor.getMinWidth());
    }

    @Test
    public void getRoomsTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(4, OFFICE, 9);
        Room newRoom2 = new Room(3, STUDY, 6);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);

        List<Room> expectedRoomList = new ArrayList<>();
        expectedRoomList.add(newRoom);
        expectedRoomList.add(newRoom2);

        Assert.assertEquals(expectedRoomList, floor.getRooms());
    }

    @Test
    public void getWidthTest(){
        double expectedFloorWidth = 9;

        Assert.assertEquals(expectedFloorWidth, floor.getWidth(),0);
    }

    @Test
    public void getLengthTest(){
        double expectedFloorLength = 6;

        Assert.assertEquals(expectedFloorLength, floor.getLength(),0);
    }

    @Test
    public void getRoomByNumberTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(4, OFFICE, 9);
        floor.addRoom(newRoom);
        int roomNumber = 4;

        Assert.assertEquals(newRoom, floor.getRoomByNumber(roomNumber));
    }

    // Null input case for getRooms method
    @Test
    public void getRoomByNumberNullTest(){
        Assert.assertNull(floor.getRoomByNumber(4));
    }

    @Test
    public void calculateAreaTest(){
        double expectedArea = 9 * 6;

        Assert.assertEquals(expectedArea, floor.calculateArea(),0);
    }

    @Test
    public void occupiedArea() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(4, OFFICE, 9.2);
        floor.addRoom(newRoom);

        float expectedOccupiedArea = (float) 9.2;

        Assert.assertEquals(expectedOccupiedArea, floor.occupiedArea(), 0);
    }

    // Next 4 tests are for addRoom method
    @Test(expected = IllegalArgumentException.class)
    public void addRoomIllegalArgumentTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 4);
        floor.addRoom(newRoom);
    }

    @Test(expected = DuplicateRoomException.class)
    public void addRoomDuplicateRoomTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 6);
        Room newRoom2 = new Room(2, STUDY, 5);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);
    }

    @Test(expected = InsufficientSpaceException.class)
    public void addRoomInsufficientSpaceTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 37);
        Room newRoom2 = new Room(3, STUDY, 30);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);
    }

    @Test
    public void addRoomNoExceptionTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 10);

        floor.addRoom(newRoom);

        Assert.assertThat(floor.getRooms(), hasItems(newRoom));
    }

    @Test
    public void fireDrillTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 6);
        Room newRoom2 = new Room(3, OFFICE, 7);
        Room newRoom3 = new Room(4, STUDY, 8);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);
        floor.addRoom(newRoom3);

        floor.fireDrill(STUDY);

        final boolean fireDrill = true;
        final boolean fireDrill2 = false;
        final boolean fireDrill3 = true;

        Assert.assertEquals(fireDrill, newRoom.fireDrillOngoing());
        Assert.assertEquals(fireDrill2, newRoom2.fireDrillOngoing());
        Assert.assertEquals(fireDrill3, newRoom3.fireDrillOngoing());
    }

    @Test
    public void fireDrillNullTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 6);
        Room newRoom2 = new Room(3, OFFICE, 7);
        Room newRoom3 = new Room(4, STUDY, 8);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);
        floor.addRoom(newRoom3);

        floor.fireDrill(null);

        final boolean fireDrill = true;
        final boolean fireDrill2 = true;
        final boolean fireDrill3 = true;

        Assert.assertEquals(fireDrill, newRoom.fireDrillOngoing());
        Assert.assertEquals(fireDrill2, newRoom2.fireDrillOngoing());
        Assert.assertEquals(fireDrill3, newRoom3.fireDrillOngoing());
    }

    @Test
    public void cancelFireDrillTest() throws InsufficientSpaceException, DuplicateRoomException {
        Room newRoom = new Room(2, STUDY, 6);
        Room newRoom2 = new Room(3, OFFICE, 7);
        Room newRoom3 = new Room(4, STUDY, 8);

        floor.addRoom(newRoom);
        floor.addRoom(newRoom2);
        floor.addRoom(newRoom3);

        newRoom.setFireDrill(true);
        newRoom2.setFireDrill(true);
        newRoom3.setFireDrill(true);

        floor.cancelFireDrill();

        final boolean fireDrill = false;
        final boolean fireDrill2 = false;
        final boolean fireDrill3 = false;

        Assert.assertEquals(fireDrill, newRoom.fireDrillOngoing());
        Assert.assertEquals(fireDrill2, newRoom2.fireDrillOngoing());
        Assert.assertEquals(fireDrill3, newRoom3.fireDrillOngoing());
    }

    @Test
    public void toStringTest(){
        String expectedString = "Floor #2: width=9.00m, length=6.00m, rooms=0";

        Assert.assertEquals(expectedString, floor.toString());
    }


}
