package com.example.consumption;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordsHistory implements Serializable {
    List<Record> records = new ArrayList<>();

    public void add(long gasVol, long odometer, String comment) {
        records.add(new Record(gasVol, odometer, comment));
        try {
            double newConsump = records.get(records.size() - 1).gasVol / (odometer - records.get(records.size() - 2).odometer) * 100;
            records.get(records.size() - 1).consump = newConsump;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    public void serialize() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))) {
//            oos.writeObject(this);
//        } catch (FileNotFoundException ex){
//            try {
//                File file = new File("person.dat");
//                file.createNewFile();
//            }catch (IOException exx){
//
//            }
//        }catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        int i = 5;
//    }
//
//    public static RecordsHistory deserialize() throws FileNotFoundException{
//        RecordsHistory hist = new RecordsHistory();
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) {
//            hist = (RecordsHistory) ois.readObject();
//        } catch (FileNotFoundException ex){
//            throw ex;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return hist;
//    }

    public void write(Context context) {
        File file = new File(String.valueOf(context.getFilesDir()));
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File gpxfile = new File(file, "sample");
            FileWriter writer = new FileWriter(gpxfile, true);
            //writer.append(enterText.getText().toString());
            for(Record rec : records){
                writer.append(rec.gasVol+"_-_"+rec.odometer+"_-_"+rec.comment+"\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }

    public static RecordsHistory read(Context context){
        RecordsHistory rh = new RecordsHistory();
        File fileEvents = new File(context.getFilesDir()+"/files/ log.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("_-_");
                rh.add(Long.parseLong(fields[0]),Long.parseLong(fields[1]),fields[2]);
            }
            br.close();
        } catch (IOException e) { }
        return rh;
    }


    public List<Record> getRecords(){
        return records;
    }
}