package com.example.sinemaproject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sinemaproject.model.AllData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SeriesTask extends AsyncTask<String, Void, List<AllData>> {
    static List<AllData> AllDataList;

    public SeriesTask() {
    }

    @Override
    protected List<AllData> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        StringBuffer stringBuffer;
        try {
            //Hacemos la peticion con los parametros que nbos pasan
            URL url = new URL("https://api.tvmaze.com/shows");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            stringBuffer = new StringBuffer();
            //Si no nos devuelve nada salimos
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + "\n"); // salida por consola con salto de linea  mientras haya más registros
            }
            if (stringBuffer.length() == 0) {
                return null;
            }
            forecastJsonStr = stringBuffer.toString();

            AllDataList = new ArrayList<AllData>(){};

            try {
                //Transformamos la repuesta a JSON array
                JSONArray jsonArray = new JSONArray(forecastJsonStr);
                for (int i = 0; i <jsonArray.length() ; i++) {
                    //Convertimos la respuesta primero a objetos JSON y despues formamos el objeto Universidad para agragarlo a la lista
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
                        Integer id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String language = jsonObject.getString("language");
                        String status = jsonObject.getString("status");
                        String premiered = jsonObject.getString("premiered");
                        String ended = jsonObject.getString("ended");
                        String summary = jsonObject.getString("summary");
                        JSONArray genres = jsonObject.getJSONArray("genres");
                        ArrayList<String> exampleList = new ArrayList<String>();
                        for (int j = 0; j < genres.length(); j++) {
                            exampleList.add((String) genres.get(j));
                        }
                        int size = exampleList.size();
                        String[] GenresstringArray = exampleList.toArray(new String[size]);

                        Double rating = jsonObject.getJSONObject("rating").isNull("rating") ? 0.0 : jsonObject.getJSONObject("rating").getDouble("average");
                        String imageOriginal = jsonObject.getJSONObject("image").getString("original");
                        String imageMedium= jsonObject.getJSONObject("image").getString("medium");
                        AllData allData = new AllData(id,name,language,status,premiered,ended,summary,GenresstringArray,rating,imageOriginal,imageMedium);
                        AllDataList.add(allData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            return null;
        } finally{
            //Cerramos conexiones
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        for (AllData item:AllDataList) {
            Log.i("Dat", String.valueOf(item));
        }
        return AllDataList;
    }

}
