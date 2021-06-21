package bms.sensors;

import java.util.Arrays;

/**
 * A sensor that measures levels of carbon dioxide (CO2) in the air, in parts
 * per million (ppm).
 */
public class CarbonDioxideSensor extends TimedSensor implements HazardSensor {
    int idealValue;
    int variationLimit;

    /**
     * Creates a new carbon dioxide sensor with the given sensor readings,
     * update frequency, ideal CO2 value and acceptable variation limit.
     * Different rooms and environments may naturally have different "normal"
     * CO2 concentrations, for example, a large room with many windows may
     * have lower typical CO2 concentrations than a small room with poor
     * airflow.
     *
     * To allow for these discrepancies, each CO2 sensor has an "ideal" CO2
     * concentration and a maximum acceptable variation from this value. Both
     * the ideal value and variation limit must be greater than zero. These
     * two values must be such that (idealValue - variationLimit) >= 0.
     *
     * @param sensorReadings array of CO2 sensor readings in ppm
     * @param updateFrequency indicates how often the sensor readings update, in
     * minutes
     * @param idealValue ideal CO2 value in ppm
     * @param variationLimit acceptable range above and below ideal value in ppm
     * @throws IllegalArgumentException if idealValue <= 0; or if
     * variationLimit <= 0; or if (idealValue - variationLimit) < 0
     */
    public CarbonDioxideSensor(int[] sensorReadings,
                               int updateFrequency, int idealValue,
                               int variationLimit)
            throws IllegalArgumentException{
        super(sensorReadings, updateFrequency);
        this.idealValue = idealValue;
        this.variationLimit = variationLimit;

        if (this.idealValue <= 0 || this.variationLimit <=0 ||
                (this.idealValue - this.variationLimit) < 0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the sensor's CO2 variation limit.
     *
     * @return variation limit in ppm
     */
    public int getVariationLimit() {
        return variationLimit;
    }

    /**
     * Returns the sensor's ideal CO2 value.
     *
     * @return ideal value in ppm
     */
    public int getIdealValue(){
        return idealValue;
    }

    @Override
    public int getHazardLevel() {
        final int NO_HAZARD = 0;
        final int LOW_HAZARD = 25;
        final int MEDIUM_HAZARD = 50;
        final int EXTREME_HAZARD = 100;

        // Current sensor readings determines hazard level
        if (getCurrentReading() >= 0 && getCurrentReading() <= 999){
            return NO_HAZARD;
        }
        else if (getCurrentReading() >= 1000 && getCurrentReading() <= 1999){
            return LOW_HAZARD;
        }
        else if (getCurrentReading() >= 2000 && getCurrentReading() <= 4999){
            return MEDIUM_HAZARD;
        }
        return EXTREME_HAZARD;
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
                sensorReadingsConverted +
                ", type=CarbonDioxideSensor, idealPPM=" +
                this.idealValue + ", varLimit=" + this.variationLimit;
    }
}
