package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addition;
    Button subtraction;
    Button multi;
    int x1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addition=findViewById(R.id.buttonAdd);
        subtraction=findViewById(R.id.buttonSub);
        multi=findViewById(R.id.buttonMulti);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game.class);
                x1=0;
                intent.putExtra("Opration",x1);
                startActivity(intent);
                finish();
            }
        });
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game.class);
                x1=1;
                intent.putExtra("Opration",x1);
                startActivity(intent);
                finish();
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game.class);
                x1=2;
                intent.putExtra("Opration",x1);
                startActivity(intent);
                finish();
            }
        });
    }
}