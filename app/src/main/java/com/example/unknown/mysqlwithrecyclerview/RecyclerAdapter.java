package com.example.unknown.mysqlwithrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UNKNOWN on 7/3/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {


    List<Fruit> fruitList = new ArrayList<>();
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;

    Context context;
    public RecyclerAdapter (Context context,List<Fruit> fruitList){
        this.fruitList = fruitList;
        this.context = context;
    }

    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEAD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,parent,false);
            RecyclerHolder recyclerHolder = new RecyclerHolder(view,viewType);
            return recyclerHolder;
        }
        else if (viewType == TYPE_LIST){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
            RecyclerHolder recyclerHolder = new RecyclerHolder(view,viewType);
            return recyclerHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerHolder holder, int position) {

        if(holder.viewType == TYPE_LIST){
            Fruit fruit = fruitList.get(position - 1);
            holder.name.setText(fruit.getName());
            holder.id.setText(String.valueOf(fruit.getId()));
            holder.email.setText(fruit.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return fruitList.size() + 1;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView name,id,email;
        int viewType;

        public RecyclerHolder(View itemView , int viewType) {
            super(itemView);
            if(viewType == TYPE_HEAD){
                name = (TextView)itemView.findViewById(R.id.h_name);
                email = (TextView)itemView.findViewById(R.id.h_email);
                id = (TextView)itemView.findViewById(R.id.h_id);
                this.viewType = viewType;
            }
            else if(viewType == TYPE_LIST){
                name = (TextView)itemView.findViewById(R.id.name);
                email = (TextView)itemView.findViewById(R.id.email);
                id = (TextView)itemView.findViewById(R.id.id);
                this.viewType = viewType;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0)
            return TYPE_HEAD;
        return TYPE_LIST;
}
}
