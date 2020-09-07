package com.example.consumption;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
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

        public void write(Context context) {
        File file = new File(String.valueOf(context.getFilesDir()));
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File gpxfile = new File(file, "sample");
            FileWriter writer = new FileWriter(gpxfile, true);
            //writer.append(enterText.getText().toString());
                writer.append(gasVol+"_-_"+odometer+"_-_"+comment+"\n");
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }
}