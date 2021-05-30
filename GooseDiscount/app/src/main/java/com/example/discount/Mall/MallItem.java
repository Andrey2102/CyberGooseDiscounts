package com.example.discount.Mall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discount.R;

public class MallItem {
    private int imageResourse;
    private  String Name;
    private String aboutmall;
    private String recipe;

    public MallItem(int imageResourse, String text1, String text2, String recipe){
        this.imageResourse= imageResourse;
        this.Name=text1;
        //this.aboutmall=text2;
        this.recipe = recipe;
    }
    public int getImageResourse(){
        return imageResourse;
    }
    public String getName(){
        return Name;
    }
    //public String getAbout(){return aboutmall; }
    public String getRecipe(){return recipe;}


}
