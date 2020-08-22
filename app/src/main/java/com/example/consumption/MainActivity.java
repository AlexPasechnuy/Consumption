package com.example.consumption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    RecordsHistory log = RecordsHistory.deserialize();

    EditText odometer, gasVol, comment;

    Button addRec, logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        log.add(Integer.parseInt(odometer.getText().toString()),
                                Integer.parseInt(gasVol.getText().toString()),
                                comment.getText().toString());
                        log.serialize();
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