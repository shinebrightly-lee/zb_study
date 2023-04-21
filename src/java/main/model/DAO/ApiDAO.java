package main.model.DAO;

import main.model.*;

import java.sql.*;

public class ApiDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ApiDAO(){
        String url = "jdbc:mysql://localhost:3306/api"; // 서버 주소
        String user = "root"; //  접속자 id
        String pw = "1233"; // 접속자 pw

        // JDBC 드라이버 로드 및 접속
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int saveApi(Api api) throws SQLException {
        String SQL = "UPDATE api SET MGR_NO = ? , WRDOFC = ?,  MAIN_NM = ?, ADRES1 = ?, ADRES2 = ?," +
                                "INSTL_FLOOR = ?, INSTL_TY = ?, INSTL_MBY = ?, SVC_SE = ?, CMCWR = ?, CNSTC_YEAR = ?," +
                                " INOUT_DOOR = ?, REMARS3 = ?, LAT = ?, LNT = ?, WORK_DTTM = ?";

        pstmt = conn.prepareStatement(SQL);
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

        pstmt.executeUpdate();

// JDBC 자원 닫기
        pstmt.close();
        conn.close();

        int list_total_count = 0;
        return list_total_count;
    }

}
