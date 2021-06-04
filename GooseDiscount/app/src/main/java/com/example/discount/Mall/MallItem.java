package com.example.discount.Mall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discount.R;

public class MallItem {
    private String imageResourse;
    private  String Name;
    private String recipe;
    private boolean subs;


    public MallItem(String imageResourse, String text1, boolean subs, String recipe){
        this.imageResourse= imageResourse;
        this.Name=text1;
        this.subs=subs;
        this.recipe = recipe;
    }


    public String getImageResourse(){
        return imageResourse;
    }
    public String getName(){
        return Name;
    }
    public boolean getsubs(){return subs; }
    public String getRecipe(){return recipe;}




    public void ChangeSub(){
        if(subs==true){
            subs=false;
        }else {subs=true;}


    }


}
