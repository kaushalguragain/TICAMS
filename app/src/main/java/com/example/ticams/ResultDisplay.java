package com.example.ticams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultDisplay extends AppCompatActivity {

    TextView present, absent, total;
    String setPresent, setTotal;
    Integer setAbsent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        present = findViewById(R.id.display_present);
        absent = findViewById(R.id.display_absent);
        total = findViewById(R.id.display_total);

        setPresent = getIntent().getExtras().getString("present");
        setTotal = getIntent().getExtras().getString("total");
        setAbsent = Integer.valueOf(setTotal)-Integer.valueOf(setPresent);
        present.setText("Present : "+setPresent);
        total.setText("Total : "+setTotal);
        absent.setText("Absent  :"+setAbsent.toString());



    }



}
