package com.sample.models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataSet {
    
    private static DataSet INSTANCE = new DataSet();
    private Map<Long, Queue> dataset = new ConcurrentHashMap<Long, Queue>();
    private DataSet() {
    }

    public static DataSet getInstance() {
        return INSTANCE;
    }
    
    public Map<Long, Queue> getDataSet() {
        return dataset;
    }
    
    public Queue getQueue(Long id) {
        return dataset.get(id);
    }
}
