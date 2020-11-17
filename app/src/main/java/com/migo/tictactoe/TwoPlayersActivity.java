package com.migo.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayersActivity extends AppCompatActivity {

    private TextView txtTurn;
    private boolean isPlaying = false;
    private Button btn0, btn1, btn2, btn4, btn5, btn6, btn7, btn8, btn9;

    // player 1 is X and player 2 is O
    private String currentTurn = "player1";

    private void initWidgets()
    {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn4 = findViewById(R.id.btnSpecial);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        txtTurn = findViewById(R.id.playerTurn);
    }

    private void init()
    {
        btn0.setText("-");
        btn1.setText("-");
        btn2.setText("-");
        btn4.setText("-");
        btn5.setText("-");
        btn6.setText("-");
        btn7.setText("-");
        btn8.setText("-");
        btn9.setText("-");

        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.again));
        builder.setTitle(getString(R.string.over));
        builder.setCancelable(false);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // restart the game
                Button btnStart = findViewById(R.id.btnStart);
                btnStart.performClick();
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // go to the main activity
                Intent intent = new Intent(TwoPlayersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.create().show();
    }

    private void findWhoWin(String s)
    {
        // horizonetal
        boolean pattern1 = btn0.getText().equals(s) && btn1.getText().equals(s) && btn2.getText().equals(s);
        boolean pattern2 = btn4.getText().equals(s) && btn5.getText().equals(s) && btn6.getText().equals(s);
        boolean pattern3 = btn7.getText().equals(s) && btn8.getText().equals(s) && btn9.getText().equals(s);

        // vertical
        boolean pattern5 = btn0.getText().equals(s) && btn4.getText().equals(s) && btn7.getText().equals(s);
        boolean pattern6 = btn1.getText().equals(s) && btn5.getText().equals(s) && btn8.getText().equals(s);
        boolean pattern7 = btn2.getText().equals(s) && btn6.getText().equals(s) && btn9.getText().equals(s);

        // diagnols
        boolean pattern8 = btn0.getText().equals(s) && btn5.getText().equals(s) && btn9.getText().equals(s);
        boolean pattern9 = btn2.getText().equals(s) && btn5.getText().equals(s) && btn7.getText().equals(s);

        if(pattern1 || pattern2 || pattern3 || pattern5 || pattern6 || pattern7 || pattern8 || pattern9)
        {
            txtTurn.setText(s + " Won");
            isPlaying = false;
            Button btnStart = findViewById(R.id.btnStart);
            btnStart.setText(getString(R.string.start_game));
            btnStart.setEnabled(true);
            showDialog();
        }
        else if(!isPlaying)
        {
            // draw
            txtTurn.setText("Draw");
            isPlaying = false;
            Button btnStart = findViewById(R.id.btnStart);
            btnStart.setText(getString(R.string.start_game));
            btnStart.setEnabled(true);
            showDialog();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        initWidgets();
    }

    @Override
    public void onBackPressed() {
        if(isPlaying)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.quit));
            builder.setTitle("Warnning");
            builder.setIcon(R.drawable.ic_action_name);

            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });

            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TwoPlayersActivity.super.onBackPressed();
                }
            });

            builder.create().show();
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void btnClicked(View view)
    {
        if(isPlaying)
        {
            Button clickedBtn = (Button)view;

            if(currentTurn.equals("player1"))
            {
                clickedBtn.setText("X");
                currentTurn = "player2";
                txtTurn.setText(getString(R.string.player2) + " - O");
                findWhoWin("X");
            }
            else
            {
                clickedBtn.setText("O");
                currentTurn = "player1";
                txtTurn.setText(getString(R.string.player1) + " - X");
                findWhoWin("O");
            }

            clickedBtn.setEnabled(false);
        }
    }

    @SuppressLint("SetTextI18n")
    public void startGame(View view)
    {
        if(!isPlaying)
        {
            init();
            isPlaying = true;
            view.setEnabled(false);
            ((Button)view).setText(getString(R.string.playing));
            currentTurn = "player1";
            txtTurn.setText(getString(R.string.player1) + " - X");
        }
    }
}
