package com.example.consumption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordsHistory implements Serializable {
    List<Record> records = new ArrayList<>();

    public void add(long gasVol, long odometer, String comment) {
        double newConsump = records.get(records.size() - 1).gasVol / (odometer - records.get(records.size() - 1).odometer) * 100;
        records.get(records.size() - 1).consump = newConsump;
        records.add(new Record(gasVol, odometer, comment));
    }

    public void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))) {
            oos.writeObject(this);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static RecordsHistory deserialize() {
        RecordsHistory hist = new RecordsHistory();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) {
            hist = (RecordsHistory) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return hist;
    }

    public List<Record> getRecords(){
        return records;
    }
}