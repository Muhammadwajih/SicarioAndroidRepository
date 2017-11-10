package com.smartical.vr.cinema.videoplayer.content;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class YouTubeContent
{
    public static List<YouTubeVideo> ITEMS = new ArrayList<>();
    public static Map<String, YouTubeVideo> ITEM_MAP = new HashMap<>();
    public static int Channelid;
    static int item_count;
    static String id;

    public static boolean getdata(final String URL) {
        AsyncTask<String,String,String> getTask = new AsyncTask<String,String,String>(){
            @Override
            protected String doInBackground(String... params)
            {
                String response = "";
                java.net.URL url;
                try{
                    url = new URL(params[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection)
                            url.openConnection();
                    BufferedReader
                            reader = new
                            BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuffer buffer = new StringBuffer();
                    int ch = -1;

                    while ( (ch= reader.read()) != -1){
                        buffer.append((char) ch);
                    }
                    response = buffer.toString();
                }
                catch (Exception e)
                {

                }
                return response;
            }
            protected void onPostExecute(String result) {
               try
                {
                    if (Channelid == 0)
                    {
                        JSONObject jObj = new JSONObject(result);
                        JSONObject pageinfo = jObj.getJSONObject("pageInfo");
                        String totalresult = pageinfo.getString("totalResults"); // Number of playlist
                        JSONArray rows = jObj.getJSONArray("items");

                        for (int i =0; i< Integer.parseInt(totalresult); i++)
                        {
                            JSONObject items = rows.getJSONObject(i);
                            id = items.getString("id");
                            JSONObject snippet  = items.getJSONObject("snippet");
                            JSONObject localized  = snippet.getJSONObject("localized");
                            String title = localized.getString("title");
                            if (title.equals("VR Cinema Videos"))
                                break;
                        }
                        //System.out.println(rows);
                        //JSONObject items = rows.getJSONObject(1);
                        //String id = items.getString("id");
                        ////
                        getdata("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=25&playlistId="+id+"&key=AIzaSyB8RuveEIrh9hWLcl0cW4zrDZz0F6oYuTo");
                        Channelid =1;
                    }
                    else if (Channelid!=0)
                    {
                        JSONObject jObj = new JSONObject(result);
                        JSONArray rows = jObj.getJSONArray("items");
                        item_count = rows.length();
                        for (int i=0;i<item_count; i++)
                        {
                            JSONObject items = rows.getJSONObject(i);
                            JSONObject snippet = items.getJSONObject("snippet");
                            String tittle = snippet.getString("title");
                            JSONObject resourceId = snippet.getJSONObject("resourceId");
                            String Vid = resourceId.getString("videoId");
                            addItem(new YouTubeVideo(Vid, tittle));
                        }
                    }
                    // End of getting data.
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        getTask.execute(URL);
        return true;
    }
    private static void addItem(final YouTubeVideo item)
    {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class YouTubeVideo
    {
        public String id;
        public String title;

        public YouTubeVideo(String id, String content)
        {
            this.id = id;
            this.title = content;
        }
        @Override
        public String toString()
        {
            return title;
        }
    }
}