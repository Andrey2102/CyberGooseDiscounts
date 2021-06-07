package com.example.discount.Mall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.Data.DatabaseHandler;
import com.example.discount.Mall.MallItem;
import com.example.discount.R;
import com.example.discount.sub.ArrayHelper;
import com.example.discount.sub.SubsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.MallHolder> {

    private ArrayList<MallItem>  arrayList;
    public static int i = 0;

    Context context;

    class MallHolder extends  RecyclerView.ViewHolder implements
            View.OnClickListener
    {
        public ImageView imageView;
        public TextView text1;

        int position;
        public Button button;




        public MallHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            boolean changer = true;
            imageView=itemView.findViewById(R.id.mallimage);
            text1=itemView.findViewById(R.id.Name);
            button = (Button) itemView.findViewById(R.id.SubsBut);

            DatabaseHandler databaseHandler = new DatabaseHandler(context);

            //databaseHandler.Delete();


           // Log.d("CarsCount:", String.valueOf(databaseHandler.getSubsCount()));


            i =ArrayHelper.counter;
            if(!arrayList.get(i).getsubs()){
                //Log.d("ArrayHelp", arrayList.get(i).getName()+"__"+ arrayList.get(i).getsubs());
                button.setText("Subscribe");
                ArrayHelper.counter++;
            }else {
                //Log.d("ArrayHelp", arrayList.get(i).getName()+"__"+ arrayList.get(i).getsubs());
                button.setText("UnSubscribe");
                ArrayHelper.counter++;
            }

             itemView.findViewById(R.id.SubsBut).setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick(View v) {
                  position = getAdapterPosition();
                  MallItem mall_item = arrayList.get(position);
                  boolean frelo = true;

                  Button button = (Button) itemView.findViewById(R.id.SubsBut);
                  List<MallItem> mallList = databaseHandler.getAllSubs();

                  for (MallItem mal : mallList) {
                      if(mall_item.getName().equals(mal.getName())){
                          frelo=false;
                          break;
                      }
                  }

                  if(frelo){
                      databaseHandler.addSub(new MallItem(mall_item.getId(),mall_item.getName(),mall_item.getImageResourse(),true));
                      Log.d("CarInfo:", "ID " + mall_item.getName() + "add" );
                      //old
                      arrayList.get(position).ChangeSub();
                      button.setText("UnSubscribe");
                  }else if(databaseHandler.getSubsCount()==1){
                      databaseHandler.Delete();
                      arrayList.get(position).ChangeSub();
                      button.setText("Subscribe");
                  }else{

                      //old
                      databaseHandler.deleteSub(mall_item);
                      arrayList.get(position).ChangeSub();
                      button.setText("Subscribe");
                      Log.d("CarInfo:", "ID " + mall_item.getName() + "was delete" );
                  }
                  for (MallItem mal : mallList) {
                      Log.d("checkBD", mal.getName());
                  }


              }});

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MallItem mall_item = arrayList.get(position);
            Intent intent = new Intent(context, MallActivity.class);
            intent.putExtra("imageRes",mall_item.getImageResourse());
            intent.putExtra("nameRes", mall_item.getName());
            intent.putExtra("Id",String.valueOf(mall_item.getId()));

            context.startActivity(intent);
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
        Picasso.with(context).load(MallItem.getImageResourse()).into(holder.imageView);
        holder.text1.setText(MallItem.getName());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}