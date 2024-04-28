package com.example.ofekleviquadraticequation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Main1Activity extends AppCompatActivity {
    Double a,b,c,root;
    TextView ans;
    ImageView parab1;
    String X1, X2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent=getIntent();
        parab1 = findViewById(R.id.iVparab1);
        ans = findViewById(R.id.answer);

        a=intent.getDoubleExtra("a",0);
        b=intent.getDoubleExtra("b",0);
        c=intent.getDoubleExtra("c",0);

        //מציגים תמונה של הפרבולה
        root = (double) (b*b-4*a*c);
        if(root==0 && a>0)   //פרבולה מחייכת נקודת חיתוך אחד
            parab1.setImageResource(R.drawable.parab1point2);
        else if(root>0 && a>0)   //פרבולה מחייכת שתי נקודות חיתוך
            parab1.setImageResource(R.drawable.parab2points2);
        else if(root<0 && a>0)   //פרבולה מחייכת מרחפת
            parab1.setImageResource(R.drawable.parabmerahef2);
        else if(root==0 && a<0)   //פרבולה עצובה נקודת חיתוך אחת
            parab1.setImageResource(R.drawable.parab1point1);
        else if(root>0 && a<0)    //פרבולה עצובה שתי נקודות חיתוך
            parab1.setImageResource(R.drawable.parab2points1);
        else if(root<0 && a<0)   //פרבולה עצובה מרחפת
            parab1.setImageResource(R.drawable.parabmerahef1);

        //חישוב פתרון המשוואה הריבועית
        if (root>0){
            X1 = (Double.valueOf(-b)-Math.sqrt(root))/(2*a)+"";
            X2 = (Double.valueOf(-b)+Math.sqrt(root))/(2*a)+"";
        } else if (root < 0) {
            X1 = "No Solution";
            X2 = "No Solution";
        } else {
            X1 = (Double.valueOf(-b)+Math.sqrt(root))/(2*a)+"";
            X2 = "No Solution";
        }
        ans.setText("X1=" + X1 + " , "+" X2=" + X2);
    }

    //חזרה למסך 1
    public void back(View view) {
        Intent ti = new Intent(Main1Activity.this, MainActivity.class);
        ti.putExtra("X1",X1);
        ti.putExtra("X2",X2);
        setResult(Activity.RESULT_OK,ti);
        finish();
    }
}
