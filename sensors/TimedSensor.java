package bms.sensors;

import bms.util.TimedItem;
import bms.util.TimedItemManager;

import java.util.Arrays;

/**
 * An abstract class to represent a sensor that iterates through observed values
 * on a timer.
 */
public abstract class TimedSensor implements TimedItem, Sensor {
    int[] sensorReadings;
    int updateFrequency;

    // Time elapsed in minutes
    private int timeElapsed = 0;

    // Current index value for sensorReadings
    private int currentValue = 0;

    /**
     * Creates a new timed sensor, using the provided list of sensor readings
     * . These represent "raw" data values, and have different meanings
     * depending on the concrete sensor class used.
     * The provided update frequency must be greater than or equal to one (1)
     * , and less than or equal to five (5). The provided sensor readings
     * array must not be null, and must have at least one element. All sensor
     * readings must be non-negative.
     *
     * The new timed sensor should be configured such that the first call to
     * getCurrentReading() after calling the constructor must return the
     * first element of the given array.
     *
     * The sensor should be registered as a timed item, see TimedItemManager
     * .registerTimedItem(TimedItem).
     *
     * @param sensorReadings a non-empty array of sensor readings
     * @param updateFrequency indicates how often the sensor readings
     * updates, in minutes
     * @throws IllegalArgumentException if updateFrequency is < 1 or > 5;
     * or if sensorReadings is null; if sensorReadings is empty;
     * or if any value in sensorReadings is less than zero
     */
    public TimedSensor(int[] sensorReadings, int updateFrequency){
        this.sensorReadings = sensorReadings;
        this.updateFrequency = updateFrequency;

        if (this.updateFrequency < 1 || this.updateFrequency > 5 ||
                this.sensorReadings == null){
            throw new IllegalArgumentException();
        }

        for (int sr : sensorReadings) {
            if (sr < 0) {
                throw new IllegalArgumentException();
            }
        }
        TimedItemManager.getInstance().registerTimedItem(this);
    }

    @Override
    public int getCurrentReading(){
        // When out of index, wraps around
        return sensorReadings[currentValue % sensorReadings.length];
    }

    /**
     * Returns the number of minutes that have elapsed since the sensor was
     * instantiated. Should return 0 immediately after the constructor is
     * called.
     *
     * @return the sensor's time elapsed in minutes
     */
    public int getTimeElapsed(){
        return timeElapsed;
    }

    /**
     * Returns the number of minutes in between updates to the current sensor
     * reading.
     *
     * @return the sensor's update frequency in minutes
     */
    public int getUpdateFrequency(){
        return this.updateFrequency;
    }

    @Override
    public void elapseOneMinute(){
        timeElapsed++;
        if (getTimeElapsed() % getUpdateFrequency() == 0){
            currentValue ++;
        }
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
                sensorReadingsConverted;
    }
}
