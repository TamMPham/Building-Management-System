package bms.sensors;

/**
 * Represents a sensor that can be used to evaluate the level of hazard present
 * in a location monitored by the sensor.
 */
public interface HazardSensor extends Sensor{
    /**
     * Returns the level of hazard in a location as detected by this sensor
     * (as a percentage).
     * A value of 0 indicates demonstrates very low hazard, and a value of
     * 100 indicates very high hazard.
     *
     * @return level of hazard at sensor location, 0 to 100
     */
    int getHazardLevel();
}
