package com.vishnu.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            {0,1,2},{3,4,5},{6,7,8}, //Rows
            {0,3,6},{1,4,7},{2,5,8}, //Columns
            {0,4,8},{2,4,6} //Diagonals
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
        for(int i = 0; i<buttons.length; i++){
            String buttonID = "btn_"+i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        //Marking Xs and Os
        if(activePlayer){
            ((Button) v).setText("X");
            ((Button) v).setTextColor(getResources().getColor(R.color.X));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(getResources().getColor(R.color.O));
            gameState[gameStatePointer] = 1;
        }
        roundCount++; //Increment the round count

        //Check for winner on every button tap
        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Player One won!", Toast.LENGTH_SHORT).show();
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Player Two won!", Toast.LENGTH_SHORT).show();
            }
            resetGame();
        } else if(roundCount == 9){
            resetGame();
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
        }else {
            activePlayer = !activePlayer;
        }

        //Setting player win status
        if(playerOneScoreCount > playerTwoScoreCount) {
            playerStatus.setText("Player One is winning");
        } else if(playerTwoScoreCount > playerOneScoreCount) {
            playerStatus.setText("Player Two is winning");
        } else {
            playerStatus.setText("");
        }

        //Implementing reset game button logic
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });

    }

    public boolean checkWinner() {
        boolean winnerResult = false;

        //check game state and winner positions.
        //Iterating through winning positions:
        for(int [] winningPosition: winningPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void resetGame() {
        roundCount = 0;
        activePlayer = true;

        for(int i = 0;i< buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }
}