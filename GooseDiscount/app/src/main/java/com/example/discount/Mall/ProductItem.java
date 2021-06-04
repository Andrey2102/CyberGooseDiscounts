package com.example.discount.Mall;

public class ProductItem {
    private String imageProduct;
    private  String NameProduct;
    private String valnew;
    private String valold;
    private String date;

    public ProductItem(String imageResourse, String NameProduct, String valnew, String valold, String date){
        this.imageProduct= imageResourse;
        this.NameProduct=NameProduct;
        this.valnew=valnew;
        this.valold= valold;
        this.date= date;
    }
    public String getimageProduct(){
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
