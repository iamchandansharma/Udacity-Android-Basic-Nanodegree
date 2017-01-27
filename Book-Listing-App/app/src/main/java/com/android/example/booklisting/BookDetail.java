package com.android.example.booklisting;


import android.graphics.Bitmap;

public class BookDetail {

    //Used for book name
    private String bookName;

    //Used for author name
    private String authorName;

    //used for Image Id
    private Bitmap imageResourceUrl;

    //used for Url link
    private String imageId;

    public BookDetail(String book, String author, Bitmap imageUrl, String image) {
        bookName = book;
        authorName = author;
        imageResourceUrl = imageUrl;
        imageId = image;
    }

    //for getting the book name
    public String getBookName() {
        return bookName;
    }

    //for getting the author name
    public String getAuthorName() {
        return authorName;
    }

    //for getting image resource id
    public Bitmap getImageResourceUrl() {
        return imageResourceUrl;
    }

    //for getting image URL
    public String getImageId(){
        return imageId;
    }
}
