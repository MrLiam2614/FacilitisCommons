package me.mrliam2614.facilitiscommons.storage;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String, Object> dataMap;

    public Data(){
        dataMap = new HashMap<>();
    }

    public <T> void set(String dataName, T value){
        dataMap.put(dataName, value);
    }

    public <T> T get(String dataName){
        return (T) dataMap.get(dataName);
    }
}
