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
        ApiService apiService;
        HistoryService historyService;
        BookmarkService bookmarkService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET" + req.getRequestURI());
        String uri = "";
        try {
            if("/openApi_Info".equals(req.getRequestURI())){
                apiService = new ApiService();
                int result = apiService.apiInfo();
                req.setAttribute("list_total_count", result);
            } else if (req.getRequestURI().contains("selectDetail")) {
                apiService = new ApiService();
                String km = req.getParameter("KM");
                String mgrNo = req.getParameter("MGR_NO");
                Api apiDetail = apiService.selectDetail(mgrNo);
                apiDetail.setKM(Double.parseDouble(km));
                req.setAttribute("detail", apiDetail);
            } else if ("/history".equals(req.getRequestURI())) {
                historyService = new HistoryService();
                ArrayList<History> historyArrayList = historyService.historyList();
                req.setAttribute("list", historyArrayList);
            } else if ("/historyDelete".equals(req.getRequestURI())) {
                historyService = new HistoryService();
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
                bookmarkService = new BookmarkService();
                bookmarkService.groupAdd(name, seq);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/groupModify".equals(req.getRequestURI())) {
                int id = Integer.parseInt(req.getParameter("bookmarkId"));
                String name = req.getParameter("bookmarkName");
                int seq = Integer.parseInt(req.getParameter("bookmarkSeq"));
                bookmarkService = new BookmarkService();
                bookmarkService.groupModify(id, name, seq);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/groupDelete".equals(req.getRequestURI())) {
                int id = Integer.parseInt(req.getParameter("id"));
                bookmarkService = new BookmarkService();
                bookmarkService.groupDelete(id);
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
                uri = "/bookmarkGroupManagement.jsp";
            } else if ("/bookmarkGroupManagement".equals(req.getRequestURI())) {
                bookmarkService = new BookmarkService();
                ArrayList<BookmarkGroup> bookmarkGroupList = bookmarkService.groupList();
                req.setAttribute("list", bookmarkGroupList);
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
        System.out.println("POST");
        RequestDispatcher rd;
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
        }
        rd = req.getRequestDispatcher("/views/home.jsp");
        rd.forward(req,resp);
    }
}
