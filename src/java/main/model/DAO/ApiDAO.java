package main.model.DAO;

import common.*;
import main.model.*;

import java.sql.*;
import java.util.*;

public class ApiDAO {
    private JDBC jdbc;

    public ApiDAO() {
        this.jdbc = new JDBC();
    }

    public void insertApi(ArrayList<Api> apiArr) throws SQLException {

        try {
            String SQL = "INSERT INTO  api (MGR_NO , WRDOFC,  MAIN_NM, ADRES1, ADRES2," +
                    "INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            int idx = 1;
            for ( Api api : apiArr ) {

                jdbc.pstmt.setString(1, api.getMGR_NO());
                jdbc.pstmt.setString(2, api.getWRDOFC());
                jdbc.pstmt.setString(3, api.getMAIN_NM());
                jdbc.pstmt.setString(4, api.getADRES1());
                jdbc.pstmt.setString(5, api.getADRES2());
                jdbc.pstmt.setString(6, api.getINSTL_FLOOR());
                jdbc.pstmt.setString(7, api.getINSTL_TY());
                jdbc.pstmt.setString(8, api.getINSTL_MBY());
                jdbc.pstmt.setString(9, api.getSVC_SE());
                jdbc.pstmt.setString(10, api.getCMCWR());
                jdbc.pstmt.setString(11, api.getCNSTC_YEAR());
                jdbc.pstmt.setString(12, api.getINOUT_DOOR());
                jdbc.pstmt.setString(13, api.getREMARS3());
                jdbc.pstmt.setDouble(14, api.getLAT());
                jdbc.pstmt.setDouble(15, api.getLNT());
                jdbc.pstmt.setString(16, api.getWORK_DTTM());

                jdbc.pstmt.addBatch();
                jdbc.pstmt.clearParameters();
                    if (( idx % 10000 ) == 0){
                        jdbc.pstmt.executeBatch() ;
                        jdbc.pstmt.clearBatch();
                        jdbc.conn.commit() ;
                    }
                    idx++;
            }
            jdbc.pstmt.executeBatch() ;
            jdbc.conn.commit() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            jdbc.closeConn();
        }
    }

    public void truncateTable() throws SQLException {
        try {
            String SQL = "TRUNCATE TABLE api";
            jdbc.stmt = jdbc.conn.createStatement();
            jdbc.stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } //finally { // insert 할때 사용하기에 우선 주석
//            jdbc.closeConn();
//        }
    }

    public ArrayList<Api> selectOrderDistance(double lat, double lnt)throws  SQLException{
            ArrayList<Api> apiList = new ArrayList<>();
        try{
            String SQL = " SELECT *, (6371 * acos(cos(radians(?)) * cos(radians(LAT)) * cos(radians(LNT) - radians(?)) + sin(radians(?)) * sin(radians(LAT)))) AS KM" +
                                    " FROM api" +
                                    " ORDER BY KM" +
                                    " LIMIT 20 ";
            jdbc.pstmt = jdbc. conn.prepareStatement(SQL);
            jdbc.pstmt.setDouble(1,lnt);
            jdbc.pstmt.setDouble(2,lat);
            jdbc.pstmt.setDouble(3,lnt);
            jdbc.rs = jdbc.pstmt.executeQuery();

            while (jdbc.rs.next()){
                Api api = new Api();
                api.setKM(Math.floor(jdbc.rs.getDouble("KM")*10000)/10000.0);
                api.setMGR_NO(jdbc.rs.getString("MGR_NO"));
                api.setWRDOFC(jdbc.rs.getString("WRDOFC"));
                api.setMAIN_NM(jdbc.rs.getString("MAIN_NM"));
                api.setADRES1(jdbc.rs.getString("ADRES1"));
                api.setADRES2(jdbc.rs.getString("ADRES2"));
                api.setINSTL_FLOOR(jdbc.rs.getString("INSTL_FLOOR"));
                api.setINSTL_TY(jdbc.rs.getString("INSTL_TY"));
                api.setINSTL_MBY(jdbc.rs.getString("INSTL_MBY"));
                api.setSVC_SE(jdbc.rs.getString("SVC_SE"));
                api.setCMCWR(jdbc.rs.getString("CMCWR"));
                api.setCNSTC_YEAR(jdbc.rs.getString("CNSTC_YEAR"));
                api.setINOUT_DOOR(jdbc.rs.getString("INOUT_DOOR"));
                api.setREMARS3(jdbc.rs.getString("REMARS3"));
                api.setLAT(jdbc.rs.getDouble("LAT"));
                api.setLNT(jdbc.rs.getDouble("LNT"));
                api.setWORK_DTTM(jdbc.rs.getString("WORK_DTTM"));
                apiList.add(api);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
            return apiList;
    }

}
