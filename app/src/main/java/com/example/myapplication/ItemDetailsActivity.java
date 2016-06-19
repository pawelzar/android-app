package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ItemDetailsActivity extends ActionBarActivity {

    public static final String ITEM_EXTRA_KEY = "item";

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        Intent i = getIntent();
        item = (Item) i.getExtras().getSerializable(ITEM_EXTRA_KEY);

        showItem(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_item: {
                startEditItemActivity();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void showItem(Item item) {
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);

        title.setText(item.getName());
        description.setText(item.getDescription());
    }

    private void startEditItemActivity() {
        Intent i = new Intent(this, EditItemActivity.class);
        i.putExtra(EditItemActivity.ITEM_EXTRA_KEY, item);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                item = (Item) data.getExtras().getSerializable(ITEM_EXTRA_KEY);
                showItem(item);
            }
        }
    }
}
