package main.controller;

import main.model.dto.*;
import main.sevice.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/")
public class HomeController extends HttpServlet {
        private ApiService apiService;
        private HistoryService historyService;
        private BookmarkService bookmarkService;
    @Override
    public void init() throws ServletException {
            apiService = new ApiService();
            historyService = new HistoryService();
            bookmarkService = new BookmarkService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET" + req.getRequestURI());
        String uri = "";
        try {
            if("/openApi_Info".equals(req.getRequestURI())){
                int result = apiService.apiInfo();
                req.setAttribute("list_total_count", result);
            } else if (req.getRequestURI().contains("selectDetail")) {
                String km = req.getParameter("KM");
                String mgrNo = req.getParameter("MGR_NO");
                Api apiDetail = apiService.selectDetail(mgrNo);
                apiDetail.setKM(Double.parseDouble(km));
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                req.setAttribute("detail", apiDetail);
            } else if ("/history".equals(req.getRequestURI())) {
                ArrayList<History> historyArrayList = historyService.historyList();
                req.setAttribute("list", historyArrayList);
            } else if ("/historyDelete".equals(req.getRequestURI())) {
                historyService.historySelectDelete(Integer.parseInt(req.getParameter("id")));
            } else if ("/bookmarkGroup".equals(req.getRequestURI())) {
                BookmarkGroup bg = new BookmarkGroup();
                if(req.getParameter("id") != null){
                    bg.setId(Integer.parseInt(req.getParameter("id")));
                    bg.setBook_name(req.getParameter("name"));
                    bg.setBook_seq(Integer.parseInt(req.getParameter("seq")));
                    req.setAttribute("bg", bg);
                }
            } else if ("/groupAdd".equals(req.getRequestURI())) {
                String name = req.getParameter("bookmarkName");
                int seq = Integer.parseInt(req.getParameter("bookmarkSeq"));
                bookmarkService.groupAdd(name, seq);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/groupModify".equals(req.getRequestURI())) {
                int id = Integer.parseInt(req.getParameter("bookmarkId"));
                String name = req.getParameter("bookmarkName");
                int seq = Integer.parseInt(req.getParameter("bookmarkSeq"));
                bookmarkService.groupModify(id, name, seq);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/groupDelete".equals(req.getRequestURI())) {
                int id = Integer.parseInt(req.getParameter("id"));
                bookmarkService.groupDelete(id);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/bookmarkGroupManagement".equals(req.getRequestURI())) {
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
            } else if ("/bookmarkList".equals(req.getRequestURI())) {
                ArrayList<Bookmark> bookmark = bookmarkService.bookmarkListAll();
                req.setAttribute("list", bookmark);
            } else if ("/bookmarkDeleteCheck".equals(req.getRequestURI())) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(Integer.parseInt(req.getParameter("id")));
                bookmark.setBook_name(req.getParameter("book_name"));
                bookmark.setWifi_name(req.getParameter("wifi_name"));
                bookmark.setRegistration_date(req.getParameter("date"));
                req.setAttribute("bookmark", bookmark);
            }else if ("/bookmarkDelete".equals(req.getRequestURI())) {
                int id = Integer.parseInt(req.getParameter("id"));
                bookmarkService.bookmarkDelete(id);
            } else if ("/bookmarkDetail".equals(req.getRequestURI())) {
                String mgr_no = req.getParameter("mgr_no");
                double km = bookmarkService.selectKM(mgr_no);
                Api apiDetail = apiService.selectDetail(mgr_no);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                apiDetail.setKM(km);
                req.setAttribute("detail", apiDetail);
                req.setAttribute("list", bookmarkGroupList);
                uri = "/selectDetail.jsp";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        uri = !(uri.equals("")) ? uri : req.getRequestURI().equals("/")  ?  "/home.jsp"  :  req.getRequestURI().concat(".jsp");
        RequestDispatcher rd = req.getRequestDispatcher("/views".concat(uri));
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST" + req.getRequestURI());
        String uri = "";

        if("/closeRange20".equals(req.getRequestURI())){
            double lat = Double.parseDouble(req.getParameter("lat"));
            double lnt = Double.parseDouble(req.getParameter("lnt"));
                req.setAttribute("lat", lat);
                req.setAttribute("lnt", lnt);
            try {
                apiService = new ApiService();
                ArrayList<Api> apiList = apiService.closeRange20(lat,lnt);
                req.setAttribute("list", apiList);
                HistoryService historyService = new HistoryService();
                historyService.historySave(lat,lnt);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            uri = "/home.jsp";
        }else if("/bookmarkAdd".equals(req.getRequestURI())){
            bookmarkService = new BookmarkService();
            Double km = Double.parseDouble(req.getParameter("km"));
            String wifi = req.getParameter("wifi");
            String mgrNo = req.getParameter("mgr_no");
            String name = req.getParameter("name");
            bookmarkService.bookmarkAdd( km, mgrNo, wifi, name );
            ArrayList<Bookmark> bookmark = bookmarkService.bookmarkListAll();
            req.setAttribute("list", bookmark);
        }
        uri = !(uri.equals("")) ? uri : req.getRequestURI().equals("/")  ?  "/home.jsp"  :  req.getRequestURI().concat(".jsp");
        RequestDispatcher rd = req.getRequestDispatcher("/views".concat(uri));
        rd.forward(req,resp);
    }
}
