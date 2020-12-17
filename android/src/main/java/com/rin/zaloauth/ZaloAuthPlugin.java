package com.rin.zaloauth;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

@NativePlugin
public class ZaloAuthPlugin extends Plugin {

    @PluginMethod
    public void login(PluginCall call) {
        JSObject ret = new JSObject();
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
