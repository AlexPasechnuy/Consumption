package com.example.consumption;

import java.util.ArrayList;
import java.util.List;

public class RecordsHistory {
    List<Record> records = new ArrayList<>();

    public void add(long gasVol, long odometer, String comment){
        double newConsump = records.get(records.size() - 1).gasVol/(odometer - records.get(records.size() - 1).odometer)*100;
        records.get(records.size() - 1).consump = newConsump;
        records.add(new Record(gasVol, odometer, comment));
    }
}
