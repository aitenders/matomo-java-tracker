/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 * 
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.corporate.piwik.tracking;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * Object representing the custom variable array required by some 
 * Piwik query parameters.  An array is represented by an object that has
 * the index of the entry as the key (1 indexed) and a 2 entry array representing 
 * a custom key and custom value as the value.
 * 
 * @author brettcsorba
 */
public class PiwikJsonObject{
    Map<String, String> map = new HashMap<>();
    
    /**
     * Gets the custom value stored at this custom key.
     * @param key key used to lookup value
     * @return value stored at specified key, null if not present
     */
    public String get(String key){
        return map.get(key);
    }
    
    /**
     * Puts a custom variable at this custom key.
     * @param key key to store value at
     * @param value value to store at specified key
     * @return previous value stored at key if present, null otherwise
     */
    public String put(String key, String value){
        return map.put(key, value);
    }
    
    /**
     * Removes the custom value stored at this custom key.
     * @param key key used to lookup value to remove
     * @return the value that was removed, null if no value was there to remove
     */
    public String remove(String key){
        return map.remove(key);
    }
    
    /**
     * Produces the JSON string representing this object.<br>
     * <br>
     * For example, if the following values were put into the object<br>
     * <br>
     * {@code ("key1", "value1") } and {@code ("key2", "value2") }<br>
     * <br>
     * {@code {"1": ["key1", "value1"], "2": ["key2": "value2"]} }<br>
     * <br>
     * could be produced.  Note that there is no guarantee about the ordering of
     * the produced JSON based.  The following JSON is also valid and could also
     * be produced:<br>
     * <br>
     * {@code {"1": ["key2", "value2"], "2": ["key1": "value1"]} }.
     * @return the JSON string representation of this object
     */
    @Override
    public String toString(){
        JsonObjectBuilder ob = Json.createObjectBuilder();
        
        int x = 1;
        for (Entry<String, String> entry : map.entrySet()){
            JsonArrayBuilder ab = Json.createArrayBuilder();
            ab.add(entry.getKey());
            ab.add(entry.getValue());
            ob.add(Integer.toString(x++), ab);
        }
        
        return ob.build().toString();
    }
}
