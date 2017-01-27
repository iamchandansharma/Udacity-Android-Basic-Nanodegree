package com.example.android.inventorymanagement;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.inventorymanagement.data.InventoryContract.InventoryEntry;

public class InventoryActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Identifier for the inventory data loader
     */
    private static final int INVENTORY_LOADER = 0;

    //Adapter for ListView
    InventoryCursorAdapter mInventoryCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Action perform by floating action button
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this, InventoryAddActivity.class);
                startActivity(intent);
            }
        });
        //get the Reference of the list view
        ListView inventoryItemListView = (ListView) findViewById(R.id.list);

        //find and set the empty view on the ListView, so that it only shows when the list has 0
        // inventory item
        View emptyView = findViewById(R.id.empty_view);
        inventoryItemListView.setEmptyView(emptyView);

        //setup an Adapter to create a list item for each row of the iventory item in the cursor
        mInventoryCursorAdapter = new InventoryCursorAdapter(this, null);
        inventoryItemListView.setAdapter(mInventoryCursorAdapter);

        inventoryItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create new intent
                Intent intent = new Intent(InventoryActivity.this,InventoryAddActivity.class);

                //get the Uri of the particular inventory item
                Uri currentInventoryItemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI,id);

                //set the Uri on the data field of the intent
                intent.setData(currentInventoryItemUri);

                //Launch the activity
                startActivity(intent);
            }
        });

        //kick off the loader
        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu on actionbar
        getMenuInflater().inflate(R.menu.menu_inventory,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete_all_entries :
                deleteAllInventoryItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllInventoryItem() {
        if(mInventoryCursorAdapter.isEmpty())
            Toast.makeText(this, R.string.no_inventory_item_in_list,Toast.LENGTH_SHORT).show();
        else {
            int rowDeleted = getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
            Toast.makeText(this, R.string.all_inventory_item,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //define projection for the inventory item
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_INVENTORY_ITEM_NAME,
                InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE,
                InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,
                InventoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mInventoryCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mInventoryCursorAdapter.swapCursor(null);
    }
}
