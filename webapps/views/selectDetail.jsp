<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.model.Api" %>
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
            <%
                Api api = (Api) request.getAttribute("detail");
            %>
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
</html>