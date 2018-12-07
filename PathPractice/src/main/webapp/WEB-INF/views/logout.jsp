<script type="text/javascript">
<%
	String id = (String) session.getAttribute("id");				
%>
	$.ajax({
		url : "/user/logout.json",
		type : "GET",
		data : {"stuId" : <%=id%>}
	});
</script>
<%
	session.invalidate();
	response.sendRedirect("/");
%>