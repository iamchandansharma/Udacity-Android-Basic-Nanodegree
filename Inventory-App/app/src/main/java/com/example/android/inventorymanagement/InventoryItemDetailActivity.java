package com.example.android.inventorymanagement;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventorymanagement.data.InventoryContract;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class InventoryItemDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    //current inventory item Uri
    private Uri mCurrentInventoryItemUri;

    //exiting loader
    private static final int EXISTING_INVENTORY_LOADER = 0;

    //get the reference of the all the item views
    private TextView mItemNameTextView;
    private ImageView mItemImageView;
    private TextView mItemPriceTextView;
    private TextView mItemQuantityTextView;
    private Button mItemQuantityIncrementButton;
    private Button mItemQuantityDecrementButton;
    private Button mItemOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item_detail);

        final Intent intent = getIntent();
        String itemIdString = intent.getStringExtra(InventoryCursorAdapter.ITEM_INDEX);
        long itemId = Long.parseLong(itemIdString);

        //Initialize all views
        mItemNameTextView = (TextView) findViewById(R.id.inventory_item_name_view);
        mItemImageView = (ImageView) findViewById(R.id.inventory_item_image_view);
        mItemPriceTextView = (TextView)findViewById(R.id.inventory_item_price_text_view);
        mItemQuantityTextView = (TextView) findViewById(R.id.inventory_item_quantity_text_view);
        mItemQuantityIncrementButton = (Button) findViewById(R.id.inventory_item_increment);
        mItemQuantityDecrementButton = (Button) findViewById(R.id.inventory_item_decrement);
        mItemOrderButton = (Button) findViewById(R.id.inventory_item_order_button);

        //get the Uri of the particular inventory item
        mCurrentInventoryItemUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI,itemId);
        mItemQuantityIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemQuantity = Integer.parseInt(mItemQuantityTextView.getText().toString());
                itemQuantity += 1;
                mItemQuantityTextView.setText(itemQuantity +"");
                ContentValues values = new ContentValues();
                values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY,itemQuantity);
                getContentResolver().update(mCurrentInventoryItemUri,values,null,null);
            }
        });
        mItemQuantityDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemQuantity = Integer.parseInt(mItemQuantityTextView.getText().toString());
                itemQuantity -= 1;
                if(itemQuantity == 0)
                    Toast.makeText(InventoryItemDetailActivity.this,"Minimum 1 Item Quantity required to place order"
                            ,Toast.LENGTH_SHORT).show();
                else {
                    mItemQuantityTextView.setText(itemQuantity + "");
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY, itemQuantity);
                    getContentResolver().update(mCurrentInventoryItemUri,values,null,null);
                }
            }
        });
        mItemOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Urgent Required Quantity for inventory item");
                intent.putExtra(Intent.EXTRA_TEXT, "Required quantity for : \n" +
                        ((TextView) findViewById(R.id.inventory_item_name_view)).getText().toString().trim() +
                        "\nQuantity : " + ((TextView) findViewById(R.id.inventory_item_quantity_text_view))
                        .getText().toString().trim() +
                        "\n\n\nFrom : CS Inventory PVT LTD");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        //kick off the loader
        getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //editor shows all inventory information
        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_NAME,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE};

        //this loader will execute contentProviders query method on background thread
        return new CursorLoader(this,
                mCurrentInventoryItemUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        //otherwise proceed moving the cursor from the first row to rad the data
        if (cursor.moveToFirst()) {
            //find the all column attribute
            int inventoryItemNameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.
                    COLUMN_INVENTORY_ITEM_NAME);
            int inventoryItemPriceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.
                    COLUMN_INVENTORY_ITEM_PRICE);
            final int inventoryItemQuantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.
                    COLUMN_INVENTORY_ITEM_QUANTITY);
            int inventoryItemImageColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.
                    COLUMN_INVENTORY_ITEM_IMAGE);

            //extract the data from the cursor from given column index
            String itemName = cursor.getString(inventoryItemNameColumnIndex);
            int itemPrice = cursor.getInt(inventoryItemPriceColumnIndex);
            int itemQuantity = cursor.getInt(inventoryItemQuantityColumnIndex);
            String itemImageString = cursor.getString(inventoryItemImageColumnIndex);

            // Update the views on the screen with the values from the database
            mItemNameTextView.setText(itemName);
            mItemPriceTextView.setText(Integer.toString(itemPrice));
            mItemQuantityTextView.setText(Integer.toString(itemQuantity));
            if (itemImageString == null)
                Toast.makeText(this, "Image Loading Fail!", Toast.LENGTH_SHORT).show();
            else {
                mItemImageView.setImageURI(Uri.parse(itemImageString));
            }
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mItemNameTextView.setText("");
        mItemPriceTextView.setText("");
        mItemQuantityTextView.setText("");
        mItemImageView.setImageURI(null);
    }
}
