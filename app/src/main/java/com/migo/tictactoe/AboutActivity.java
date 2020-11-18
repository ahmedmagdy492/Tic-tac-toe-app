package com.migo.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.Toast;

import java.net.URI;

public class AboutActivity extends AppCompatActivity {

    private Button btnVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        btnVisit = findViewById(R.id.btnVisit);
    }

    public void btnClicked(View view)
    {
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCrdtLRuJjnkEQRsgPMZWaAA?view_as=subscriber"));
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
