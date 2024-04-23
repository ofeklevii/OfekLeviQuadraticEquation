package com.example.ofekleviquadraticequation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText eTa, eTb, eTc;
    Button random, solve;
    double numA, numB, numC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eTa = findViewById(R.id.eTa);
        eTb = findViewById(R.id.eTb);
        eTc = findViewById(R.id.eTc);
        random = findViewById(R.id.random);
        solve = findViewById(R.id.solve);
    }
    //מגרילים מספר
    public static int get_randNum()
    {
        Random randomNumber = new Random();
        return (int)(Math.random() * 89 + -60);
    }

    public void random(View view) {
        numA = get_randNum();
        numB = get_randNum();
        numC = get_randNum();
        eTa.setText("" + numA);
        eTb.setText("" + numB);
        eTc.setText("" + numC);
    }

    public void solve(View view) {
        Intent si=new Intent(MainActivity.this, Main1Activity.class);
        numA = Double.parseDouble(eTa.getText().toString());
        numB = Double.parseDouble(eTb.getText().toString());
        numC = Double.parseDouble(eTc.getText().toString());

        si.putExtra("a", numA);
        si.putExtra("b", numB);
        si.putExtra("c", numC);

        startActivity(si);
    }
}