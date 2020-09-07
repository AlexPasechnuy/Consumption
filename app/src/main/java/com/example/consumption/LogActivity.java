package com.example.consumption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.List;

public class LogActivity extends AppCompatActivity {
//    public long gasVol, odometer;
//    public String comment;
//    public double consump;

    TableLayout table;

    RecordsHistory log = new RecordsHistory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        log = RecordsHistory.read(this);
        table = (TableLayout)findViewById(R.id.table);
        List<Record> records = log.getRecords();


        {
            TableRow head = new TableRow(this);

            TextView gasVol = new TextView(this);
            gasVol.setText("Volume");
            head.addView(gasVol);

            TextView odometer = new TextView(this);
            odometer.setText("Odometer");
            head.addView(odometer);

            TextView comment = new TextView(this);
            comment.setText("Comment");
            head.addView(comment);

            TextView consump = new TextView(this);
            consump.setText("Consumption");
            head.addView(consump);

            table.addView(head);
        }


        for ( Record record : records) {
            TableRow tr = new TableRow(this);

            TextView gasVol = new TextView(this);
            gasVol.setText(String.valueOf(record.gasVol));
            tr.addView(gasVol);

            TextView odometer = new TextView(this);
            odometer.setText(String.valueOf(record.odometer));
            tr.addView(odometer);

            TextView comment = new TextView(this);
            comment.setText(record.comment);
            tr.addView(comment);

            TextView consump = new TextView(this);
            consump.setText(String.valueOf(record.consump));
            tr.addView(consump);

            table.addView(tr);
        }

    }
}