package com.example.discount.Mall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discount.R;

public class MallItem {
    private String imageResourse;
    private  String Name;
    private int id;
    private boolean subs;


    public void setId(int id) {
        this.id = id;
    }

    public void setImageResourse(String imageResourse) {
        this.imageResourse = imageResourse;
    }

    public void setName(String name) {
        Name = name;
    }

    public MallItem() {
    }

    public MallItem(int id, String text1, String imageResourse, boolean subs){

        this.imageResourse= imageResourse;
        this.Name=text1;
        this.subs=subs;
        this.id = id;
    }


    public String getImageResourse(){
        return imageResourse;
    }
    public String getName(){
        return Name;
    }
    public boolean getsubs(){return subs; }
    public int getId() {
        return id;
    }

    public void ChangeSub(){
        if(subs==true){
            subs=false;
        }else {subs=true;}


    }


}
