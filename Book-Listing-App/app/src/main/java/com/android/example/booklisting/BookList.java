package com.android.example.booklisting;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookList extends AppCompatActivity {

    String userInput;
    String searchUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_item);

        Bundle bundle = getIntent().getExtras();
        userInput = bundle.getString("input");
        searchUrl += userInput;

        BookSearchAsyncTask asyncTask = new BookSearchAsyncTask();
        asyncTask.execute();
    }

    public class BookSearchAsyncTask extends AsyncTask<BookDetail, Void, Void> {

        ArrayList<BookDetail> bookDetails = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(BookDetail... bookDetails) {
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            String jsonResponse = "";

            try {
                url = new URL(searchUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                extractDataFromJason(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateUi();
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private ArrayList<BookDetail> extractDataFromJason(String jsonInput) throws JSONException {

            String bookTitle = "";
            String bookImageUrl = "";
            String bookSourceUrl = "";
            String authorName = "";
            Bitmap bookImage = null;
            JSONObject rootObject = new JSONObject(jsonInput);
            if (rootObject.has("items")) {
                JSONArray itemDetail = rootObject.getJSONArray("items");

                for (int i = 0; i < itemDetail.length(); i++) {
                    //get the ith index json object from json array
                    JSONObject volumeInfo = itemDetail.getJSONObject(i);
                    JSONObject bookDetail = volumeInfo.getJSONObject("volumeInfo");
                    bookTitle = bookDetail.optString("title");
                    //Check for book has no author
                    if (bookDetail.has("authors")) {
                        JSONArray authorDetail = bookDetail.getJSONArray("authors");
                        for (int j = 0; j < authorDetail.length(); j++) {
                            authorName = authorName + authorDetail.optString(j) + "\n";
                        }
                    } else
                        authorName = "Book has No Author";
                    //check for book has no image preview
                    if (bookDetail.has("imageLinks")) {
                        JSONObject imageDetail = bookDetail.getJSONObject("imageLinks");
                        bookImageUrl = imageDetail.optString("thumbnail");
                        bookSourceUrl = bookDetail.optString("infoLink");

                        try {
                            URL bookUrl = new URL(bookImageUrl);
                            bookImage = BitmapFactory.decodeStream(bookUrl.openConnection().getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        bookImage = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
                    //return the bookDetail object which contain booktitle, authorname,
                    //book image and book image url
                    bookDetails.add(new BookDetail(bookTitle, authorName, bookImage, bookSourceUrl));
                    authorName = "";
                }
            } else {
                Intent noDataFound = new Intent(BookList.this,ResultActivity.class);
                startActivity(noDataFound);
            }
            return bookDetails;
        }


        public void updateUi() {
            BookDetailAdapter bookDetailAdapter = new BookDetailAdapter(BookList.this, bookDetails);
            GridView gridView = (GridView) findViewById(R.id.gridview);
            gridView.setAdapter(bookDetailAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    BookDetail bookItem = bookDetails.get(i);
                    String url = bookItem.getImageId();

                    // Create the text message with a string
                    //sent intent and book deatil open in web browser
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse(url));

                    // Verify that the intent will resolve to an activity
                    if (sendIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(sendIntent);
                    }
                }
            });
        }
    }

}
