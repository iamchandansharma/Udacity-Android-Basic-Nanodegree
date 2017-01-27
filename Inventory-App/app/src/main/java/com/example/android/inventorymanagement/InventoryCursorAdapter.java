package com.example.android.inventorymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventorymanagement.data.InventoryContract;
import com.example.android.inventorymanagement.data.InventoryContract.InventoryEntry;

/**
 * Created by CS on 1/10/2017.
 */
public class InventoryCursorAdapter extends CursorAdapter {

    public static final String ITEM_INDEX = "ItemIndex";

    //to get the current context of the activity
    private Context mContext;

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.inventory_list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        //get the reference of the textViews
        TextView inventoryItemNameTextView = (TextView) view.findViewById(R.id.inventory_name);
        TextView inventoryItemPriceTextView = (TextView) view.findViewById(R.id.inventory_price);
        final TextView inventoryItemQuantityTextView = (TextView) view.findViewById(R.id.inventory_quantity);
        Button inventoryItemSaleButton = (Button) view.findViewById(R.id.sale_item_button);
        Button inventoryItemTrackButton = (Button) view.findViewById(R.id.track_item_button);
        final ListView listView = (ListView)view.findViewById(R.id.list);

        inventoryItemSaleButton.setFocusable(false);
        inventoryItemTrackButton.setFocusable(false);

        //find the column of the inventory item attributes in we are interested in
        int inventoryItemNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_ITEM_NAME);
        int inventoryItemPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_ITEM_PRICE);
        int inventoryItemQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY);

        //Read the attribute from the cursor for the current inventory item
        String inventoryItemNameString = cursor.getString(inventoryItemNameColumnIndex);
        String inventoryItemPriceString = cursor.getString(inventoryItemPriceColumnIndex);
        final String inventoryItemQuantityString = cursor.getString(inventoryItemQuantityColumnIndex);
        final int[] itemQuantity = {Integer.parseInt(inventoryItemQuantityString)};

        //update the TextView with the attribute for current inventory item
        inventoryItemNameTextView.setText(inventoryItemNameString);
        inventoryItemPriceTextView.setText(inventoryItemPriceString);
        inventoryItemQuantityTextView.setText(inventoryItemQuantityString);

        inventoryItemSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemQuantity[0] == 0) {
                    Toast.makeText(mContext, "Item is out of Stock!", Toast.LENGTH_SHORT).show();
                } else {
                    itemQuantity[0] = itemQuantity[0] - 1;
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_ITEM_QUANTITY, itemQuantity[0]);
                    inventoryItemQuantityTextView.setText(itemQuantity[0] + "");
                    Uri currentItemUri = Uri.withAppendedPath(InventoryEntry.CONTENT_URI,
                            getItemId(cursor.getPosition()) +"");
                    mContext.getContentResolver().update(currentItemUri,
                            values,null,null);
                }
            }
        });

        inventoryItemTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,InventoryItemDetailActivity.class);
                long itemId = getItemId(cursor.getPosition());
                intent.putExtra(ITEM_INDEX,itemId + "");
                mContext.startActivity(intent);
            }
        });
    }
}
