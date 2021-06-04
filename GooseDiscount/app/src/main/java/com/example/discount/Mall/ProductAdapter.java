package com.example.discount.Mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discount.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {
    private ArrayList<ProductItem> arrayListProd;
    private ArrayList<ProductItem> arrayListProdSearch;
    Context context;

    public ProductAdapter(ArrayList<ProductItem> arrayListProd){
        this.arrayListProd=arrayListProd;
        arrayListProdSearch= new ArrayList<>(arrayListProd);
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

        Picasso.with(context).load(productItem.getimageProduct()).into(holder.imageproduct);

        holder.Nameproduct.setText(productItem.getNameProduct());
        holder.valnew.setText(productItem.getvalnew());
        holder.valold.setText(productItem.getvalold());
        holder.date.setText(productItem.getdate());
    }

    @Override
    public int getItemCount() {
        return arrayListProd.size();
    }

    @Override
    public Filter getFilter() {
        return ProductFilter;
    }

    private Filter ProductFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ProductItem> FilterList= new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                FilterList.addAll(arrayListProdSearch);
            }else {
                String FilterPattern = constraint.toString().toLowerCase().trim();

                for(ProductItem item: arrayListProdSearch){
                    if(item.getNameProduct().toLowerCase().contains(FilterPattern)){
                        FilterList.add(item);
                    }
                }
            }
            FilterResults Results = new FilterResults();
            Results.values = FilterList;
            return Results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayListProd.clear();
            arrayListProd.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

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
