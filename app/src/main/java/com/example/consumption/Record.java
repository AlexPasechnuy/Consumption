package com.example.consumption;

public class Record {
    public long gasVol, odometer;
    public String comment;
    public double consump;

    public Record(long gasVol, long odometer, String comment){
        this.gasVol = gasVol;
        this.odometer = odometer;
        this.comment = comment;
    }
}
