<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import = "java.io.*, java.net.HttpURLConnection, java.net.URL" %>    
<%@ page import="java.sql.*"%>
<%@ page import="com.google.android.gcm.server.*"%> 
 
<%
    ArrayList<String> token = new ArrayList<String>();    //token값을 ArrayList에 저장
    String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);    //메시지 고유 ID
    boolean SHOW_ON_IDLE = false;    //옙 활성화 상태일때 보여줄것인지
    int LIVE_TIME = 1;    //옙 비활성화 상태일때 FCM가 메시지를 유효화하는 시간
    int RETRY = 2;    //메시지 전송실패시 재시도 횟수
 
    
    String simpleApiKey = "AAAAT7524RU:APA91bHmHPHjRxinj3ZWMpFKaIdnUrdLZ4DtM3ZqCNCeBbFF3iCB4zxHh6KGMFSth809I35ud83zGD8kalN89XO9R9ICrKv-Xv3igphMlkSft2K6ZZXg5GN_MF-uoYqUSBbDwohtqYDs";
    String gcmURL = "https://android.googleapis.com/fcm/send";    
    //Connection conn = null; 
    //Statement stmt = null; 
    //ResultSet rs = null;
    
    String msg = request.getParameter("message");;
    
    if(msg==null || msg.equals("")){
        msg="";
    }
    
    msg = new String(msg.getBytes("UTF-8"), "UTF-8");   //메시지 한글깨짐 처리
    
    try {
       /*  String url = "jdbc:mysql://203.250.148.53/PATH;databaseName=com.mysql.jdbc.Driver;user=jihyun;password=root;";
        conn = DriverManager.getConnection(url);
        out.println("MSSQL 연결이 성공 하였습니다.");
        stmt = conn.createStatement();            
        String sql = "select token from student";
        rs = stmt.executeQuery(sql);
        
        //모든 등록ID를 리스트로 묶음
        while(rs.next()){
            token.add(rs.getString("token"));
        }
        conn.close(); */
        
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        //연결할 controller의 @RequestMapping Value
        String chartUrl = request.getParameter("url"); //ex: /chart/drawBarChart
        String queryString = request.getQueryString();
        //String address = "http://localhost:7001/gcss/chart/drawBarChart.do";

        if (chartUrl != null) {
         //chart 요청 url 
         String address = "http://" + serverName + ":" + serverPort + contextPath + chartUrl + ".do?"+queryString;
           
         URL url = new URL(address);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");
         conn.connect();

         BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));       
         String line;
         StringBuffer sb = new StringBuffer();      
         while ((line = rd.readLine()) != null) {
          sb.append(line);
          sb.append(System.getProperty("line.separator")); 
         }
         rd.close();

         out.print(sb.toString());
        }
         
         //message 전송
        Sender sender = new Sender(simpleApiKey);
        Message message = new Message.Builder()
        .collapseKey(MESSAGE_ID)
        .delayWhileIdle(SHOW_ON_IDLE)
        .timeToLive(LIVE_TIME)
        .addData("message",msg)
        .build();
        MulticastResult result1 = sender.send(message,token,RETRY);
        if (result1 != null) {
            List<Result> resultList = result1.getResults();
            for (Result result : resultList) {
                System.out.println(result.getErrorCodeName()); 
            }
        }
    }catch (Exception e) {
    	 out.println("MSSQL 연결이 실패 하였습니다.");
        e.printStackTrace();
    }
%>


