package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    Button exit;
    Button playAgain;
    TextView result;
    TextView cong;
    int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.textViewResult);
        exit=findViewById(R.id.buttonExit);
        playAgain=findViewById(R.id.buttonAgain);
        cong=findViewById(R.id.textViewcong);
        Intent intent=getIntent();
        score=intent.getIntExtra("Score",0);
        String userScore=String.valueOf(score);
        result.setText("Your Score:"+userScore);
        cong.setText("Game Over");
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}