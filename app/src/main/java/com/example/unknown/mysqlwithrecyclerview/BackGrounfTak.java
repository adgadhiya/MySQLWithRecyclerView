package com.example.unknown.mysqlwithrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by UNKNOWN on 7/3/2016.
 */
public class BackGrounfTak extends AsyncTask<Void,Fruit,Void> {

    String json_string = "http://10.3.0.1/series/dynamic/Checking/fruit_details.php";
    Context context;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Fruit> fruits = new ArrayList<>();

    public BackGrounfTak(Context context){
        this.context = context;
        activity = (Activity) context;
    }


    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView)activity.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerAdapter(context,fruits);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL(json_string);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine())  != null){
                sb.append(line + "\n");
            }

            urlConnection.disconnect();
            is.close();
            String json_string = sb.toString().trim();

            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            while (count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                count++;

                Fruit fruit = new Fruit(object.getInt("id"),object.getString("first"),object.getString("email"));
                publishProgress(fruit);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Fruit... values) {

        fruits.add(values[0]);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
