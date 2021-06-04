package com.example.discount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.sub.ArrayHelper;
import com.example.discount.sub.SubsActivity;

import java.util.ArrayList;

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
        //Наша главная страница
        ArrayHelper help = new ArrayHelper();
        Log.v("bc", "it is"+ help.start);
        if(help.start){
            String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        mallArray = new ArrayList();
        mallArray.add(new MallItem(imageUri,"АТБ",false, "бла"));
        mallArray.add(new MallItem(imageUri,"Cільпо", false,"бла"));
        mallArray.add(new MallItem(imageUri,"Екомаркет", false,"бла"));
        mallArray.add(new MallItem(imageUri,"Cільпо", false,"бла"));
        mallArray.add(new MallItem(imageUri,"Екомаркет", false,"бла"));
            help.start=false;
        Log.v("bc2", "it is"+ help.start);
        }else{
            mallArray=help.fullArray;
        }

        help.fullArray=mallArray;


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

