package com.rin.zaloauth;

import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import com.zing.zalo.zalosdk.oauth.*;

@NativePlugin
public class ZaloAuthCapacitorPlugin extends Plugin {

    @PluginMethod
    public void login(PluginCall call) {
        OAuthCompleteListener listener = new OAuthCompleteListener() {
            @Override
            public void onAuthenError(int errorCode, String message) {
                //Đăng nhập thất bại..
            }

            @Override
            public void onGetOAuthComplete(OauthResponse response) {
                String code = response.getOauthCode();
                //Đăng nhập thành công..
            }
        };
        ZaloSDK.Instance.authenticate(this.getActivity(), LoginVia.APP_OR_WEB, listener);
        ret.put("token", "login from Android");
        call.success(ret);
    }

    @PluginMethod
    public void getUserProfile(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("token", "getUserProfile from Android");
        call.success(ret);
    }

    @PluginMethod
    public void logout(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("token", "logout from Android");
        call.success(ret);
    }
}
