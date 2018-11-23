package org.androidtwon.pathandroid;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ghwnw on 2018-11-17.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIDService";

    //[STRAR refresh_token]
    @Override
    public void onTokenRefresh(){
        //Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
    }
}
