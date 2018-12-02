<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;
    String token = null;
    String requestMethod = null;
    
    request.setCharacterEncoding("utf-8");
    
    try{
    	 String url = "jdbc:mysql://203.250.148.53/PATH;databaseName=com.mysql.jdbc.Driver;user=jihyun;password=root;";
        conn = DriverManager.getConnection(url);
        out.println("MSSQL 연결이 성공 하였습니다.");
    }catch(Exception e){
        out.println("MSSQL 연결이 실패 하였습니다.");
        e.printStackTrace();
    }
    
    token = request.getParameter("token");
    
    if( token.equals("") ){
        out.println("토큰값이 전달 되지 않았습니다.");
    }else{
        // 토큰값 전달시 쿼리문 입력할곳임
        sql = "INSERT INTO STUDENT(token) VALUES(?)";
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1,token);
        
        pstmt.executeUpdate();//쿼리를 실행 하라는 명령어
    }
%>