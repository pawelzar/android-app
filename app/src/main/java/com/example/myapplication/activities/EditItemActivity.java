package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.structure.Item;
import com.example.myapplication.R;
import com.example.myapplication.database.ItemDatabase;


public class EditItemActivity extends ActionBarActivity {

    public static final String ITEM_EXTRA_KEY = "item";
    private Item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        item = (Item) i.getExtras().getSerializable(ITEM_EXTRA_KEY);

        setContentView(R.layout.add_item);
        setupViews();
    }

    private void setupViews() {
        final EditText itemNameEditText = (EditText) findViewById(R.id.item_name_edit_text);
        final EditText descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        final Button saveButton = (Button) findViewById(R.id.save_item_button);

        itemNameEditText.setText(item.getName());
        descriptionEditText.setText(item.getDescription());
        saveButton.setText(R.string.edit_item);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = itemNameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                update(new Item(name, description));
            }
        });
    }

    private void update(Item item) {
        if(item.getName().equals("")) {
            Toast.makeText(this, "Must specify name", Toast.LENGTH_SHORT).show();
        }
        else {
            ItemDatabase itemDb = new ItemDatabase(this);
            itemDb.updateItem(this.item.getName(), item);

            Toast.makeText(this, "Edited " + this.item.getName(), Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, ItemDetailsActivity.class);
            i.putExtra(ItemDetailsActivity.ITEM_EXTRA_KEY, item);
            setResult(RESULT_OK, i);

            finish();
        }
    }
}
