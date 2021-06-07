package com.example.discount.API;

import android.util.Log;

import com.example.discount.Mall.MallItem;
import com.example.discount.Mall.ProductItem;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpClient {

    private final JsonParser jsonParser;

    public HttpClient(){
        jsonParser = new JsonParser();
    }

    public ArrayList<MallItem> readMall() throws IOException, JSONException {
        String requestUrl = "https://cybergooseapi.azurewebsites.net/api/Shop";
        ArrayList<MallItem> mallItems =new ArrayList<MallItem>();
        HttpURLConnection connection=null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            InputStream in;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                in = connection.getErrorStream();
            } else {
                in = connection.getInputStream();
            }

            String response = convertStreamToString(in);
            mallItems=jsonParser.GetMall(response);
        }catch(Exception ex0)
        {
            Log.d("Error : ",ex0.toString());
        }
        finally
        {
            if(connection!=null){connection.disconnect(); 	}

        }

        return mallItems;
    }

    public ArrayList<ProductItem> readProduct(String id) throws IOException, JSONException {
        String requestUrl = "https://cybergooseapi.azurewebsites.net/api/Discount/"+id;
        ArrayList<ProductItem> productItems =new ArrayList<ProductItem>();
        HttpURLConnection connection=null;
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            InputStream in;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                in = connection.getErrorStream();
            } else {
                in = connection.getInputStream();
            }

            String response = convertStreamToString(in);
            productItems=jsonParser.GetProduct(response);
        }catch(Exception ex0)
        {
            Log.d("Error : ",ex0.toString());
        }
        finally
        {
            if(connection!=null){connection.disconnect(); 	}

        }

        return productItems;
    }

    private String convertStreamToString(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        stream.close();

        return sb.toString();
    }


}
