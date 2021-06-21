package bms.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class which manages all the timed items.
 * All classes that implement TimedItem must be registered with this manager,
 * which will allow their elapseOneMinute() method to be called at regular
 * time intervals.
 *
 * Once a class is registered with the timed item manager by calling
 * registerTimedItem(TimedItem) ()} and passing itself, the manager will
 * ensure that its elapseOneMinute() method is called at regular intervals.
 */
public class TimedItemManager implements TimedItem {
    // A singleton instance of class
    static TimedItemManager timeManager = new TimedItemManager();

    // List containing registered timed items
    List<TimedItem> timedItemList = new ArrayList<>();

    /**
     * Returns the singleton instance of the timed item manager.
     *
     * @return singleton instance
     */
    public static TimedItemManager getInstance(){
        return timeManager;
    }

    /**
     * Registers a timed item with the manager.
     * After calling this method, the manager will call the given timed
     * item's elapseOneMinute() method at regular intervals.
     *
     * @param timedItem - a timed item to register with the manager
     */
    public void registerTimedItem(TimedItem timedItem){
        timedItemList.add(timedItem);
    }
    
    @Override
    public void elapseOneMinute() {
        // Simulate minute passing for all registered timed items
        for (TimedItem t : timedItemList){
            t.elapseOneMinute();
        }
    }
}
