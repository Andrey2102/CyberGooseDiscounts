package com.example.discount.sub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.Mall.ProductAdapter;
import com.example.discount.R;

import java.util.ArrayList;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.SubsHolder> {

    private ArrayList<MallItem> subarrayList;
    public SubsAdapter(ArrayList<MallItem> arrayList){
        this.subarrayList=arrayList;

    }


    class SubsHolder extends  RecyclerView.ViewHolder  {
        public ImageView imageView;
        public TextView text1;
        public TextView text2;

        public SubsHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.mallimagesub);
            text1 = itemView.findViewById(R.id.Namesub);
            text2 = itemView.findViewById(R.id.TVtextsub);
        }
    }

    @NonNull
    @Override
    public SubsAdapter.SubsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subs_item,
                parent,false);
        SubsAdapter.SubsHolder SubsHold = new SubsAdapter.SubsHolder(view);
        return SubsHold;
    }

    @Override
    public void onBindViewHolder(@NonNull  SubsHolder holder, int position) {
        MallItem MallItem = subarrayList.get(position);
        holder.imageView.setImageResource(MallItem.getImageResourse());
        holder.text1.setText(MallItem.getName());
        holder.text2.setText(MallItem.getAbout());
    }

    @Override
    public int getItemCount() {
        return subarrayList.size();
    }
}
