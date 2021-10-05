package com.example.simpletictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.editText);
        player2 = findViewById(R.id.editText2);
    }

    public void playButtonClick(View view) //Play Button Method
    {
        String player1Name = player1.getText().toString(); //Gets the names from the text boxes and makes them a string
        String player2Name = player2.getText().toString(); //Gets the names from the text boxes and makes them a string

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("PLAYER_NAMES", new String[]{player1Name, player2Name}); //puts in our extra info with the intent //dont need
        startActivity(intent); //starts the intent
    }

    public void highScoreButtonClick(View view) //High Score Button Method
    {
        //Intent intent = new Intent(this, PlayerSetup.class); //our intent
        //startActivity(intent); //pass in intent
    }

    public void exitButtonClick(View view) //Exit Button Method
    {
        //Intent intent = new Intent(this, PlayerSetup.class); //our intent
        //startActivity(intent); //pass in intent
    }
}
