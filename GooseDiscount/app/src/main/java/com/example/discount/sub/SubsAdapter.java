package com.example.discount.sub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.Mall.MallActivity;
import com.example.discount.Mall.MallAdapter;
import com.example.discount.Mall.MallItem;
import com.example.discount.Mall.ProductAdapter;
import com.example.discount.R;

import java.util.ArrayList;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.SubsHolder> {

    private ArrayList<MallItem> subarrayList;
    Context context;
    public SubsAdapter(ArrayList<MallItem> arrayList,Context context){
        this.subarrayList=arrayList;

        this.context=context;
    }


    class SubsHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView text1;
        public TextView text2;

        public SubsHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.mallimagesub);
            text1 = itemView.findViewById(R.id.Namesub);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MallItem mall_item = subarrayList.get(position);
            Intent intent = new Intent(context, MallActivity.class);
            intent.putExtra("imageRes",mall_item.getImageResourse());
            intent.putExtra("nameRes", mall_item.getName());
            //intent.putExtra("DescriptionRes", mall_item.getAbout());
            intent.putExtra("recipeRes",mall_item.getRecipe());
            context.startActivity(intent);
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
        //holder.text2.setText(MallItem.getAbout());
    }

    @Override
    public int getItemCount() {
        return subarrayList.size();
    }
}
