package com.example.discount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.discount.Settings.SettingsActivity;
import com.example.discount.sub.SubsActivity;

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

        final LinearLayout layout = (LinearLayout) findViewById(R.id.aboutACT);
        final TextView title = findViewById(R.id.about_text);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getBoolean("OTHER",true)){
            layout.setBackgroundColor(Color.WHITE);
            title.setTextColor(Color.parseColor("#353434"));
        }else{
            layout.setBackgroundColor(Color.parseColor("#353434"));
            title.setTextColor(Color.parseColor("#F3F3F3"));
        }

        view.setText("Нас называют гиками и красноглазиками, обычные люди думают, что мы живем в серверных или у мамы в подвале, едим один дошик и моемся жидкостью для чистки мониторов (на самом деле это не совсем правда).\n" +
                "Мы занимаемся только оригинальными и интересными проектами какими бы сложными и длинными они не были, так как всегда ищем способ показать наш креатив =D.\n" +
                "Support:\n" +
                "+(38)068-801-75-75\n" +
                "Email: cyber_goose@gmail.com");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.discount_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        if(id==R.id.action_setings){
            Intent openSettings = new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
            return true;
        }else if(id==R.id.action_about){
            Intent openAbout = new Intent(this, AboutActivity.class);
            startActivity(openAbout);
            return true;}
        else if(id==R.id.action_subs){
            Intent openSubs = new Intent(this, SubsActivity.class);
            startActivity(openSubs);
            return true;}
        else if(id==R.id.action_home){
            Intent openHome = new Intent(this, MainActivity.class);
            startActivity(openHome);
            return true;}

        return super.onOptionsItemSelected(item);
    }
}