package com.example.simpletictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;


    private boolean winningLine = false;



    private final Paint paint = new Paint();

    private final GameLogic game;

    private int cellSize = getWidth()/3; //default value

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToeBoard,
                0, 0);

        try {



            boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor , 0);
            XColor = a.getInteger(R.styleable.TicTacToeBoard_XColor, 0);
            OColor = a.getInteger(R.styleable.TicTacToeBoard_OColor, 0);


        }finally{

            a.recycle();
        }


    }

    @Override
    protected void onMeasure(int width, int height){ //gets dimensions needed for board
        super.onMeasure(width, height);

        int dimension= Math.min(getMeasuredWidth(), getMeasuredHeight()); //Gets width and height
        cellSize = dimension/3;


        setMeasuredDimension(dimension, dimension); //set the dimensions of board
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX(); //get positions
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN)
        {
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if(!winningLine)
            {
                if (game.updateGameBoard(row, col))
                {
                    invalidate();

                    if(game.checkWin())
                    {
                        winningLine = true;
                        invalidate();
                    }

                    if (game.getPlayer() % 2 == 0)
                    {
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else
                    {
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }


            invalidate();

            return true;
        }
        return false;
    }

    private void drawGameBoard(Canvas canvas) //draws the lines
    {

        paint.setColor(boardColor);
        paint.setStrokeWidth(16);


        for (int c=1; c<3; c++)
        {
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);

        }
        for (int r=1; r<3; r++)
        {
            canvas.drawLine(0, cellSize*r, canvas.getWidth(), cellSize*r, paint);
        }

    }

    private void drawMarkers(Canvas canvas)
    {
        for (int r=0; r<3; r++)
        {
            for (int c=0; c<3; c++)
            {
                if (game.getGameBoard()[r][c] != 0)
                {
                    if (game.getGameBoard()[r][c] == 1)
                    {
                        drawX(canvas, r, c);
                    }
                    else
                    {
                        drawO(canvas, r, c);
                    }

                }
            }
        }

    }

    private void drawX(Canvas canvas, int row, int col) //draws X's
    {
        paint.setColor(XColor);
        canvas.drawLine((col+1)*cellSize, row*cellSize , col*cellSize, (row+1)*cellSize, paint);
        canvas.drawLine(col*cellSize, row*cellSize , (col+1)*cellSize, (row+1)*cellSize, paint);

    }
    private void drawO(Canvas canvas, int row, int col)
    {
        paint.setColor(OColor);

        canvas.drawOval(col*cellSize, row*cellSize, (col*cellSize + cellSize), (row*cellSize+ cellSize), paint  );
    }

    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names) {

        game.setPlayAgainBTN(playAgain);
        game.setHomeBTN(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);


    }

    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }
}
