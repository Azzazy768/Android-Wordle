package com.example.wordleclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class PlayingScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_playing_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WordleOperation operationObject = new WordleOperation();


        String gameWord = operationObject.giveStartingWord();


        ImageButton HomeButton = findViewById(R.id.Home_Button);

        TextView gameWordText = findViewById(R.id.Cheat);
        gameWordText.setText("The Word Was: " + gameWord);
        gameWordText.setVisibility(View.INVISIBLE);

        EditText inputGuess = findViewById(R.id.InputGuess);
        TextView FirstGuess = findViewById(R.id.FirstGuess);
        TextView SecondGuess = findViewById(R.id.secondGuess);
        TextView ThirdGuess = findViewById(R.id.ThirdGuess);
        TextView FourthGuess = findViewById(R.id.FourthGuess);
        TextView FifthGuess = findViewById(R.id.FifthGuess);
        FirstGuess.setVisibility(View.INVISIBLE);
        SecondGuess.setVisibility(View.INVISIBLE);
        ThirdGuess.setVisibility(View.INVISIBLE);
        FourthGuess.setVisibility(View.INVISIBLE);
        FifthGuess.setVisibility(View.INVISIBLE);


        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayingScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });





        TextView[] GuessesArr = {FirstGuess,SecondGuess,ThirdGuess,FourthGuess,FifthGuess};
        final int[] CurrentGuess = {0};

        Button submitButton = findViewById(R.id.Submit_Button);

        Character[] GuessedCharacters = new Character[5];
        Animation fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Character,Integer> numOfOccurnces = new HashMap<>(operationObject.returnCharOccurences());
                GuessesArr[CurrentGuess[0]].setText(operationObject.checkGuess(inputGuess.getText().toString().toUpperCase(),numOfOccurnces));
                GuessesArr[CurrentGuess[0]].setVisibility(View.VISIBLE);
                GuessesArr[CurrentGuess[0]].startAnimation(fadeIn);
                CurrentGuess[0] = CurrentGuess[0] +1;
                if(CurrentGuess[0] == 5){
                    submitButton.setVisibility(View.INVISIBLE);
                    inputGuess.setVisibility(View.INVISIBLE);
                    gameWordText.setVisibility(View.VISIBLE);
                }
//                FirstGuess.setText(Html.fromHtml(operationObject.checkGuess(inputGuess.getText().toString(),numOfOccurnces)));
            }
        });

    }
}