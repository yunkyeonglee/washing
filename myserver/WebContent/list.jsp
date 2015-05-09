<%@ page language="java" contentType="text/html; charset=EUC-KR"
    import="java.io.*,java.sql.*,java.util.*,com.yk.myserver.*"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js" ></script>
<script type="text/javascript">
$(function(){
	$('.item').click(function(){
		
		var url = "http://192.168.0.50:8080/myserver/reservation.jsp?id=" + $(this).data("value");
		window.location.assign(url);
	});
});
</script>

<style>
.item{
	white-space:nowrap;
    display:block;
    width:420px;
    height:30px;
    cursor: hand; 
}

.bitem{
	background-color: yellow;
	white-space:nowrap;
    display:block;
    width:420px;
    height:30px;
}

.item div{
	display: inline;
list-style-type: none;
padding-right: 20px;
cursor: hand; 
}

.bitem div{
	display: inline;
list-style-type: none;
padding-right: 20px;
}
</style>
</head>
<body> 
<div>

<%
	//Do required initialization
	DBConnectionManager manager = null;
	try {
		manager = new DBConnectionManager("jdbc:mysql://localhost:3306/myserver", "root", "root");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Connection conn = manager.getConnection();

	try {
		PreparedStatement stmt = conn.prepareStatement("select * from machine");
	
		ResultSet set = stmt.executeQuery();
		while( set.next() ){
			String id = set.getString("id");
			String name = set.getString("name");
			String status = set.getString("status");
			String building= set.getString("building");
			String used = set.getString("used");
			if( status.compareTo("U") == 0 ){
				status = "사용가능";
			}else{
				status = "사용중";
			}
			if( used.compareTo("N") == 0 ){
				status = "수리중";
			}
			String desc = set.getString("description");
			
			%>
			<div <%if(used.compareTo("N") == 0){ %>class="bitem"<%}else{ %>class="item"<%} %> data-value="<%=id %>" >
			<div class="name" ><%=name %></div><div class="using" ><%=status %></div><div class="desc"><%=building %></div>
			</div>
			<%
		}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>

</div>

</body>
</html>