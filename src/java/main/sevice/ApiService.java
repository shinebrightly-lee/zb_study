package main.sevice;

import main.model.dao.*;
import main.model.dto.*;
import org.json.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class ApiService {
        public int apiInfo() throws IOException{

            int start = 1;
            int end = 1000;
            URL url;
            HttpURLConnection conn;
            JSONObject JsonOBJ;
            JSONObject obj;
            JSONArray jArray;
            JSONArray resultArray = new JSONArray();
            StringBuilder sb;
            int list_total_count = 0;

            while(end != 0){
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/4f6b4c73476d6831313130774b57657a/json/TbPublicWifiInfo");
                urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start), "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end), "UTF-8"));

                url = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader br;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                conn.disconnect();

                JsonOBJ = new JSONObject(sb.toString());
                obj = JsonOBJ.getJSONObject("TbPublicWifiInfo");
                list_total_count = obj.getInt("list_total_count");
                jArray = obj.getJSONArray("row");
                start  = end+1;
                end = jArray.length() >= 1000 ?  start+999 : 0;

                for (int i = 0; i < jArray.length(); i++) {
                    resultArray.put(jArray.getJSONObject(i));
                }
            }

            ArrayList<Api> apiArr = new ArrayList<>();
                try{
                        ApiDAO apiDao = new ApiDAO();
                        apiDao.truncateTable();
                        for (int i = 0; i < resultArray.length(); i++) {
                                Api api = new Api();
                                JSONObject objArr = resultArray.getJSONObject(i);
                                api.setMGR_NO(objArr.getString("X_SWIFI_MGR_NO"));
                                api.setWRDOFC(objArr.getString("X_SWIFI_WRDOFC"));
                                api.setMAIN_NM(objArr.getString("X_SWIFI_MAIN_NM"));
                                api.setADRES1(objArr.getString("X_SWIFI_ADRES1"));
                                api.setADRES2(objArr.getString("X_SWIFI_ADRES2"));
                                api.setINSTL_FLOOR(objArr.getString("X_SWIFI_INSTL_FLOOR"));
                                api.setINSTL_TY(objArr.getString("X_SWIFI_INSTL_TY"));
                                api.setINSTL_MBY(objArr.getString("X_SWIFI_INSTL_MBY"));
                                api.setSVC_SE(objArr.getString("X_SWIFI_SVC_SE"));
                                api.setCMCWR(objArr.getString("X_SWIFI_CMCWR"));
                                api.setCNSTC_YEAR(objArr.getString("X_SWIFI_CNSTC_YEAR"));
                                api.setINOUT_DOOR(objArr.getString("X_SWIFI_INOUT_DOOR"));
                                api.setREMARS3(objArr.getString("X_SWIFI_REMARS3"));
                                api.setLAT(Double.parseDouble(objArr.getString("LAT")));
                                api.setLNT(Double.parseDouble(objArr.getString("LNT")));
                                api.setWORK_DTTM(objArr.getString("WORK_DTTM"));
                                apiArr.add(api);
                        }
                        apiDao.insertApi(apiArr);
                }catch (Exception e){
                        e.printStackTrace();
                }
            return list_total_count;
        }

        public ArrayList<Api> closeRange20(double lat, double lnt) throws SQLException {
            ApiDAO apiDAO = new ApiDAO();
            return apiDAO.selectOrderDistance(lat,lnt);
        }

        public Api selectDetail(String mgrNo) throws SQLException {
            ApiDAO apiDAO = new ApiDAO();
            return apiDAO.selectDetail(mgrNo);
        }


}
