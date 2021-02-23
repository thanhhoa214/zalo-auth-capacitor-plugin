package com.rin.zaloauth;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.zing.zalo.zalosdk.oauth.FeedData;
import com.zing.zalo.zalosdk.oauth.LoginVia;
import com.zing.zalo.zalosdk.oauth.OAuthCompleteListener;
import com.zing.zalo.zalosdk.oauth.OauthResponse;
import com.zing.zalo.zalosdk.oauth.OpenAPIService;
import com.zing.zalo.zalosdk.oauth.ZaloOpenAPICallback;
import com.zing.zalo.zalosdk.oauth.ZaloPluginCallback;
import com.zing.zalo.zalosdk.oauth.ZaloSDK;
import org.json.JSONException;
import org.json.JSONObject;

@NativePlugin
public class ZaloAuthCapacitorPlugin extends Plugin {

    @PluginMethod
    public void login(final PluginCall call) {
        final JSObject ret = new JSObject();
        OAuthCompleteListener listener = new OAuthCompleteListener() {
            @Override
            public void onAuthenError(int errorCode, String message) {
                ret.put("success", true);
                ret.put("error", new ErrorType("ERROR_001", "Authenticate failed. Progress dismissed"));
            }

            @Override
            public void onGetOAuthComplete(OauthResponse response) {
                String code = response.getOauthCode();
                ret.put("success", true);
                ret.put("oauthCode", code);
                call.success(ret);
            }
        };
        ZaloSDK.Instance.authenticate(this.getActivity(), LoginVia.APP_OR_WEB, listener);
    }

    @PluginMethod
    public void getUserProfile(final PluginCall call) {
        final String[] fields = { "id", "birthday", "gender", "picture", "name" };
        ZaloSDK.Instance.getProfile(
            this.getContext(),
            new ZaloOpenAPICallback() {
                @Override
                public void onResult(JSONObject jsonObject) {
                    JSObject ret = new JSObject();
                    try {
                        ret.put("success", true);
                        ret.put("id", jsonObject.get("id"));
                        ret.put("birthday", jsonObject.get("birthday"));
                        ret.put("gender", jsonObject.get("gender"));
                        ret.put("picture", jsonObject.getJSONObject("picture").getJSONObject("data").get("url"));
                        ret.put("name", jsonObject.get("name"));
                        call.success(ret);
                    } catch (JSONException e) {
                        ret.put("success", false);
                        call.success(ret);
                        e.printStackTrace();
                    }
                }
            },
            fields
        );
    }

    @PluginMethod
    public void logout(PluginCall call) {
        ZaloSDK.Instance.unauthenticate();
        JSObject ret = new JSObject();
        ret.put("success", true);
        call.success(ret);
    }

    @PluginMethod
    public void share(final PluginCall call) {
        JSObject input = call.getData().getJSObject("input");

        FeedData feed = new FeedData();
        feed.setMsg(input.getString("message"));
        feed.setLink(input.getString("link"));
        feed.setLinkTitle(input.getString("title"));
        feed.setLinkSource(input.getString("link"));
        feed.setLinkThumb(new String[] {input.getString("thumbnailUrl")});
        OpenAPIService.getInstance().shareFeed(this.getContext(), feed, new ZaloPluginCallback() {
            @Override
            public void onResult(boolean b, int i, String s, String s1) {
                JSObject ret = new JSObject();
                ret.put("success", true);
                call.success(ret);
            }
        });
    }
}

class ErrorType {

    private String code;
    private String message;

    public ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
