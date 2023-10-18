package com.example.amongger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
//import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

    private EditText inputName;
    private String collectName = null;
    private Button okButton;
    private AlertDialog.Builder alert;
    private Button exitButton;

    private Button startButton;

    //Difficulty Buttons
    private ImageButton difficulty1;
    private ImageButton difficulty2;
    private ImageButton difficulty3;

    private int difficulty = 0;

    //Sprite Buttons
    private ImageButton yellowMogus;
    private ImageButton redMogus;
    private ImageButton greenMogus;

    private int colorId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().getInsetsController().hide(WindowInsetsCompat.Type.systemBars());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        inputName = findViewById(R.id.name_field);
        okButton = findViewById(R.id.nameField_OkButton);
        exitButton = findViewById(R.id.exit_button);
        startButton = findViewById(R.id.start_button);
        alert = new AlertDialog.Builder(this);

        diffButtonHandler();
        spriteButtonHandler();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOkButtonClick();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleExitButtonClick();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (collectName != null) {
                    initializeGame();
                }
            }
        });
    }


    private void handleOkButtonClick() {
        collectName = inputName.getText().toString();
        if (collectName.isEmpty() || collectName == null) {
            alert.setTitle("Please try again!")
                    .setMessage("This field cannot leave empty")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        } else if (collectName.trim().isEmpty()) {
            alert.setTitle("Invalid input!")
                    .setMessage("Please try again.")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        } else {
            closeKeyboard();
        }
    }

    /**
     * After click ok, close keyboard
     */
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    private void handleExitButtonClick() {
        alert.setTitle("Confirmation")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(true)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            })
            .show();
    }

    /**
     * Starts up Game Screen. Passes
     * player name and difficulty into
     * GameActivity.
     */
    public void initializeGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("name", collectName);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("color", colorId);
        startActivity(intent);
        finish();
    }


    /**
     * Handles difficulty button initialization, handles button click
     * events for all three difficulty levels
     */
    public void diffButtonHandler() {
        difficulty1 = findViewById(R.id.level1_button);
        difficulty2 = findViewById(R.id.level2_button);
        difficulty3 = findViewById(R.id.level3_button);

        difficulty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 0;
            }
        });

        difficulty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 1;
            }
        });

        difficulty3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 2;
            }
        });
    }

    /**
     * Handles sprite button initialization, handles button click
     * events for all three sprite colors
     */
    public void spriteButtonHandler() {
        yellowMogus = findViewById(R.id.sprite_1button);
        redMogus = findViewById(R.id.sprite_2button);
        greenMogus = findViewById(R.id.sprite_3button);

        yellowMogus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorId = 0;
            }
        });

        redMogus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorId = 1;
            }
        });

        greenMogus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorId = 2;
            }
        });
    }
}