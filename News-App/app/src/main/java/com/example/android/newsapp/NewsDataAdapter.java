package com.example.android.newsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class NewsDataAdapter extends ArrayAdapter <NewsData> {

    public NewsDataAdapter(Context context, List<NewsData> newsFeed) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for four TextViews the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, newsFeed);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Check if existing view is being reused, otherwise inflate the view
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem  = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }
        //get the object located in the list
        NewsData currentItem = getItem(position);

        //find the textView id for section name
        TextView sectionName = (TextView) listViewItem.findViewById(R.id.section_name_text_view);
        sectionName.setText(currentItem.getSectionName());

        //find the textView id for the Item Type
        TextView itemType = (TextView) listViewItem.findViewById(R.id.item_type_text_view);
        itemType.setText(currentItem.getItemType());

        //find the textView id for the web title
        TextView webTitle = (TextView) listViewItem.findViewById(R.id.web_title_text_view);
        webTitle.setText(currentItem.getWebTitle());

        //find the textView id for the web abstract
        TextView webAbstractData = (TextView) listViewItem.findViewById(R.id.web_abstract_text_view);
        webAbstractData.setText(currentItem.getWebAbstract());

        //find the ImageView id for the Article image
        ImageView articleImage = (ImageView)listViewItem.findViewById(R.id.article_image);
        articleImage.setImageBitmap(currentItem.getWebImage());

        //find the textView id for the writer text view
        TextView authorName = (TextView)listViewItem.findViewById(R.id.author_text_view);
        authorName.setText(currentItem.getAuthorName());

        //find the textView of the time and date
        TextView webPublicationDate = (TextView) listViewItem.findViewById(R.id.web_publication_date_text_view);
        webPublicationDate.setText(currentItem.getWebPublicationDate());

        return listViewItem;
    }
}
