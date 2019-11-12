package com.example.lecture7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Assignment6 extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView value;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment6);
        setupButton();
        setupRatingBar();
    }


    public void setupRatingBar() {
        ratingBar = findViewById(R.id.ratingBar);
        value = findViewById(R.id.value);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                value.setText(String.valueOf(rating));
            }
        });
    }

    public void setupButton() {
        ratingBar = findViewById(R.id.ratingBar);
        button = findViewById(R.id.ratingButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
