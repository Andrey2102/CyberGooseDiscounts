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

import com.example.discount.Data.DatabaseHandler;
import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.sub.ArrayHelper;
import com.example.discount.sub.SubsActivity;

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
        //Наша главная страница


            String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        mallArray = new ArrayList();
        mallArray.add(new MallItem(1,"АТБ",imageUri,false));
        mallArray.add(new MallItem(2,"Cільпо",imageUri, false));
        mallArray.add(new MallItem(3,"Екомаркет",imageUri, false));
        mallArray.add(new MallItem(4,"Cільпо2",imageUri, false));
        mallArray.add(new MallItem(5,"Екомаркет2",imageUri, false));


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

            for (MallItem malsub : ArrayHelper.fullArray) {
                //Log.d("ArrayHelp", malsub.getName()+"__"+ malsub.getsubs() );
            }

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

