package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.ItemDatabase;

public class AddItemActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);
        setupViews();
    }

    private void setupViews() {
        final EditText itemNameEditText = (EditText) findViewById(R.id.item_name_edit_text);
        final EditText descriptionEditText = (EditText) findViewById(R.id.description_edit_text);

        Button saveButton = (Button) findViewById(R.id.save_item_button);
        saveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = itemNameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                saveItem(new Item(name, description));
            }
        });
    }

    private void saveItem(Item item) {
        if(item.getName().equals("")) {
            Toast.makeText(this, "Must specify name", Toast.LENGTH_SHORT).show();
        }
        else {
            ItemDatabase itemDb = new ItemDatabase(this);
            itemDb.addItem(item);

            Toast.makeText(this, "Item " + item.getName() + " saved", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
