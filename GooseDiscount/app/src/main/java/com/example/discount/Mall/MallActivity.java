package com.example.discount.Mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.discount.ProductItem;
import com.example.discount.R;

import java.util.ArrayList;

public class MallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        TextView Titlel = findViewById(R.id.TitletextView);

        ArrayList<ProductItem> ProdArray = new ArrayList();
        ProdArray.add(new ProductItem(R.drawable.bak,"Баклажан1","100", "150", "04.05.21 - 05.02.21"));
        ProdArray.add(new ProductItem(R.drawable.bak,"Баклажан2","80", "111","04.05.21 - 05.02.21"));
        Intent intent = getIntent();
        recyclerView=findViewById(R.id.recycle_item);
        recyclerView.setHasFixedSize(true);
        adapter = new ProductAdapter(ProdArray);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        if(intent != null) {

            Titlel.setText(intent.getStringExtra("nameRes"));
        }
        }
    }