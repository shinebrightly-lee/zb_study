<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.model.dto.Api, java.util.ArrayList, main.model.dto.BookmarkGroup" %>
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
    	<h1> &nbsp; 와이파이 정보 구하기 </h1>
        <jsp:include page="header.jsp" />
            <% Api api = (Api) request.getAttribute("detail"); %>
            <select id="select_bookmark_name">
            <% ArrayList<BookmarkGroup> list = (ArrayList<BookmarkGroup>) request.getAttribute("list");  %>
                <option> 북마크 그룹 이름 선택 </option>
                <% for (BookmarkGroup group : list) { %>
                    <option name="select_bookmark_name" value="<%= group.getBook_name() %>"> <%= group.getBook_name() %> </option> <%  }  %>
            </select>
            <input type="button" value="북마크 추가하기" onclick="bookmarkAdd(<%= api.getKM() %>,'<%= api.getMAIN_NM() %>', '<%= api.getMGR_NO() %>');"/>
                    <table  id="customers">
                        <tr>
                            <th>거리(Km)</th>
                            <td> <%= api.getKM() %></td>
                        </tr>
                        <tr>
                            <th>관리번호</th>
                            <td><%= api.getMGR_NO() %></td>
                        </tr>
                        <tr>
                            <th>자치구</th>
                            <td><%= api.getWRDOFC() %></td>
                        </tr>
                        <tr>
                            <th>와이파이명</th>
                            <td><a href=""><%= api.getMAIN_NM() %></a></td>
                        </tr>
                        <tr>
                            <th>도로명 주소</th>
                            <td><%= api.getADRES1() %></td>
                        </tr>
                        <tr>
                            <th>상세주소</th>
                            <td><%= api.getADRES2() %></td>
                        </tr>
                        <tr>
                            <th>설치위치(층)</th>
                            <td><%= api.getINSTL_FLOOR() %></td>
                        </tr>
                        <tr>
                            <th>설치유형</th>
                            <td><%= api.getINSTL_TY() %></td>
                        </tr>
                        <tr>
                            <th>설치기관</th>
                            <td><%= api.getINSTL_MBY() %></td>
                        </tr>
                        <tr>
                            <th>서비스 구분</th>
                            <td><%= api.getSVC_SE() %></td>
                        </tr>
                        <tr>
                            <th>망종류</th>
                            <td><%= api.getCMCWR() %></td>
                        </tr>
                        <tr>
                            <th>설치년도</th>
                            <td><%= api.getCNSTC_YEAR() %></td>
                        </tr>
                        <tr>
                            <th>실내외구분</th>
                            <td><%= api.getINOUT_DOOR() %></td>
                        </tr>
                        <tr>
                            <th>wifi 접속환경</th>
                            <td><%= api.getREMARS3() %></td>
                        </tr>
                        <tr>
                            <th>Y 좌표</th>
                            <td><%= api.getLAT() %></td>
                        </tr>
                        <tr>
                            <th>X 좌표</th>
                            <td><%= api.getLNT() %></td>
                        </tr>
                        <tr>
                            <th>작업일자</th>
                            <td><%= api.getWORK_DTTM() %></td>
                        </tr>
                    </table>
	</body>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
    function bookmarkAdd(km, wifi, mgr_no){
        const select_bookmark_name = document.getElementById('select_bookmark_name');
        const name = select_bookmark_name.options[select_bookmark_name.selectedIndex].value;
         axios.post('/bookmarkAdd', {
           km : km,
           wifi : wifi,
           mgr_no : mgr_no,
           name : name
         }, {
           headers: {
             'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
           }
         })
         .then(function (response) {
           location.href="bookmarkList";
           console.log(response);
         })
         .catch(function (error) {
           console.log(error);
         });


     }
    </script>
</html>