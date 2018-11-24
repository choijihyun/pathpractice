package org.androidtwon.pathandroid;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ghwnw on 2018-11-17.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIDService";

    //[START refresh_token]
    //다 FirebaseMessagingService.java 로 옮김
    /*@Override
    public void onTokenRefresh(){
        //Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token:"+token);
        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        //request
        Request request = new Request.Builder()
                .url("http://ghwnwjd.cafe24.com/fcm/register.php")
//                /fcm/register.php 이거 언니한테 만들어달라고 해야함
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
