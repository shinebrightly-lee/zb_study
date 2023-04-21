<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title> 와이파이 정보 구하기 </title>
</head>
<body>
    <h1> &nbsp; 와이파이 정보 구하기 </h1>
        <div>
            &nbsp; <a href="home"> 홈 </a> &nbsp; | &nbsp;
            <a href="history"> 위치 히스토리 목록 </a> &nbsp; | &nbsp;
            <a href="openApi_Info"> Open Api 와이파이 정보 가져오기 </a><br/> </br>
        </div>

    	<div >
    		<form action="" method="post" name="">
    			&nbsp; LAT : <input type="text" id="lat" value="0.0"/>
    			, LNT : <input type="text"  id="lnt" value="0.0"/>
    			<input type="button" value="내 위치 가져오기" onclick="place();" />
    			<input type="button" value="근처 WIPI 정보 보기" class="" />
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
          </tr>
          <tr>
            <td colspan="5" align = "center"> 위치 정보를 입력 후 조회 해주세요. </td>
          </tr>
        </table>


    	<%-- <div id="">
    		 <div id="lists">
    		<%for(BoardDTO dto : lists){ %>
    			<dl>
    				<dd class="num"><%=dto.getNum() %></dd>
    				<dd class="subject">
    				<a href="<%=articleUrl %>&num=<%=dto.getNum()%>">
    				<%=dto.getSubject() %></a>
    				<!-- currentPage는 현재 내가보고있는 페이지 -->
    				</dd>
    				<dd class="name"><%=dto.getName() %></dd>
    				<dd class="created"><%=dto.getCreated() %></dd>
    				<dd class="hitCount"><%=dto.getHitCount() %></dd>
    			</dl>
    			<%} %>
    		</div> --%>


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
    </script>
</html>