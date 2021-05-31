package com.example.discount.Mall;

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

import com.example.discount.R;

import java.util.ArrayList;

public class MallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        setContentView(R.layout.activity_mall);
       double i=1;
        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.thisid);
        TextView Titlel = findViewById(R.id.TitletextView);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getBoolean("OTHER",true)){
            layout.setBackgroundColor(Color.WHITE);
        }else{
            layout.setBackgroundColor(Color.parseColor("#9932CC"));
        }

        String valuta = sharedPreferences.getString("CURRENCY", "1");
        Log.i("chek","it is " + valuta);

        if(valuta.equals("1")){
            i=1;
        } else if(valuta.equals("0.030")){
            i= 0.030;
            Log.i("chek","it was");
        }else if(valuta.equals("0.036")){
            i= 0.036;
            Log.i("chek","it was too");
        }

        Log.i("chek","it is " + i);



        ArrayList<ProductItem> ProdArray = new ArrayList();
        ProdArray.add(new ProductItem(R.drawable.bak, "Баклажан1", String.valueOf(100*i), String.valueOf(150*i), "04.05.21 - 05.02.21"));
        ProdArray.add(new ProductItem(R.drawable.bak, "example", String.valueOf(80*i), String.valueOf(111*i), "04.05.21 - 05.02.21"));
        Intent intent = getIntent();
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