package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.structure.Item;
import com.example.myapplication.adapter.ItemAdapter;
import com.example.myapplication.R;


public class ItemListActivity extends ActionBarActivity {

    private ListView listView;
    private ItemAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        initializeView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void initializeView() {
        listView = (ListView) findViewById(R.id.item_list);
        adapter = new ItemAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                showItem(item);
            }
        });
    }

    private void showItem(Item item) {
        Intent i = new Intent(this, ItemDetailsActivity.class);
        i.putExtra(ItemDetailsActivity.ITEM_EXTRA_KEY, item);
        startActivity(i);
    }

    private void startAddItemActivity() {
        Intent i = new Intent(this, AddItemActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_item: {
                startAddItemActivity();
                return true;
            }
            case R.id.delete_all: {
                deleteAllItems();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void deleteAllItems() {
        if (adapter.getCount() == 0) {
            Toast.makeText(this, "No elements", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.deleteAll();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Deleted all elements", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.item_label);
        String task = String.valueOf(taskTextView.getText());

        adapter.deleteItem(task);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Deleted " + task, Toast.LENGTH_SHORT).show();
    }
}
