package bms.sensors;

import java.util.Arrays;

/**
 * A sensor that measures the number of people in a room.
 */
public class OccupancySensor extends TimedSensor implements HazardSensor {
    int capacity;

    /**
     * Creates a new occupancy sensor with the given sensor readings, update
     * frequency and capacity.
     * The given capacity must be greater than or equal to zero.
     *
     * @param sensorReadings a non-empty array of sensor readings
     * @param updateFrequency indicates how often the sensor readings update, in
     * minutes
     * @param capacity maximum allowable number of people in the room
     *
     * @throws IllegalArgumentException if capacity is less than zero
     */
    public OccupancySensor(int[] sensorReadings, int updateFrequency,
                           int capacity){
        super(sensorReadings, updateFrequency);
        this.capacity = capacity;

        if (this.capacity < 0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the capacity of this occupancy sensor.
     *
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

    @Override
    public int getHazardLevel() {
        final int MAX_HAZARD = 100;
        // Relative reading to max capacity
        double hazardRatio = getCurrentReading() / (double)this.capacity;
        // Max hazard if exceed max capacity
        if (hazardRatio >= 1){
            return MAX_HAZARD;
        }
        // If doesn't exceed, hazard is determined by ratio
        return (int) Math.floor(hazardRatio * 100);
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
                sensorReadingsConverted + ", type=OccupancySensor, capacity=" +
                this.capacity;
    }
}
