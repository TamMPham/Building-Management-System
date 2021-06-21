package bms.sensors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CarbonDioxideSensorTest {
    private CarbonDioxideSensor carbonDioxideSensor;

    @Before
    public void setUp(){
        carbonDioxideSensor = new CarbonDioxideSensor(new int[]{340, 220}, 4, 300, 120);
    }

    // CarbonDioxideSensor constructor test
    @Test
    public void carbonDioxideSensorConstructorTest(){
        int[] Readings = {340, 220};
        String expectedReadings = Arrays.toString(Readings);
        int expectedFrequency = 4;
        int expectedValue = 300;
        int expectedLimit = 120;

        Assert.assertEquals(expectedReadings, Arrays.toString(carbonDioxideSensor.sensorReadings));
        Assert.assertEquals(expectedFrequency, carbonDioxideSensor.getUpdateFrequency());
        Assert.assertEquals(expectedValue, carbonDioxideSensor.getIdealValue());
        Assert.assertEquals(expectedLimit, carbonDioxideSensor.getVariationLimit());
    }

    // Next three tests are for constructor's illegal argument exception test

    @Test(expected = IllegalArgumentException.class)
    public void idealValueIllegalArgumentTest(){
        CarbonDioxideSensor cSensor = new CarbonDioxideSensor(new int[] {330, 250}, 5, 0, 150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void varLimitIllegalArgumentTest(){
        CarbonDioxideSensor cSensor = new CarbonDioxideSensor(new int[] {330, 250}, 5, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idealAndVarLimitIllegalArgumentTest(){
        CarbonDioxideSensor cSensor = new CarbonDioxideSensor(new int[] {330, 250}, 5, 120, 150);
    }

    @Test
    public void getVariationLimitTest(){
        int expectedLimit = 120;

        Assert.assertEquals(expectedLimit, carbonDioxideSensor.variationLimit);
    }

    @Test
    public void getIdealValueTest(){
        int expectedValue = 300;

        Assert.assertEquals(expectedValue, carbonDioxideSensor.getIdealValue());
    }

    @Test
    public void getHazardLevelTest(){
        int expectedHazard = 0;

        Assert.assertEquals(expectedHazard, carbonDioxideSensor.getHazardLevel());
    }

    @Test
    public void toStringTest(){
        String expectedString = "TimedSensor: freq=4, readings=340,220, type=CarbonDioxideSensor, idealPPM=300, varLimit=120";

        Assert.assertEquals(expectedString, carbonDioxideSensor.toString());
    }
}
