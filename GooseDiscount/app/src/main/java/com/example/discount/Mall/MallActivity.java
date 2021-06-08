package com.example.discount.Mall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.SearchManager;
import android.view.inputmethod.EditorInfo;

import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;
import androidx.core.view.MenuItemCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.API.HttpClient;
import com.example.discount.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductItem> ProdArray;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        setContentView(R.layout.activity_mall);
       double i=1;
        TextView Titlel = findViewById(R.id.TitletextView);


        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.thisid);
        final TextView title = findViewById(R.id.TitletextView);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getBoolean("OTHER",true)){
            layout.setBackgroundColor(Color.WHITE);
            title.setTextColor(Color.parseColor("#353434"));
        }else{
            layout.setBackgroundColor(Color.parseColor("#353434"));
            title.setTextColor(Color.parseColor("#F3F3F3"));
        }

        String valuta = sharedPreferences.getString("CURRENCY", "1");

        if(valuta.equals("1")){
            i=1;
        } else if(valuta.equals("0.030")){
            i= 0.030;

        }else if(valuta.equals("0.036")){
            i= 0.036;
        }





        ProdArray = new ArrayList();
        Intent intent = getIntent();
        HttpClient client=new HttpClient();
        Thread MallList = new Thread(new Runnable() {
            public void run() {
                try {
                    ProdArray = client.readProduct(intent.getStringExtra("Id"));
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

        for(ProductItem item: ProdArray){
            item.ChangeValue(i);
        }

        recyclerView = findViewById(R.id.recycle_item);
        recyclerView.setHasFixedSize(true);
        adapter = new ProductAdapter(ProdArray);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        if (intent != null) {
            Titlel.setText(intent.getStringExtra("nameRes"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if ( TextUtils.isEmpty ( newText ) ) {
                    adapter.getFilter().filter("");
                } else {
                    adapter.getFilter().filter(newText.toString());
                }
                return false;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }



}