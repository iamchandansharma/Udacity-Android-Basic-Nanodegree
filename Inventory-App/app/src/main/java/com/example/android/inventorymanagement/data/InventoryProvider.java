package com.example.android.inventorymanagement.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.inventorymanagement.data.InventoryContract.InventoryEntry;

/**
 * Created by CS on 1/10/2017.
 */
public class InventoryProvider extends ContentProvider {

    public static final String TAG = InventoryProvider.class.getSimpleName();

    private static final int INVENTORY = 100;
    private static final int INVENTORY_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //Create Content Uri "content://com.example.android.inventorymanagement/inventory"
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, INVENTORY);
        //Create Content Uri "content://com.example.android.inventorymanagement/inventory/2"
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", INVENTORY_ID);
    }

    private InventoryDbHelper mInventoryDbHelper;

    @Override
    public boolean onCreate() {
        mInventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //Get Readable database
        SQLiteDatabase database = mInventoryDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }
        //set notification Uri to the cursor
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        //return cursor
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertInventoryItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not support for " + uri);
        }
    }

    private Uri insertInventoryItem(Uri uri, ContentValues values) {
        //get the name of the inventory item
        String itemName = values.getAsString(InventoryEntry.COLUMN_INVENTORY_ITEM_NAME);
        if (itemName == null) {
            throw new IllegalArgumentException("Inventory item requires Name");
        }
        //get the price of the inventory item
        Integer itemPrice = values.getAsInteger(InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE);
        if (itemPrice == null || itemPrice < 0) {
            throw new IllegalArgumentException("Inventory item requires Price");
        }
        //get the quantity of the inventory item
        Integer itemQuantity = values.getAsInteger(InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY);
        if (itemQuantity == null || itemQuantity < 0) {
            throw new IllegalArgumentException("Inventory item requires Quantity");
        }
        //get the quantity of the inventory item
        String itemImage = values.getAsString(InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE);
        if (itemImage == null ) {
            throw new IllegalArgumentException("Inventory item requires Image");
        }
        //Get Writable database for insertion
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();

        //insert the new inventory item with given values
        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);
        //if ID = -1 then insertion fails Log on the message that insertion is fail
        if (id == -1) {
            Log.e(TAG, "Failed to insert row for" + uri);
            return null;
        }

        //notify all listeners to change the uri
        getContext().getContentResolver().notifyChange(uri, null);

        //return the new uri with the ID append at the end
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return updateInventoryItem(uri, values, selection, selectionArgs);
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateInventoryItem(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateInventoryItem(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        //check the item name value is not null and valid
        if (values.containsKey(InventoryEntry.COLUMN_INVENTORY_ITEM_NAME)) {
            String itemName = values.getAsString(InventoryEntry.COLUMN_INVENTORY_ITEM_NAME);
            if (itemName == null) {
                throw new IllegalArgumentException("Inventory item requires Name");
            }
        }
        //check the item price is not null and valid
        if (values.containsKey(InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE)) {
            Integer itemPrice = values.getAsInteger(InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE);
            if (itemPrice == null || itemPrice < 0) {
                throw new IllegalArgumentException("Inventory item requires Price");
            }
        }
        //check the item quantity is not null and valid
        if (values.containsKey(InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY)) {
            Integer itemQuantity = values.getAsInteger(InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY);
            if (itemQuantity == null || itemQuantity < 0) {
                throw new IllegalArgumentException("Inventory item requires Quantity");
            }
        }
        //check the item quantity is not null and valid
        if (values.containsKey(InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE)) {
            String itemImage = values.getAsString(InventoryEntry.COLUMN_INVENTORY_ITEM_IMAGE);
            if (itemImage == null) {
                throw new IllegalArgumentException("Inventory item requires Image");
            }
        }
        //if the there is no value to update, then don;t need to update database
        if (values.size() == 0) {
            return 0;
        }
        //get the writable database
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        //perform update on database and get the number of row reflected
        int rowUpdated = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);

        //if one or more row updated then notify thr all listener that has data at the
        //given uri has changed
        if (rowUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        //return the reflected row
        return rowUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //get writable database
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();

        //track the number of row deleted
        int rowDeleted;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                //delete the all row from the inventory database
                rowDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                //delete the single row from the inventory database
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        //if 1 or more rows were deleted, then notify all listeners that the data at the
        //given URI changed
        if (rowDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        //return the number row deleted
        return rowDeleted;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + match);
        }
    }
}
