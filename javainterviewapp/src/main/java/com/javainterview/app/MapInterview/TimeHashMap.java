package com.javainterview.app.MapInterview;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created on 10/6/2015.
 */
public class TimeHashMap {

    Map<String, TreeMap<Integer, String>> map;

    /**
     * Constructor to initialize the internal map
     */
    public TimeHashMap() {
        map = new HashMap<String, TreeMap<Integer, String>>();
    }

    /**
     * Method to insert data associated with key and timestamp
     *
     * @param key       key in top map
     * @param value     value to store
     * @param timestamp timestamp of data
     */
    public void insert(String key, String value, int timestamp) {
        TreeMap<Integer, String> timeMap;
        // check if the map already has this key
        if (map.containsKey(key)) {
            timeMap = map.get(key);
        } else {
            timeMap = new TreeMap<Integer, String>();
            map.put(key, timeMap);
        }

        // after retrieving the time map
        // push the new value
        timeMap.put(timestamp, value);
    }

    /**
     * Returns value at key associated with timestamp
     * If it does not exist, then get a newer value
     *
     * @param key       key of value
     * @param timestamp timestamp associated with value
     * @return value associated with key and timestamp
     */
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return null;
        }

        TreeMap<Integer, String> timeMap = map.get(key);
        if (timeMap.containsKey(timestamp)) {
            return timeMap.get(timestamp);
        }

        int floorKey = timeMap.floorKey(timestamp);
        return timeMap.get(floorKey);
    }


    /**
     * Returns value associated with key at latest time
     *
     * @param key key associated with data
     * @return value of the latest entry
     */
    public String get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }

        TreeMap<Integer, String> timeMap = map.get(key);
        int lastKey = timeMap.lastKey();
        return timeMap.get(lastKey);
    }

}
