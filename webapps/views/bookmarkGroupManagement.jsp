<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, main.model.dto.BookmarkGroup" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>웹페이지 제목</title>
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
	<h1> &nbsp; 북마크 그룹 </h1>
        <jsp:include page="header.jsp" />
        <input type="button" value="북마크 그룹 이름 추가" onclick="groupAdd();" />
        <table id="customers">
                  <tr>
                    <th> ID </th>
                    <th> 북마크 이름 </th>
                    <th> 순서 </th>
                    <th> 등록일자</th>
                    <th> 수정일자</th>
                    <th> 비고 </th>
                  </tr>
                  <%
                        ArrayList<BookmarkGroup> list = (ArrayList<BookmarkGroup>) request.getAttribute("list");
                          if(list != null){
                          for (BookmarkGroup group : list) { %>
                            <tr>
                                <td><%= group.getId() %></td>
                                <td><%= group.getBook_name() %></td>
                                <td><%= group.getBook_seq() %></td>
                                <td><%= group.getRegistration_date() %></td>
                                <% if( group.getInquiry_date() == null){ %>
                                <td>  </td> <% } else { %>
                                <td><%= group.getInquiry_date() %></td> <% } %>
                               <td><a href="bookmarkGroup?id=<%= group.getId() %>&name=<%= group.getBook_name() %>&seq=<%= group.getBook_seq() %>"> 수정 </a>
                               &nbsp; <a href="groupDelete?id=<%= group.getId() %>"> 삭제 </a></td>
                            </tr>
                       <% } } else{ %>
                  <tr>
                    <td colspan="6" align = "center"> 그룹 정보가 존재하지 않습니다. </td>
                  </tr>
                  <%  }  %>
                </table>
	</body>
<script>
    function groupAdd(){
               location.href = "/bookmarkGroup";
    }
</script>
</html>