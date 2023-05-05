package main.model.dao;

import common.*;
import main.model.dto.*;

import java.sql.*;
import java.time.*;
import java.util.*;

public class HistoryDAO {
    private JDBC jdbc;
    public HistoryDAO() {
        this.jdbc = new JDBC();
    }

    public void insertHistory(double lat, double lnt){
        try{
            String SQL = "INSERT INTO history_list ( lat , lnt ,  inquiry_date ) VALUES ( ? , ? , ? )";
            LocalDateTime dateTime = LocalDateTime.now();
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setDouble(1, lat);
            jdbc.pstmt.setDouble(2, lnt);
            jdbc.pstmt.setString(3, dateTime.toString());
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }

    public ArrayList<History> selectAllHistory() throws  SQLException{
        ArrayList<History> historyList = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM history_list";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.rs = jdbc.pstmt.executeQuery();

            while (jdbc.rs.next()){
                History history = new History();
                history.setId(jdbc.rs.getInt("id"));
                history.setLat(jdbc.rs.getDouble("lat"));
                history.setLnt(jdbc.rs.getDouble("lnt"));
                history.setInquiry_date(jdbc.rs.getString("inquiry_date"));
                historyList.add(history);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
        return historyList;
    }

    public void selectHistoryDelete(int id){
        try{
            String SQL = "DELETE FROM history_list WHERE id = ? ";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setInt(1,id);
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }


}
