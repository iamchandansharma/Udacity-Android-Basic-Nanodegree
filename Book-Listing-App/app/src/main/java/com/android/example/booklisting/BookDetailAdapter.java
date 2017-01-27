package com.android.example.booklisting;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class BookDetailAdapter extends ArrayAdapter <BookDetail> {

    public BookDetailAdapter(Context context, ArrayList <BookDetail> bookDetails ) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0

        super(context, 0 , bookDetails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list, parent, false);
        }

        BookDetail currentItem = getItem(position);

        //find the text view of the book name id
        TextView bookNameView = (TextView) listItemView.findViewById(R.id.book_name);
        bookNameView.setText(currentItem.getBookName());

        //find the text view of the author name
        TextView authorNameView = (TextView) listItemView.findViewById(R.id.author_name);
        authorNameView.setText(currentItem.getAuthorName());

        //find the image view of the image of the book
        ImageView bookImageView = (ImageView)listItemView.findViewById(R.id.book_image);
        Bitmap bookImage = currentItem.getImageResourceUrl();

        bookImageView.setImageBitmap(bookImage);
        return listItemView;
    }
}
