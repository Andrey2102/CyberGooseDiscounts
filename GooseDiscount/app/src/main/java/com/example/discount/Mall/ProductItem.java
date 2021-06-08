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

    public void ChangeValue(Double i){
        double calc=roundAvoid(Double.parseDouble(valnew)*i,2);
        this.valnew=Double.toString(calc);
        calc=roundAvoid(Double.parseDouble(valold)*i,2);
        this.valold=Double.toString(calc);
    }

    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }





}
