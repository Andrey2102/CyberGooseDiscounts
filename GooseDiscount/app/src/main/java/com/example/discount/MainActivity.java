package com.example.discount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.discount.API.HttpClient;
import com.example.discount.Data.DatabaseHandler;
import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.Settings.SettingsActivity;
import com.example.discount.sub.ArrayHelper;
import com.example.discount.sub.SubsActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MallItem> mallArray;
    private boolean subsstarter=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainACT);


        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getBoolean("OTHER",true)){
            layout.setBackgroundColor(Color.WHITE);


        }else{
            layout.setBackgroundColor(Color.parseColor("#353434"));


        }
        //Наша главная страница




        HttpClient client=new HttpClient();
        Thread MallList = new Thread(new Runnable() {
            public void run() {
                try {
                    mallArray = client.readMall();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        MallList.start();
        try {
            MallList.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<MallItem> mallList = databaseHandler.getAllSubs();
        if(mallList!= null) {
            for (MallItem mal : mallArray) {
                for (MallItem malsub : mallList) {
                    if (mal.getName().equals(malsub.getName())) {
                            mal.ChangeSub();

                            break;
                    }
                }
            }
            ArrayHelper.fullArray=mallArray;

        }
        ArrayHelper.counter=0;





        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        adapter = new MallAdapter(mallArray, this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }



    //Наши настройки
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.discount_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
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

