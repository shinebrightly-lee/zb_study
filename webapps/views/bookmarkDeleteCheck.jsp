<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.model.dto.Bookmark" %>
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
	<h1> &nbsp; 북마크 목록 </h1>
      <jsp:include page="header.jsp" />
        <h3> 북마크를 삭제 하시겠습니까? </h3>
        <% Bookmark bm = (Bookmark) request.getAttribute("bookmark"); %>
            <table  id="customers">
                    <tr>
                         <th> 북마크 이름 </th>
                        <td> <%= bm.getBook_name() %> </td>
                    </tr>
                    <tr>
                        <th >와이파이명 </th>
                        <td><%= bm.getWifi_name() %> </td>
                    </tr>
                    <tr>
                        <th >등록일자</th>
                        <td><%= bm.getRegistration_date() %></td>
                    </tr>
            </table>
            <div style="text-align: center;">
                    <a href="bookmarkList"> 돌아가기</a> &nbsp;|&nbsp;
                    <input type="button" value="삭제" onclick="bookmarkDelete(<%= bm.getId() %>);" />
            </div>
	</body>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
    function bookmarkDelete(id){
    console.log(id);
        axios.get("/bookmarkDelete?id="+id)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
           location.href = "/bookmarkList";
          });
    }
    </script>
</html>