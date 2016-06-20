package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.structure.Item;
import com.example.myapplication.R;
import com.example.myapplication.database.ItemDatabase;


public class ItemAdapter extends BaseAdapter {

    private ItemDatabase provider;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
        this.provider = new ItemDatabase(context);
    }

    @Override
    public int getCount() {
        return provider.getItemsNumber();
    }

    @Override
    public Item getItem(int position) {
        return provider.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;

        if (convertView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        } else {
            itemView = convertView;
        }

        bindItemToView(getItem(position), itemView);

        return itemView;
    }

    private void bindItemToView(Item item, View itemView) {
        TextView itemLabel = (TextView) itemView.findViewById(R.id.item_label);
        itemLabel.setText(item.getName());
    }

    public void deleteAll() {
        provider.deleteAll();
    }

    public void deleteItem(String name) {
        provider.deleteItem(name);
    }
}
