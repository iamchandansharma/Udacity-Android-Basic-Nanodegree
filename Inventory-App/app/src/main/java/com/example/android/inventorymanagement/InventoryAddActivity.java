package com.example.android.inventorymanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.inventorymanagement.data.InventoryContract.InventoryEntry;

public class InventoryAddActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_INVENTORY_LOADER = 0;
    private static final int SELECT_PICTURE = 100;

    //create reference of the all editText
    private EditText mInventoryItemName;
    private EditText mInventoryItemPrice;
    private EditText mInventoryItemQuantity;

    //create reference of the button
    private Button mAddInventoryItemButton;
    private Button mDeleteInventoryItemButton;
    private Button mUploadInventoryImageButton;

    //content uri of the current inventory item
    private Uri mCurrentInventoryItemUri;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);

        //get the intent from the previous activity
        final Intent intent = getIntent();
        mCurrentInventoryItemUri = intent.getData();

        //get the reference of all Button
        mAddInventoryItemButton = (Button) findViewById(R.id.add_item);
        mDeleteInventoryItemButton = (Button) findViewById(R.id.delete_item);
        mUploadInventoryImageButton = (Button) findViewById(R.id.upload_button);

        //if the intent not contain inventory uri so we create new inventory item
        if (mCurrentInventoryItemUri == null) {
            //set the title of the new inventory uri activity
            setTitle("Add Inventory");

            //Set the text to Update Button
            mAddInventoryItemButton.setText(R.string.add_item);
            mDeleteInventoryItemButton.setVisibility(View.INVISIBLE);
            selectedImageUri = null;
        } else {
            setTitle("Edit Inventory");
            //Initialize a loader to load the data

            //Set the text to Update Button
            mAddInventoryItemButton.setText(R.string.update_item);
            getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
        }
        //get the reference of all editText
        mInventoryItemName = (EditText) findViewById(R.id.inventory_title_edit_text);
        mInventoryItemPrice = (EditText) findViewById(R.id.inventory_item_price_edit_text);
        mInventoryItemQuantity = (EditText) findViewById(R.id.inventory_item_quantity_edit_text);

        //set up listeners on button
        mAddInventoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInventoryItem();
            }
        });
        mDeleteInventoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
        mUploadInventoryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                ((ImageView)findViewById(R.id.inventory_item_image_view)).setImageURI(selectedImageUri);
            }
        }
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this inventory item?");
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteInventoryItem();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteInventoryItem() {
        //Only perform deletion if the inventory item existing
        if (mCurrentInventoryItemUri != null) {
            //call the contentResolver to delete the inventory item
            int rowsDeleted = getContentResolver().delete(mCurrentInventoryItemUri, null, null);
            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.inventory_delete_item_error),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.inventory_delete_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveInventoryItem() {
        //read the data from the input fields;
        //using trim to eliminate the leading or trailing space
        String inventoryItemNameString = mInventoryItemName.getText().toString().trim();
        String inventoryItemPriceString = mInventoryItemPrice.getText().toString().trim();
        String inventoryItemQuantityString = mInventoryItemQuantity.getText().toString().trim();
        if(inventoryItemNameString.isEmpty()){
            mInventoryItemName.setError("Item Name Required");
            return;
        }
        if (inventoryItemPriceString.isEmpty()) {
            mInventoryItemPrice.setError("Item Price Required");
            return;
        }
        if (inventoryItemQuantityString.isEmpty()) {
            mInventoryItemQuantity.setError("Item Quantity Required");
            return;
        }
        //check for Integer input only
        try {
            int itemPrice = Integer.parseInt(inventoryItemPriceString);
        } catch (NumberFormatException e) {
            mInventoryItemPrice.setError("Please Enter Valid Number");
            return;
        }
        try {
            int itemQuantity = Integer.parseInt(inventoryItemQuantityString);
        } catch (NumberFormatException e) {
            mInventoryItemQuantity.setError("Please Enter Valid Number");
            return;
        }
        if(selectedImageUri == null){
            Toast.makeText(this,"Please choose Image",Toast.LENGTH_SHORT).show();
            return;
        }

        //check if this is new inventory item
        //and lso check that all the filed in the editor are empty
        //Create contentValues object
        //and save the inventory item information in the contentValue
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryEntry.COLUMN_INVENTORY_ITEM_NAME, inventoryItemNameString);
        contentValues.put(InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE, inventoryItemPriceString);
        contentValues.put(InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY, inventoryItemQuantityString);
        contentValues.put(InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE,selectedImageUri.toString().trim());

        //determine the inventory item is exiting item by checking if mCurrentInventoryItemUri is null or not
        if (mCurrentInventoryItemUri == null) {
            //This is new inventory item
            //returning the content URI for new inventory item
            Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, contentValues);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.inventory_save_item_error),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.inventory_save_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //this inventory item is existing inventory item
            //we simply update the inventory data
            int rowsAffected = getContentResolver().update(mCurrentInventoryItemUri, contentValues, null, null);
            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.inventory_update_item_error),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.inventory_update_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //editor shows all inventory information
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_INVENTORY_ITEM_NAME,
                InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE,
                InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY,
                InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE};

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
            int inventoryItemNameColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_INVENTORY_ITEM_NAME);
            int inventoryItemPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_INVENTORY_ITEM_PRICE);
            int inventoryItemQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_INVENTORY_ITEM_QUANTITY);
            int inventoryItemImageColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_INVENTORY_ITEM_IMAGE);

            //extract the data from the cursor from given column index
            String itemName = cursor.getString(inventoryItemNameColumnIndex);
            int itemPrice = cursor.getInt(inventoryItemPriceColumnIndex);
            int itemQuantity = cursor.getInt(inventoryItemQuantityColumnIndex);
            String itemImageString = cursor.getString(inventoryItemImageColumnIndex);

            // Update the views on the screen with the values from the database
            mInventoryItemName.setText(itemName);
            mInventoryItemPrice.setText(Integer.toString(itemPrice));
            mInventoryItemQuantity.setText(Integer.toString(itemQuantity));
            if(itemImageString == null)
                Toast.makeText(this,"Please choose Image",Toast.LENGTH_SHORT).show();
            else {
                selectedImageUri = Uri.parse(itemImageString);
                ((ImageView)findViewById(R.id.inventory_item_image_view)).setImageURI(selectedImageUri);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mInventoryItemName.setText("");
        mInventoryItemPrice.setText("");
        mInventoryItemQuantity.setText("");
    }
}
