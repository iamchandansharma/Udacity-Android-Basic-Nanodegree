package com.example.android.newsapp;

import android.graphics.Bitmap;

public class NewsData {

    //Variable used for store the type of the content
    private String sectionName;

    //variable used for store the section name of the content
    private String itemType;

    //variable used for store the title of the content
    private String webTitle;

    //variable used of the abstract of the content
    private String webAbstract;

   //variable used for the image of the content
    private Bitmap webImage;

    //variable for the store the author name
    private String authorName;

    //variable used for store the publication date of the content
    private String webPublicationDate;

    //variable used for store the web url of the content
    private String webUrl;

    //Constructor used for initialize the value

    public NewsData (String name,String type, String title, String abstractData, Bitmap image,
                     String aName, String publicationDate, String url ){
        sectionName = name;
        itemType = type;
        webTitle = title;
        webAbstract = abstractData;
        webImage = image;
        authorName = aName;
        webPublicationDate = publicationDate;
        webUrl = url;
    }

    //Getter method to get the all data


    public String getSectionName() {
        return sectionName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebAbstract() {
        return webAbstract;
    }

    public Bitmap getWebImage() {
        return webImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
