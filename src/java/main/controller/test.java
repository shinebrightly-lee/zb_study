package main.controller;

import com.mysql.cj.xdevapi.*;
import org.json.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class test {
    public static void main(String[] args)throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("4f6b4c73476d6831313130774b57657a","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader br;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        JSONObject JsonOBJ = new JSONObject(sb.toString());
        JSONObject obj = JsonOBJ.getJSONObject("TbPublicWifiInfo");
        int list_total_count = obj.getInt("list_total_count");
        System.out.println("검색된 결과 총 " + list_total_count + " 건");

        int numOfRows = 1000;
        int pageNo = 1;
        int totalPage = (list_total_count + numOfRows - 1) / numOfRows;

        JSONArray resultArray = new JSONArray();

        while(pageNo <= totalPage) {
            urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("4f6b4c73476d6831313130774b57657a","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(pageNo),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(numOfRows),"UTF-8"));
            url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            conn.disconnect();

            JsonOBJ = new JSONObject(sb.toString());
            obj = JsonOBJ.getJSONObject("TbPublicWifiInfo");
            JSONArray jArray = obj.getJSONArray("row");
            for (int i = 0; i < jArray.length(); i++) {
                resultArray.put(jArray.getJSONObject(i));
            }
            pageNo++;
        }

        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject objArr = resultArray.getJSONObject(i);
            String X_SWIFI_MGR_NO = objArr.getString("X_SWIFI_MGR_NO");
            String X_SWIFI_WRDOFC = objArr.getString("X_SWIFI_WRDOFC");
            System.out.println(X_SWIFI_MGR_NO);
            System.out.println(X_SWIFI_WRDOFC);
        }

        }
}
