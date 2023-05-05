<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, main.model.dto.Api" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 와이파이 정보 구하기 </title>
<!-- <link href="/resources/style.css" rel="stylesheet"> -->
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
</head>
<body>
    <h1> &nbsp; 와이파이 정보 구하기 </h1>
    <jsp:include page="header.jsp" />
    	<div >
    		 <form action="closeRange20" method="post" >
    		 <% double lat = request.getAttribute("lat") != null ? (Double) request.getAttribute("lat") : 0.0; %>
    		 <% double lnt = request.getAttribute("lnt") != null ? (Double) request.getAttribute("lnt") : 0.0; %>
    			&nbsp; LAT : <input type="text" name="lat" id="lat" value="<%= lat %>"/>
    			, LNT : <input type="text" name="lnt" id="lnt" value="<%= lnt %>"/>
    			<input type="button" value="내 위치 가져오기" onclick="place();" />
    			<input type="submit" value="근처 WIFI 정보 보기" />
    		</form>
    	</div>
        </br>
    	<table id="customers">
          <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명 주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스 구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>wifi 접속환경</th>
            <th>Y 좌표</th>
            <th>X 좌표</th>
            <th>작업일자</th>
          </tr>
          <%
                ArrayList<Api> list = (ArrayList<Api>) request.getAttribute("list");
                  if(list != null){
                  for (Api api : list) { %>
                    <tr>
                        <td><%= api.getKM() %></td>
                        <td><%= api.getMGR_NO() %></td>
                        <td><%= api.getWRDOFC() %></td>
                        <td><a href="selectDetail?KM=<%= api.getKM() %>&MGR_NO=<%= api.getMGR_NO() %>"><%= api.getMAIN_NM() %></a></td>
                        <td><%= api.getADRES1() %></td>
                        <td><%= api.getADRES2() %></td>
                        <td><%= api.getINSTL_FLOOR() %></td>
                        <td><%= api.getINSTL_TY() %></td>
                        <td><%= api.getINSTL_MBY() %></td>
                        <td><%= api.getSVC_SE() %></td>
                        <td><%= api.getCMCWR() %></td>
                        <td><%= api.getCNSTC_YEAR() %></td>
                        <td><%= api.getINOUT_DOOR() %></td>
                        <td><%= api.getREMARS3() %></td>
                        <td><%= api.getLAT() %></td>
                        <td><%= api.getLNT() %></td>
                        <td><%= api.getWORK_DTTM() %></td>
                    </tr>
               <%  } } else{ %>
          <tr>
            <td colspan="17" align = "center"> 위치 정보를 입력 후 조회 해주세요. </td>
          </tr>
          <%  }  %>
        </table>
   <div>
   </div>

    </div>
</body>
<script>
        let latitude;
        let longitude;
        function success({ coords, timestamp }) {
            latitude = coords.latitude;   // 위도
            longitude = coords.longitude; // 경도
        }
        function getUserLocation() {
            if (!navigator.geolocation) {
                throw "위치 정보가 지원되지 않습니다.";
            }
            navigator.geolocation.watchPosition(success);
        }
        getUserLocation();

        function place(){
            document.getElementById('lat').value = latitude;
            document.getElementById('lnt').value = longitude;
        }
        function apiBtnDisabled(){
            location.href="openApi_Info";
        }
    </script>
</html>