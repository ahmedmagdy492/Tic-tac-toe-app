package com.migo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnMultiPlayers, btnAbout;

    private void initWidgets()
    {
        btnStart = findViewById(R.id.btnStart);
        btnMultiPlayers = findViewById(R.id.btnMulti);
        btnAbout = findViewById(R.id.btnAbout);
    }

    private void setOnClickListeners()
    {
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: create a new activity
                Toast.makeText(MainActivity.this, getString(R.string.vsCom), Toast.LENGTH_SHORT).show();
            }
        });

        btnMultiPlayers.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: create a new activity
                Intent intent = new Intent(MainActivity.this, TwoPlayersActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO: create a new activity
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setOnClickListeners();
    }
}
