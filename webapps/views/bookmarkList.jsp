<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, main.model.dto.Bookmark" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>bookmark_list</title>
	</head>
	<style>
        #customers {
          font-family: Arial, Helvetica, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }

        #customers td, #customers th {
          border: 1px solid #ddd;
          padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
          padding-top: 12px;
          padding-bottom: 12px;
          text-align: left;
          background-color: #04AA6D;
          color: white;
        }
        </style>
	<body>
          <h1> &nbsp; 북마크 목록 </h1>
                  <jsp:include page="header.jsp" />
                  <table id="customers">
                            <tr>
                              <th> ID </th>
                              <th> 북마크 이름 </th>
                              <th> 와이파이명 </th>
                              <th> 등록일자</th>
                              <th> 비고 </th>
                            </tr>
                            <%
                                  ArrayList<Bookmark> list = (ArrayList<Bookmark>) request.getAttribute("list");
                                    if(list != null){
                                    for (Bookmark bookmark : list) { %>
                                      <tr>
                                          <td><%= bookmark.getId() %></td>
                                          <td><%= bookmark.getBook_name() %></td>
                                          <td><a href="bookmarkDetail?mgr_no=<%= bookmark.getMgr_no() %>"><%= bookmark.getWifi_name() %></a></td>
                                          <td><%= bookmark.getRegistration_date() %></td>
                                         <td><a href="bookmarkDeleteCheck?id=<%= bookmark.getId() %>&book_name=<%= bookmark.getBook_name() %>&wifi_name=<%= bookmark.getWifi_name() %>&date=<%= bookmark.getRegistration_date() %>"> 삭제 </a></td>
                                      </tr>
                                 <% } } else{ %>
                            <tr>
                              <td colspan="5" align = "center"> 목록 정보가 존재하지 않습니다. </td>
                            </tr>
                            <%  }  %>
                          </table>
	</body>
</html>