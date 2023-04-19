package common;

import java.sql.*;

class JDBC_Template{
    public static void main(String[] args) {
        Connection con = null;   // 데이터 베이스와 연결을 위한 객체
        Statement stmt = null;   // SQL 문을 데이터베이스에 보내기위한 객체
        ResultSet rs = null;     // SQL 질의에 의해 생성된 테이블을 저장하는 객체

        String url = "jdbc:mysql://localhost:3306/test"; // 서버 주소
        String user = "root"; //  접속자 id
        String pw = "1233"; // 접속자 pw

        String SQL = "SELECT * FROM test1";

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }

        // 접속
        try {
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            System.out.println("연결 완료!");

            while (rs.next()) {

                String id = rs.getString("userID");
                String mobile = rs.getString("mobile");
                String date = rs.getString("date");

                System.out.println(id + " " + mobile + " " + date);
            }

        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }

        // 접속 종료
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }
}