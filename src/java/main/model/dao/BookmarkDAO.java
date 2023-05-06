package main.model.dao;

import common.*;
import main.model.dto.*;

import java.sql.*;
import java.util.*;

public class BookmarkDAO {
    private JDBC jdbc;
    public BookmarkDAO() {
        this.jdbc = new JDBC();
    }

    public void insertBookmarkGroup(String name, int seq){
        try{
            String SQL = "INSERT INTO bookmark_group " +
                                "( book_name, book_seq, registration_date ) VALUES ( ?, ?,  NOW() ) ";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setString(1, name);
            jdbc.pstmt.setInt(2, seq);
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }

    public ArrayList<BookmarkGroup> selectAllBookmarkGroup(){
        ArrayList<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM bookmark_group ORDER BY book_seq ASC";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.rs = jdbc.pstmt.executeQuery();
            while (jdbc.rs.next()){
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setId(jdbc.rs.getInt("id"));
                bookmarkGroup.setBook_name(jdbc.rs.getString("book_name"));
                bookmarkGroup.setBook_seq(jdbc.rs.getInt("book_seq"));
                bookmarkGroup.setRegistration_date(jdbc.rs.getString("registration_date"));
                bookmarkGroup.setInquiry_date(jdbc.rs.getString("inquiry_date"));
                bookmarkGroupList.add(bookmarkGroup);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
        return bookmarkGroupList;
    }

    public void modifyBookmarkGroup(int id, String name, int seq){
        try{
            String SQL = "UPDATE bookmark_group SET book_name = ?, book_seq = ? WHERE id = ?";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setString(1, name);
            jdbc.pstmt.setInt(2, seq);
            jdbc.pstmt.setInt(3, id );
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }

    public void inquiry_dateUpdate(int id){
        // 반드시 다른 작업을 하기전에 사용할 것 ( closeConn() 없음. )
        try{
            String SQL = "UPDATE bookmark_group SET inquiry_date = NOW() WHERE id = ?";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setInt(1, id );
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void selectGroupDelete(int id){
        try{
            String SQL = "DELETE FROM bookmark_group WHERE id = ? ";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setInt(1,id);
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }

    public void bookmarkListAdd(Double km, String mgr_no, String wifi, String name){
        try{
            String SQL = "INSERT INTO bookmark_list " +
                    "( km, book_name, wifi_name, registration_date, mgr_no ) VALUES ( ?, ?, ?,  NOW(), ? ) ";
            jdbc.pstmt = jdbc.conn.prepareStatement( SQL );
            jdbc.pstmt.setDouble(1, km );
            jdbc.pstmt.setString(2, name );
            jdbc.pstmt.setString(3, wifi );
            jdbc.pstmt.setString(4, mgr_no );
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }
    public ArrayList<Bookmark> selectAllBookmarkList(){
        ArrayList<Bookmark> bookmarkList = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM bookmark_list";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.rs = jdbc.pstmt.executeQuery();
            while (jdbc.rs.next()){
                Bookmark bookmark = new Bookmark();
                bookmark.setMgr_no(jdbc.rs.getString("mgr_no"));
                bookmark.setId(jdbc.rs.getInt("id"));
                bookmark.setBook_name(jdbc.rs.getString("book_name"));
                bookmark.setWifi_name(jdbc.rs.getString("wifi_name"));
                bookmark.setRegistration_date(jdbc.rs.getString("registration_date"));
                bookmarkList.add(bookmark);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
        return bookmarkList;
    }

    public void selectBookmarkDelete(int id){
        try{
            String SQL = "DELETE FROM bookmark_list WHERE id = ? ";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setInt(1,id);
            jdbc.pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
    }

    public Double selectKM(String mgr_no){
        double km = 0.0;
        try {
            String SQL = "SELECT km FROM bookmark_list WHERE mgr_no = ? ";
            jdbc.pstmt = jdbc.conn.prepareStatement(SQL);
            jdbc.pstmt.setString(1, mgr_no);
            jdbc.rs = jdbc.pstmt.executeQuery();
            if (jdbc.rs.next()) {
                km = jdbc.rs.getDouble("km");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.closeConn();
        }
        return km;
    }

}
