package com.example.discount.Mall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.discount.R;

public class MallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        TextView Titlel = findViewById(R.id.TitletextView);
        TextView Recipe = findViewById(R.id.RecipetextView2);

        Intent intent = getIntent();
        if(intent != null) {

            Titlel.setText(intent.getStringExtra("nameRes"));
            Recipe.setText(intent.getStringExtra("recipeRes"));
        }
        }
    }