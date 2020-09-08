package com.example.onroad;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Background extends AsyncTask<String,String,String> {

    Context c;
    public Background(Context c){
        this.c=c;
    }
    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];

        String addUrl = "https://onroaduniversal.000webhostapp.com/onroad/conn.php";
        if (type.equals("add")) {
            String cityd = strings[1];
            String citya = strings[2];
            String timed = strings[3];
            String timea = strings[4];
            String price = strings[5];
            String date = strings[6];
            String minpas = strings[7];
            String maxpas = strings[8];
            String agency = strings[9];
            String linename = strings[10];
            try {
                URL url = new URL(addUrl);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String insert_data = URLEncoder.encode("cityd", "UTF-8") + "=" + URLEncoder.encode(cityd, "UTF-8") + "&" +
                            URLEncoder.encode("citya", "UTF-8") + "=" + URLEncoder.encode(citya, "UTF-8") + "&" +
                            URLEncoder.encode("timed", "UTF-8") + "=" + URLEncoder.encode(timed, "UTF-8") + "&" +
                            URLEncoder.encode("timea", "UTF-8") + "=" + URLEncoder.encode(timea, "UTF-8") + "&" +
                            URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                            URLEncoder.encode("minpas", "UTF-8") + "=" + URLEncoder.encode(minpas, "UTF-8") + "&" +
                            URLEncoder.encode("maxpas", "UTF-8") + "=" + URLEncoder.encode(maxpas, "UTF-8") + "&" +
                            URLEncoder.encode("agency", "UTF-8") + "=" + URLEncoder.encode(agency, "UTF-8") + "&" +
                            URLEncoder.encode("linename", "UTF-8") + "=" + URLEncoder.encode(linename, "UTF-8");
                    bufferedWriter.write(insert_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }
}
