<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>open_api_info</title>
	</head> 
	<body>
	    <div style="text-align: center;" >
            <h1> 총 <%= request.getAttribute("list_total_count") %> WIFI 정보를 정상적으로 저장 하였습니다. </h1>
            <a href="/home"> 홈으로 가기 </a>
	    </div>
	</body>
</html>