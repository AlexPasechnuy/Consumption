package com.example.consumption;

import java.io.Serializable;

public class Record implements Serializable {
    public long gasVol, odometer;
    public String comment;
    public double consump;

    public Record(long gasVol, long odometer, String comment){
        this.gasVol = gasVol;
        this.odometer = odometer;
        this.comment = comment;
    }
}