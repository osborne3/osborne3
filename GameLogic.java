package com.example.simpletictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {

    private int[][] gameBoard;
    private int player = 1;

    private String [] playerNames = {"Player 1", "Player 2"};

    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;


    GameLogic(){
        gameBoard = new int[3][3];
        for (int r=0; r<3; r++)
        {
            for (int c=0; c<3; c++)
            {
                gameBoard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col)
    {
        if (gameBoard[row-1][col-1] == 0)
        {
            gameBoard[row-1][col-1] = player;

            if (player == 1)
            {
                playerTurn.setText((playerNames[1] + "'s Turn" )); //could add turn number in here too
            }
            else
            {
                playerTurn.setText((playerNames[0] + "'s Turn"));
            }

            return true;

        }
        else{
            return false;
        }
    }

    public boolean checkWin(){ //method to check if there is three in a row
        boolean isWinner = false;

        for (int r=0; r<3; r++)
        {
            if (gameBoard[r][0] == gameBoard [r][1] && gameBoard[r][0] == gameBoard[r][2] //row check
                    && gameBoard[r][0] != 0)
            {
                isWinner = true;
            }
        }

        for (int c=0; c<3; c++)
        {
            if (gameBoard[0][c] == gameBoard [1][c] && gameBoard[2][c] == gameBoard[0][c] //column check
                    && gameBoard[0][c] != 0)
            {
                isWinner = true;
            }
        }

        for (int c=0; c<3; c++)
        {
            if (gameBoard[0][0] == gameBoard [1][1] && gameBoard[0][0] == gameBoard[2][2] //diag check
                    && gameBoard[0][0] != 0)
            {
                isWinner = true;
            }
        }

        for (int c=0; c<3; c++)
        {
            if (gameBoard[2][0] == gameBoard [1][1] && gameBoard[2][0] == gameBoard[0][2]//diag check
                    && gameBoard[2][0] != 0)
            {
                isWinner = true;
            }
        }

        int boardFilled = 0; //can use this to add turns

        for (int r=0; r<3; r++)
        {
            for (int c=0; c<3; c++)
            {
                if (gameBoard[r][c] != 0)
                {
                    boardFilled +=1; //set game turn in here??
                }
            }
        }

        if (isWinner) //Sets the winner
        {
            //playAgainBTN.setVisibility(View.VISIBLE); //can probably remove this don't need to remove buttons
            //homeBTN.setVisibility(View.VISIBLE); //can probably remove this don't need to remove buttons
            playerTurn.setText((playerNames[player-1]) +  " Won!");
            return true;
        }

        else if(boardFilled == 9)
        {
            //playAgainBTN.setVisibility(View.VISIBLE); //can probably remove this don't need to remove buttons
            //homeBTN.setVisibility(View.VISIBLE); //can probably remove this don't need to remove buttons
            playerTurn.setText("Tie");
            return true;
        }
        else
        {
            return false;
        }

    }


    public void resetGame() // Don't know if ill need this
    {
        for (int r=0; r<3; r++)
        {
            for (int c=0; c<3; c++)
            {
                gameBoard[r][c] = 0;
            }
        }

        player = 1;

        //playAgainBTN.setVisibility(View.GONE); //can remove these
        //homeBTN.setVisibility(View.GONE); //can remove these

        playerTurn.setText(playerNames[0] + "'s Turn");

    }

    public void setPlayAgainBTN(Button playAgainBTN)
    {
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN)
    {
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn)
    {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames)
    {
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }

    public int getPlayer()
    {
        return player;
    }
}
