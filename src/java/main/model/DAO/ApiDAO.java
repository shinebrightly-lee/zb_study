package main.model.DAO;

import main.model.*;

import java.sql.*;
import java.util.*;

public class ApiDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ApiDAO() {
        String url = "jdbc:mysql://localhost:3306/java"; // 서버 주소
        String user = "root"; //  접속자 id
        String pw = "1233"; // 접속자 pw

        // JDBC 드라이버 로드 및 접속
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertApi(ArrayList<Api> apiArr) throws SQLException {

        try {
            String SQL = "INSERT INTO  api (MGR_NO , WRDOFC,  MAIN_NM, ADRES1, ADRES2," +
                    "INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(SQL);
            int idx = 1;
            for ( Api api : apiArr ) {

                    pstmt.setString(1, api.getMGR_NO());
                    pstmt.setString(2, api.getWRDOFC());
                    pstmt.setString(3, api.getMAIN_NM());
                    pstmt.setString(4, api.getADRES1());
                    pstmt.setString(5, api.getADRES2());
                    pstmt.setString(6, api.getINSTL_FLOOR());
                    pstmt.setString(7, api.getINSTL_TY());
                    pstmt.setString(8, api.getINSTL_MBY());
                    pstmt.setString(9, api.getSVC_SE());
                    pstmt.setString(10, api.getCMCWR());
                    pstmt.setString(11, api.getCNSTC_YEAR());
                    pstmt.setString(12, api.getINOUT_DOOR());
                    pstmt.setString(13, api.getREMARS3());
                    pstmt.setDouble(14, api.getLAT());
                    pstmt.setDouble(15, api.getLNT());
                    pstmt.setString(16, api.getWORK_DTTM());

                    pstmt.addBatch();
                    pstmt.clearParameters();
                    if (( idx % 10000 ) == 0){
                        pstmt.executeBatch() ;
                        pstmt.clearBatch();
                        conn.commit() ;
                    }
                    idx++;
            }
            pstmt.executeBatch() ;
            conn.commit() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//                pstmt.close();
//                conn.close();
//        }
    }

    public void truncateTable() throws SQLException {
        try{
            String SQL = "TRUNCATE TABLE api";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
//        finally {
//            conn.close();
//        }
    }

    public void closeConn(){
        try{
            pstmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Api> selectOrderDistance(double lat, double lnt)throws  SQLException{
            ArrayList<Api> apiList = new ArrayList<>();
        try{
            String SQL = " SELECT *, (6371 * acos(cos(radians(?)) * cos(radians(LAT)) * cos(radians(LNT) - radians(?)) + sin(radians(?)) * sin(radians(LAT)))) AS KM" +
                                    " FROM api" +
                                    " ORDER BY KM" +
                                    " LIMIT 20 ";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setDouble(1,lnt);
            pstmt.setDouble(2,lat);
            pstmt.setDouble(3,lnt);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Api api = new Api();
                api.setKM(rs.getDouble("KM"));
                api.setMGR_NO(rs.getString("MGR_NO"));
                api.setWRDOFC(rs.getString("WRDOFC"));
                api.setMAIN_NM(rs.getString("MAIN_NM"));
                api.setADRES1(rs.getString("ADRES1"));
                api.setADRES2(rs.getString("ADRES2"));
                api.setINSTL_FLOOR(rs.getString("INSTL_FLOOR"));
                api.setINSTL_TY(rs.getString("INSTL_TY"));
                api.setINSTL_MBY(rs.getString("INSTL_MBY"));
                api.setSVC_SE(rs.getString("SVC_SE"));
                api.setCMCWR(rs.getString("CMCWR"));
                api.setCNSTC_YEAR(rs.getString("CNSTC_YEAR"));
                api.setINOUT_DOOR(rs.getString("INOUT_DOOR"));
                api.setREMARS3(rs.getString("REMARS3"));
                api.setLAT(rs.getDouble("LAT"));
                api.setLNT(rs.getDouble("LNT"));
                api.setWORK_DTTM(rs.getString("WORK_DTTM"));
                apiList.add(api);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
            return apiList;
    }

}
