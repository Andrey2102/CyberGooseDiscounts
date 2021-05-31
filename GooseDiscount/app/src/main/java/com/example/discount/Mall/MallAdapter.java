package com.example.discount.Mall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.Mall.MallItem;
import com.example.discount.R;
import com.example.discount.sub.SubsActivity;

import java.util.ArrayList;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.MallHolder> {

    private ArrayList<MallItem>  arrayList;

    Context context;

    class MallHolder extends  RecyclerView.ViewHolder implements
            View.OnClickListener
    {
        public ImageView imageView;
        public TextView text1;
        int position;



        public MallHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.mallimage);
            text1=itemView.findViewById(R.id.Name);

             itemView.findViewById(R.id.SubsBut).setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick(View v) {
                  int position = getAdapterPosition();
                  Log.v("bl", "it is"+ position);
                  MallItem mall_item = arrayList.get(position);
                  Intent intent = new Intent(context, SubsActivity.class);
                  intent.putExtra("imgsub",mall_item.getImageResourse());
                  intent.putExtra("nameSub", mall_item.getName());
                  Log.v("bl", "it is"+ mall_item.getName());
                  context.startActivity(intent);
              }});

        }


        @Override
        public void onClick(View v) {

        }
    }
    public MallAdapter(ArrayList<MallItem> arrayList,Context context){

        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public MallAdapter.MallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mall_item,
                parent,false);
        MallHolder MallHold = new MallHolder(view);
        return MallHold;
    }

    @Override
    public void onBindViewHolder(@NonNull MallHolder holder, int position) {
        MallItem MallItem = arrayList.get(position);
        holder.imageView.setImageResource(MallItem.getImageResourse());
        holder.text1.setText(MallItem.getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static class mall_recipe extends  RecyclerView.ViewHolder{

        public  ImageView  imageView;
        public TextView text1;



        public mall_recipe(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.mallimage);
            text1=itemView.findViewById(R.id.Name);

        }
    }

}