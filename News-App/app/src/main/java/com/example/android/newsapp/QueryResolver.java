package com.example.android.newsapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
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
import java.util.List;

public class QueryResolver  {

    /* fetching data from the string url parse it and return newsData object
     */
    private QueryResolver(){
    }
    public static List<NewsData> fetchNewsData (String requestUrl){
        //Create Url object
        URL url = makeUrl(requestUrl);

        //perform HTTP request to the URL and and JSON response back
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        //Extract the relevant information from the JSON response
        List <NewsData> newsData = extractFromJson(jsonResponse);

        return newsData;
    }

    /* generate the url form the given string
     *Here method is declare static because without creating the object we call this method
     */
    private static URL makeUrl (String input){
        //create Url object
        URL url = null;
        try {
            url = new URL(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /*Make http request to the server and return the json response
     */
    private static String makeHttpRequest (URL url){
        //create json response and also httpurlrequest and inptstream
        String jsonResponse = "";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        //if Url is null then simply return empty jsonResponse
        if (url == null){
            return jsonResponse;
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //set the http request method
            httpURLConnection.setRequestMethod("GET");
            //establish the connection
            httpURLConnection.connect();


            //if the request was successfully then it return 200 code
            //then read the stream and parse the response
            if (httpURLConnection.getResponseCode() == 200 ){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream (inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //check if all the data is retrive so close the http connection
            //and release the resources
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            //close the inputStream and release the inputstream resource
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return  jsonResponse;
    }

    //get the input stream and convert to  the string json
    private static String readFromStream (InputStream input) throws IOException{
        StringBuilder data = new StringBuilder();
        if (input != null) {
            InputStreamReader inputStream = new InputStreamReader (input, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStream);
            String line = reader.readLine();
            while (line != null){
                data.append(line);
                line = reader.readLine();
            }
        }
        return data.toString();
    }

    //extract data from json and return list of the newsData object
    private static List <NewsData> extractFromJson (String newsDataJson){
        //check if the json string empty or null
        if (TextUtils.isEmpty(newsDataJson)){
            return null;
        }

        //Create empty arrayList in which store the all the newsData object

        List <NewsData> newsData = new ArrayList<>();

        //Parsing the json and get the required data form the string url

        try {
            //create the root json object of the give string
            JSONObject baseObject = new JSONObject(newsDataJson);

            //create the json array and fetch all array object from given string
            JSONArray resultArray = baseObject.getJSONArray("results");

            //fetch all the json array object one by one
            for ( int i = 0; i < resultArray.length() ; i++){
                JSONObject currentNewsData = resultArray.getJSONObject(i);

                //extract all the information that required
                String sectionName = currentNewsData.optString("section");
                String itemType = currentNewsData.optString("subsection");
                String webTitle = currentNewsData.optString("title");
                String webAbstract = currentNewsData.optString("abstract");
                Bitmap bmpImage;
                try {
                    JSONArray webImage = currentNewsData.getJSONArray("multimedia");
                    String articleImage = (webImage.getJSONObject(1)).optString("url");
                    URL url = new URL(articleImage);
                    bmpImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                catch (JSONException e){
                    bmpImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                }
                String webUrl = currentNewsData.optString("url");
                String authorName = currentNewsData.optString("byline");
                String date = currentNewsData.optString("published_date");
                String webPublicationDate = "";
                for(int j = 0 ; date.charAt(j)!='T'; j++){
                    webPublicationDate += Character.toString(date.charAt(j));
                }
                newsData.add( new NewsData(sectionName, itemType, webTitle, webAbstract,bmpImage ,authorName, webPublicationDate, webUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsData;
    }
}
