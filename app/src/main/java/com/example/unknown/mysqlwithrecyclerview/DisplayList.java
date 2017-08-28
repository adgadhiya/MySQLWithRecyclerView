package com.example.unknown.mysqlwithrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class DisplayList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        BackGrounfTak backGrounfTak = new BackGrounfTak(DisplayList.this);
        backGrounfTak.execute();
    }
}
