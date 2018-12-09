<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd>
<html lang="kr">
<script type="text/javascript">
<%
	String id = (String) session.getAttribute("id");				
%>
$(document).ready(function() {
	alert("또잉또잉유자찰떡");
	$.ajax({
		url : "/user/logout.json",
		type : "GET",
		data : {"stuId" : <%=id%>}
	});
});
</script>
<%
	response.sendRedirect("/");
%>