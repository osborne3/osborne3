package com.example.simpletictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard; // not needed if I remove button

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display); //sets out our XML layout

        Button playAgainBTN = findViewById(R.id.play_again);
        Button homeBTN = findViewById(R.id.home_button);
        TextView playerTurn = findViewById(R.id.player_display);

        //playAgainBTN.setVisibility(View.GONE); // Not really needed can remove
        //homeBTN.setVisibility(View.GONE); // Not really needed can remove

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if (playerNames != null)
        {
            playerTurn.setText((playerNames[0]) + "Turn");
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard3); // not needed if I remove button

        ticTacToeBoard.setUpGame(playAgainBTN, homeBTN, playerTurn, playerNames);
    }

    public void playAgainButtonClick(View view) //Our play again button
    {
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();

    }

    public void homeButtonClick(View view) //Our home button
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
