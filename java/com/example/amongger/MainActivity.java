package com.example.amongger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton getStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Fullscreen. Removes title bar and hides navigation bar at the bottom.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());

        getStartButton = findViewById(R.id.button2);
        getStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfigScreen();
            }
        });
    }

    /**
     * open config screen
     */
    public void openConfigScreen() {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }
}