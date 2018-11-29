<?php
include_once 'root-context.xml';
 
$conn = mysqli_connect("jdbc:mysql://203.250.148.53/PATH?useSSL=false&amp;serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=utf8", 
			jihyun,
			root,
			com.mysql.jdbc.Driver);
 
if($conn){
    echo "접속성공<br>";
}
else{
    echo "접속실패<br>";
}
 
$token = $_POST['Token'];
 
$db_sql = "INSERT INTO Student(token) values('".$token."') ON DUPLICATE KEY UPDATE token = '".$token."';";
mysqli_query($conn, $db_sql);
 
mysqli_close($conn);
?>

