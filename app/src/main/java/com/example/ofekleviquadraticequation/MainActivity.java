package com.example.ofekleviquadraticequation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText eTa, eTb, eTc; //הצבת המספרים שהוגרלו או שהמשתמש הקליד
    Button random, solve; //כפתור להגרלת מספרים, וכפתור למעבר המסך ה2 ופתירת המשוואה
    double numA, numB, numC; //המספרים שהמשתמש מגריל
    final int REQUEST_CODE=100;
    TextView ans; //מציג בסוף את התוצאה
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
        ans=findViewById(R.id.answer1);
    }
    @Override //החזרת נתונים (תשובות של שני האיקסים) ממסך 2 והצגתם במסך1
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                if(data!=null){
                    String str1=data.getStringExtra("X1");
                    String str2=data.getStringExtra("X2");
                    ans.setText("X1="+str1+"\nX2="+str2);
                }
                else {
                    Log.i("MainActivity","NO data returned");
                }
            }
            else {
                Log.i("MainActivity","Bad return code");
            }
        }
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

        startActivityForResult(si,REQUEST_CODE);
    }
}