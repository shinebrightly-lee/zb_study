package common;

import java.sql.*;

public class JDBC{
    public Connection conn;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;
    public JDBC(){
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

    public void closeConn(){
        try{
            if(conn != null) conn.close();
            if(pstmt != null) pstmt.close();
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}