<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, main.model.dto.History" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>history</title>
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
            <h1> &nbsp; 위치 히스토리 목록 </h1>
                <jsp:include page="header.jsp" />
                <table id="customers">
                          <tr>
                            <th> ID </th>
                            <th> X 좌표 </th>
                            <th> Y 좌표 </th>
                            <th> 조회일자 </th>
                            <th> 비고 </th>
                          </tr>
                          <% ArrayList<History> list = (ArrayList<History>) request.getAttribute("list");
                                    if ( list != null && list.size() > 0 ){
                                    for (History history : list) { %>
                                      <tr>
                                          <td><%= history.getId() %></td>
                                          <td><%= history.getLat() %></td>
                                          <td><%= history.getLnt() %></td>
                                          <td><%= history.getInquiry_date() %></td>
                                          <td> <input type="button" value="삭제" onclick="historyDel(<%= history.getId() %>);" /> </td>
                                      </tr>
                                 <%  } } else{  %>
                            <tr>
                              <td colspan="5" align = "center"> 위치 히스토리가 없습니다. </td>
                            </tr>
                            <%  }  %>
                          </table>
	</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
function historyDel(id){
    axios.get("/historyDelete?id="+id)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      })
      .finally(function () {
       location.href = "/history";
      });
}
</script>
</html>