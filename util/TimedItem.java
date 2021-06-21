package bms.util;

/**
 * Denotes a class containing a routine to be executed at regular intervals in time.
 */
public interface TimedItem {
    /**
     * This method will be called by TimedItemManager once every second, provided
     * the model is not in a paused state.
     */
    void elapseOneMinute();
}
