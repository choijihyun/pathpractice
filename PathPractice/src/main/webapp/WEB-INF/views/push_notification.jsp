<?php 
    
    function send_notification ($tokens, $message)
    {
        $url = 'https://fcm.googleapis.com/fcm/send';
        $fields = array(
             'registration_ids' => $tokens,
             'data' => $message
            );
 
        $headers = array(
            'Authorization:key =' "AAAAT7524RU:APA91bHmHPHjRxinj3ZWMpFKaIdnUrdLZ4DtM3ZqCNCeBbFF3iCB4zxHh6KGMFSth809I35ud83zGD8kalN89XO9R9ICrKv-Xv3igphMlkSft2K6ZZXg5GN_MF-uoYqUSBbDwohtqYDs",
            'Content-Type: application/json'
            );
 
       $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);           
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
    }
    
 
    //데이터베이스에 접속해서 토큰들을 가져와서 FCM에 발신요청
    include_once 'root-context.xml';
    $conn =mysqli_connect("jdbc:mysql://203.250.148.53/PATH?useSSL=false&amp;serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=utf8", 
			jihyun,
			root,
			com.mysql.jdbc.Driver);
 
    $sql = "Select token From Student";
 
    $result = mysqli_query($conn,$sql);
    $tokens = array();
 
    if(mysqli_num_rows($result) > 0 ){
 
        while ($row = mysqli_fetch_assoc($result)) {
            $tokens[] = $row["token"];
        }
    }
    
    mysqli_close($conn);
    
    $myMessage = $_POST['message']; //폼에서 입력한 메세지를 받음
    if ($myMessage == ""){
        $myMessage = "새글이 등록되었습니다!";
    }
 	
    $message = array("message" => $myMessage);
    $message_status = send_notification($tokens, $message);
    echo $message_status;
?>

