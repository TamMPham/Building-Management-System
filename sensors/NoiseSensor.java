package bms.sensors;

import java.util.Arrays;

/**
 * A sensor that measures the noise levels in a room.
 */
public class NoiseSensor extends TimedSensor implements HazardSensor {
    /**
     * Creates a new noise sensor with the given sensor readings and update
     * frequency.
     *
     * @param sensorReadings array of noise sensor readings in decibels
     * @param updateFrequency indicates how often the sensor readings update, in
     * minutes
     */
    public NoiseSensor(int[] sensorReadings, int updateFrequency){
        super(sensorReadings, updateFrequency);
    }

    /**
     * Calculates the relative loudness level compared to a reference of 70.0
     * decibels.
     * The loudness of sounds in comparison to 70.0 decibels is given by the
     * formula:
     *
     * 2^((measured volume - 70.0)/10.0)
     *
     * For example, a sound reading of 67 decibels would have a relative
     * loudness of 0.8123. A Sound reading of 82 decibels would have a
     * relative loudness of 2.2974.
     *
     * @return relative loudness of current reading to 70dB
     */
    public double calculateRelativeLoudness(){
        return Math.pow(2, (getCurrentReading() - 70.0) / 10.0);
    }

    @Override
    public int getHazardLevel() {
        final int EXTREME_HAZARD = 100;
        // Rounds down to nearest integer
        double hazardNoise = Math.floor(calculateRelativeLoudness() * 100);

        if (hazardNoise > 100){
            return EXTREME_HAZARD;
        }
        return (int) hazardNoise;
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
                sensorReadingsConverted + ", type=NoiseSensor";
    }
    }
