package com.example.discount.sub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.discount.AboutActivity;
import com.example.discount.Data.DatabaseHandler;
import com.example.discount.MainActivity;
import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.R;
import com.example.discount.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class SubsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subs);

        ArrayList<MallItem> subsArrayfull = new ArrayList();
        ArrayList<MallItem> subsArray = new ArrayList();
        Intent intent = getIntent();
        Log.v("bl", "it is"+ intent.getStringExtra("nameSub"));

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Log.d("CarsCount:", String.valueOf(databaseHandler.getSubsCount()));

        ArrayList<MallItem> carList = databaseHandler.getAllSubs();
        subsArray=carList;


        recyclerView=findViewById(R.id.recycle_sub);
        recyclerView.setHasFixedSize(true);
        adapter = new SubsAdapter(subsArray,this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);


        }
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