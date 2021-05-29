package com.example.discount;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.Stream;

public class ProductItem {
    private int imageProduct;
    private  String NameProduct;
    private String valnew;
    private String valold;
    private String date;

    public ProductItem(int imageResourse, String NameProduct, String valnew, String valold, String date){
        this.imageProduct= imageResourse;
        this.NameProduct=NameProduct;
        this.valnew=valnew;
        this.valold= valold;
        this.date= date;
    }
    public int getimageProduct(){
        return imageProduct;
    }
    public String getNameProduct(){
        return NameProduct;
    }
    public String getvalnew(){
        return valnew;
    }
    public String getvalold(){return valold;}
    public String getdate(){return date;}


}
