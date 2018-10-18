package com.example.neto.exemplomenuaction;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class ListAdapter extends ListFragment {

    private ArrayAdapter<String> adapter;
    private OnItemClick listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof OnItemClick)){
            throw new RuntimeException("Deve ser um OnItemClick");
        }

        listener = (OnItemClick)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);

        setListAdapter(this.adapter);
    }

    public String get(int position){
        return this.adapter.getItem(position);
    }

    public void add(String value){
        this.adapter.add(value);
        this.adapter.notifyDataSetChanged();
    }

    public void insert(int p, String value){
        this.adapter.remove(this.adapter.getItem(p));
        this.adapter.insert(value,p);
        this.adapter.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        this.adapter.notifyDataSetChanged();
    }

    public void delete(int p){
        this.adapter.remove(this.adapter.getItem(p));
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(listener != null){
            listener.onClick(adapter.getItem(position), v, position);
        }

    }

    public interface OnItemClick{
       void onClick(String item, View iv, int position);
    }

}
