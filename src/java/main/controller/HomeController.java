package main.controller;

import main.model.*;
import main.sevice.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        ApiService apiService = new ApiService();
        try {
            if("/openApi_Info".equals(req.getRequestURI())){
                int result = apiService.apiInfo();
                req.setAttribute("list_total_count", result);
            } else if (req.getRequestURI().contains("selectDetail")) {
                String km = req.getParameter("KM");
                String mgrNo = req.getParameter("MGR_NO");
                Api apiDetail = apiService.selectDetail(mgrNo);
                apiDetail.setKM(Double.parseDouble(km));
                req.setAttribute("detail", apiDetail);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String uri = req.getRequestURI().equals("/")  ?  "/home.jsp"  :  req.getRequestURI().concat(".jsp");
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
            ArrayList<Api> apiList = new ArrayList<>();
            try {
                ApiService apiService = new ApiService();
                apiList = apiService.closeRange20(lat,lnt);
                req.setAttribute("list", apiList);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        rd = req.getRequestDispatcher("/views/home.jsp");
        rd.forward(req,resp);
    }
}
