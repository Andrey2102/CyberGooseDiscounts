package com.example.discount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        TextView view = findViewById(R.id.about_text);
        view.setText("Нас называют гиками и красноглазиками, обычные люди думают, что мы живем в серверных или у мамы в подвале, едим один дошик и моемся жидкостью для чистки мониторов (на самом деле это не совсем правда).\n" +
                "Мы занимаемся только оригинальными и интересными проектами какими бы сложными и длинными они не были, так как всегда ищем способ показать наш креатив =D.\n" +
                "Support:\n" +
                "+(38)068-801-75-75\n" +
                "Email: cyber_goose@gmail.com");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }
}