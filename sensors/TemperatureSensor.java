package bms.sensors;

import java.util.Arrays;

/**
 * A sensor that measures ambient temperature in a room.
 */
public class TemperatureSensor extends TimedSensor implements HazardSensor{
    /**
     * Creates a new temperature sensor with the given sensor readings and
     * update frequency.
     * For safety reasons, all temperature sensors must have an update
     * frequency of 1 minute.
     *
     * @param sensorReadings a non-empty array of sensor readings
     */
    public TemperatureSensor(int[] sensorReadings){
        super(sensorReadings, 1);
    }

    @Override
    public int getHazardLevel() {
        final int HAS_HAZARD = 100;
        final int NO_HAZARD = 0;
        // Higher or equal to 68 degree
        if (getCurrentReading() >= 68){
            return HAS_HAZARD;
        }
        return NO_HAZARD;
    }

    @Override
    public String toString(){
        String sensorReadingsConverted = Arrays.toString(sensorReadings);
        // Removing square brackets at beginning and end
        sensorReadingsConverted = sensorReadingsConverted.substring
                (1, sensorReadingsConverted.length()-1);
        // Removing white spaces
        sensorReadingsConverted = sensorReadingsConverted.replaceAll
                ("\\s+", "");

        return "TimedSensor: freq=" + this.updateFrequency + ", readings=" +
                sensorReadingsConverted + ", type=TemperatureSensor";
    }

}
