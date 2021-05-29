package com.example.discount.Mall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.ProductItem;
import com.example.discount.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<ProductItem> arrayListProd;

    public ProductAdapter(ArrayList<ProductItem> arrayListProd){
        this.arrayListProd=arrayListProd;
    }

    @NonNull

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mall_product_item,
                parent,false);
        ProductViewHolder ProductHold = new ProductViewHolder(view);
        return ProductHold;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItem productItem = arrayListProd.get(position);
        holder.imageproduct.setImageResource(productItem.getimageProduct());
        holder.Nameproduct.setText(productItem.getNameProduct());
        holder.valnew.setText(productItem.getvalnew());
        holder.valold.setText(productItem.getvalold());
        holder.date.setText(productItem.getdate());
    }

    @Override
    public int getItemCount() {
        return arrayListProd.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageproduct;
    public TextView Nameproduct;
    public TextView valnew;
    public TextView valold;
    public TextView date;

    public ProductViewHolder(@NonNull  View itemView) {
        super(itemView);

        imageproduct=itemView.findViewById(R.id.product_image);
        Nameproduct=itemView.findViewById(R.id.NameProduct);
        valnew=itemView.findViewById(R.id.valuenew);
        valold=itemView.findViewById(R.id.valueold);
        date=itemView.findViewById(R.id.date);
    }
}

}
