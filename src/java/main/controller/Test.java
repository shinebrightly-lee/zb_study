package main.controller;

import org.json.*;

import java.io.*;
import java.net.*;

public class Test {
    public static void main(String[] args) throws IOException {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                    /*URL*/
                    urlBuilder.append("/" + URLEncoder.encode("4f6b4c73476d6831313130774b57657a","UTF-8") );
                    // 인증키 - 4f6b4c73476d6831313130774b57657a
                    urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
                    /*요청파일타입 (xml, xmlf, xls, json) */
                    urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
                    /*서비스명 (대소문자 구분 필수입니다.)*/
                    urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
                    /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
                    urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8"));
                /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
                // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
                // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에자세히 나와 있습니다.
                urlBuilder.append("/" + URLEncoder.encode("","UTF-8"));
                /* 서비스별 추가 요청인자들*/
                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());
                /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
                BufferedReader rd;
                // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
                if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                conn.disconnect();

        JSONObject JsonOBJ = new JSONObject(sb.toString());

        JSONObject obj = JsonOBJ.getJSONObject("TbPublicWifiInfo");
        int list_total_count = obj.getInt("list_total_count");
        System.out.println("검색된 결과 총 " + list_total_count + " 건");
        JSONArray jArray = obj.getJSONArray("row");

        for (int i = 0; i < jArray.length(); i++) {
                    JSONObject objArr = jArray.getJSONObject(i);
                    String X_SWIFI_MGR_NO = objArr.getString("X_SWIFI_MGR_NO");
                    String X_SWIFI_WRDOFC = objArr.getString("X_SWIFI_WRDOFC");
                    String X_SWIFI_MAIN_NM = objArr.getString("X_SWIFI_MAIN_NM");
                    System.out.println("관리번호(" + i + "): " + X_SWIFI_MGR_NO);
                    System.out.println("자치구(" + i + "): " + X_SWIFI_WRDOFC);
                    System.out.println("와이파이명(" + i + "): " + X_SWIFI_MAIN_NM);
                    System.out.println();
        }

    }
}
