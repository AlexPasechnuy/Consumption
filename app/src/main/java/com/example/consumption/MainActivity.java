package com.example.consumption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {

    RecordsHistory log = new RecordsHistory();

    EditText odometer, gasVol, comment;

    Button addRec, logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = RecordsHistory.read(this);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        odometer = (EditText) findViewById(R.id.odomField);
        gasVol = (EditText) findViewById(R.id.gasVolField);
        comment = (EditText) findViewById(R.id.comment);
        addRec = (Button) findViewById(R.id.addRecBtn);
        logBtn = (Button) findViewById(R.id.logBtn);

        addRec.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            new Record(Long.parseLong(odometer.getText().toString()),
                                    Long.parseLong(gasVol.getText().toString()),
                                    comment.getText().toString()).write(MainActivity.this);
                        }catch (ArrayIndexOutOfBoundsException ex){

                        }
                        odometer.setText("");
                        gasVol.setText("");
                        comment.setText("");
                    }
                }
        );

        logBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".LogActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}