package bms.sensors;

/**
 * Represents a device that is used to monitor and report readings of some
 * observed variable at a location.
 * All sensor readings are provided as integers but may have different
 * meanings depending on the concrete sensor type.
 */
public interface Sensor {
    /**
     * Returns the current sensor reading observed by the sensor.
     *
     * @return the current sensor reading
     */
    int getCurrentReading();
}
