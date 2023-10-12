package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {
    TextView score;
    TextView time;
    TextView life;
    TextView question;
    EditText answer;

    Button next;
    Button ok;
    Random random=new Random();
    int number1;
    int number2;
    int userAnswer;
    int realanswer=0;
    int userScore=0;
    int userlife=3;
    int operation=0;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS =60000;
    Boolean timer_running;
    long time_left_int_milis=START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score=findViewById(R.id.textViewScore);
        time=findViewById(R.id.textViewTime);
        life=findViewById(R.id.textViewLife);
        question=findViewById(R.id.textViewQuestion);
        answer=findViewById(R.id.editTextAnswer);
        next=findViewById(R.id.buttonNext);
        ok=findViewById(R.id.buttonOk);
        Intent intent1=getIntent();
        operation=intent1.getIntExtra("Opration",0);
        gameContinue();



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer=Integer.valueOf(answer.getText().toString());
                pauseTimer();
                if(userAnswer==realanswer){
                    userScore+=10;
                    score.setText(""+userScore);
                    question.setText("Congratulation, Your answer is Correct");
                }else{
                    userlife--;
                    if(userlife==0){
                        Intent intent=new Intent(Game.this,Result.class);
                        intent.putExtra("Score",userScore);
                        startActivity(intent);
                        finish();
                    }else {
                        life.setText("" + userlife);
                        question.setText("Sorry, Your answer is Wrong");
                    }
                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   resetTimer();
                   gameContinue();
                   answer.setText("");
                   if(userlife==0){
                       Intent intent=new Intent(Game.this,Result.class);
                       intent.putExtra("Score",userScore);
                       startActivity(intent);
                       finish();
                   }
            }
        });
    }

    public void gameContinue(){
      number2=random.nextInt(100);
      number1=random.nextInt(100);
      if(operation==0){
          question.setText(number1+" + "+number2);
          realanswer=number1+number2;
      }else{
          if(operation==1){
              question.setText(number1+" - "+number2);
              realanswer=number1-number2;
          }else{
              question.setText(number1+" * "+number2);
              realanswer=number1*number2;
          }
      }
      startTimer();
    }
    public void startTimer(){
        timer=new CountDownTimer(time_left_int_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_int_milis=millisUntilFinished;
                updateText();
            }
            @Override
            public void onFinish() {
                  timer_running=false;
                  pauseTimer();
                  resetTimer();
                  updateText();
                  userlife--;
                  if(userlife==0){
                      Intent intent=new Intent(Game.this,Result.class);
                      intent.putExtra("Score",userScore);
                      startActivity(intent);
                      finish();
                  }
                  life.setText(" "+userlife);
                  question.setText("Sorry! Time is Up..!!");
            }
        }.start();
        timer_running=true;
    }
    public void updateText(){
        int second=(int)(time_left_int_milis / 1000)%60;;
        String time_left=String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running=false;

    }
    public void resetTimer(){
        time_left_int_milis=START_TIMER_IN_MILIS;
        updateText();
    }

}