package com.example.tigergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int count;

    ImageView tappedImageView;

    enum Player{
        ONE, TWO, NO
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    private GridLayout mGridLayout;

    private boolean gameOver = false;

    private Button btnReset;

    int[][] winnerRowsColumns = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridLayout = findViewById(R.id.gridLayout);
        btnReset = findViewById(R.id.btnReset);

        for(int i=0; i<=8 ; i++)
        {
            playerChoices[i] = Player.NO;
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


//        for(int i=0; i<9; i++) {
//            if (playerChoices[i] != Player.NO){
//                btnReset.setVisibility(View.VISIBLE);
//            }
//        }


    }

    public void imgIsTapped(View imageView) {

        count++;
        if(count == 9){
            btnReset.setVisibility(View.VISIBLE);
        }
        tappedImageView = (ImageView) imageView;
        int tappedImageTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tappedImageTag] == Player.NO && gameOver == false) {
            tappedImageView.setTranslationX(-1000);

            playerChoices[tappedImageTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);

                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.lion);

                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(1000).alpha(1).rotation(3600).setDuration(1000);

            // Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.NO) {
                    gameOver = true;
                    btnReset.setVisibility(View.VISIBLE);
                    String winnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Lion is the winner";

                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Tiger is the winner";
                    }
                    Toast.makeText(this, winnerOfGame, Toast.LENGTH_LONG).show();

                }
            }
        }
    }



//Reset the Game

    private void restartGame(){

        for(int index = 0 ; index < mGridLayout.getChildCount() ; index++){

           ImageView imageView = (ImageView) mGridLayout.getChildAt(index);
           imageView.setImageDrawable(null);
           imageView.setAlpha(0.4f);
           gameOver = false;
           currentPlayer = Player.ONE;

            for(int i=0; i<=8 ; i++)
            {
                playerChoices[i] = Player.NO;
            }

            count =0;
            imageView.animate().rotation(-3600).setDuration(1);
            btnReset.setVisibility(View.GONE);

        }


    }

}
