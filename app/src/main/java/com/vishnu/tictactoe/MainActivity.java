package com.vishnu.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    //States:
    //p1 -> 0, p2 ->1, empty -> 2
    int [] gameState = {2,2,2,2,2,2,2,2,2}; //All tiles are empty

    int [][] winningPositions = {
            {1,2,3},{4,5,6},{7,8,9}, //Rows
            {1,4,7},{2,5,8},{3,6,9}, //Columns
            {1,5,9},{3,5,7} //Diagonals
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.PlayerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.PlayerTwoScore);
        playerStatus = (TextView) findViewById(R.id.PlayerStatus);

        resetGame = (Button) findViewById(R.id.resetGame);

        //Initializing tiles
        for(int i = 1; i<buttons.length+1; i++){
            String buttonID = "btn"+i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }
}