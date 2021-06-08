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
import com.pixplicity.sharp.Sharp;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.SubsHolder> {

    private ArrayList<MallItem> subarrayList;
    private static OkHttpClient httpClient;
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
            intent.putExtra("Id",String.valueOf(mall_item.getId()));
            //intent.putExtra("DescriptionRes", mall_item.getAbout());
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
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(MallItem.getImageResourse()).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream stream = response.body().byteStream();
                Sharp.loadInputStream(stream).into(holder.imageView);
                stream.close();
            }
        });
        Picasso.with(context).load(MallItem.getImageResourse()).into(holder.imageView);
        holder.text1.setText(MallItem.getName());
        //holder.text2.setText(MallItem.getAbout());
    }

    @Override
    public int getItemCount() {
        return subarrayList.size();
    }
}
