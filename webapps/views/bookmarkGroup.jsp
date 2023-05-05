<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.model.dto.BookmarkGroup" %>
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
	 <% BookmarkGroup bg = (BookmarkGroup)request.getAttribute("bg");
	    if(bg != null){ %>
	    <h1> &nbsp; 북마크 그룹 수정 </h1> <% } else { %>
	    <h1> &nbsp; 북마크 그룹 추가</h1> <%  }  %>
        <jsp:include page="header.jsp" />
        <% if(bg != null){ %> <form action="groupModify" method="get" > <% } else { %>
        <form action="groupAdd" method="get" > <% } %>
            <table  id="customers">
                    <tr>
                         <th><label for="bookmarkName"> 북마크 이름 </label></th>
                        <% if(bg != null){ %><td> <input type="text" name="bookmarkName" id="bookmarkName" value="<%= bg.getBook_name() %>"  /> </td> <% } else { %>
                        <td> <input type="text" name="bookmarkName" id="bookmarkName"  /> </td> <% } %>
                    </tr>
                    <tr>
                        <th ><label for="bookmarkSeq"> 순서 </label></th>
                        <% if(bg != null){ %> <td><input type="text" name="bookmarkSeq" id="bookmarkSeq" value="<%= bg.getBook_seq() %>"  /> </td> <% } else { %>
                        <td><input type="text" name="bookmarkSeq" id="bookmarkSeq"  /> </td> <% } %>
                    </tr>
            </table>
            <% if(bg != null){ %> <input type="hidden" name="bookmarkId" value="<%= bg.getId() %>"><% } %>
            <div style="text-align: center;">
                <% if(bg != null){ %>
                <a href="bookmarkGroupManagement"> 돌아가기</a> &nbsp;|&nbsp;
                <input type="submit" value="수정" /> <% } else { %>
                <h1> <input type="submit" value="추가" /> <%  }  %>
            </div>
        </form>
	</body>
</html>