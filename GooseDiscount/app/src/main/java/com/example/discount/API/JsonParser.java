package com.example.discount.API;

import com.example.discount.Mall.MallItem;
import com.example.discount.Mall.ProductItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JsonParser {

        public ArrayList<MallItem> GetMall(String response) throws JSONException
        {
                ArrayList<MallItem> mallItems=new ArrayList<MallItem>();
                JSONArray JsArr=new JSONArray(response);
                for (int i=0;i<JsArr.length();i++) {
                        JSONObject js=JsArr.getJSONObject(i);
                        mallItems.add(new MallItem(js.getInt("shopId"),js.getString("name"),js.getString("imageLink"),false));
                }
                return  mallItems;
        }

        public ArrayList<ProductItem> GetProduct(String response) throws JSONException
        {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                ArrayList<ProductItem> productItems=new ArrayList<ProductItem>();
                JSONArray JsArr=new JSONArray(response);
                for (int i=0;i<JsArr.length();i++) {
                        JSONObject js=JsArr.getJSONObject(i);
                        productItems.add(new ProductItem(js.getString("imageLink"),js.getString("name"),js.getString("newPrice"), //Комаентарий для владика(Здесь)
                                js.getString("oldPrice"), dateFormat.format(cal.getTime())));
                }
                return productItems;
        }
}
