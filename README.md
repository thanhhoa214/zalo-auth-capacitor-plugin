# zalo-auth-capacitor-plugin
Zalo Authenticate through CapacitorJS

Currently, I just publish for personal use.

## Installation:
```bash
npm install zalo-auth-capacitor-plugin
npx cap sync
```

## Configuration:

### 1. iOS:

### 2. Android:

#### 1. In `android/src/build.gradle`:
Inside `dependencies { ... }`, append:

```
implementation "com.zing.zalo.zalosdk:core:+"
implementation "com.zing.zalo.zalosdk:auth:+"
implementation "com.zing.zalo.zalosdk:openapi:+"
```


#### 2. In `android/src/main/java/..../MainActivity.java`:
1. Import class.
    ```
    import com.zing.zalo.zalosdk.oauth.ZaloSDK;
    ```
2. In the callback of `this.init(savedInstanceState, ....), append:
    ```
    add(com.rin.zaloauth.ZaloAuthCapacitorPlugin.class);
    ```
3. In `onActivityResult` method, under `super.onActivityResult(requestCode, resultCode, data);` append:
    ```
    ZaloSDK.Instance.onActivityResult(this, requestCode, resultCode, data);
    ```
#### 3. In `android/src/main/res/values/strings.xml`:
Inside `resources`, create Zalo App on [Zalo Developer](https://developers.zalo.me/), get back `APP_ID` (may be similar `42735613396xxxx150`):
```
<resources>
    ...
    <string name="zalo_app_id">APP_ID_FROM_ZALO_DEVELOPER</string>
    <string name="zalo_login_protocol_scheme">zalo-APP_ID_FROM_ZALO_DEVELOPER</string>
    ...
</resources>
```
#### 4. In `android/src/main/AndroidManifest.xml`:
1. Add `name` attribute to your application.
```
<application
    android:name="com.zing.zalo.zalosdk.oauth.ZaloSDKApplication"
    ...
>
    ...
    <meta-data 
        android:name="com.zing.zalo.zalosdk.appID"   
        android:value="@string/zalo_app_id" 
    />
    <activity
        android:name="com.zing.zalo.zalosdk.oauth.BrowserLoginActivity"
    >
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data android:scheme="@string/zalo_login_protocol_scheme" />
        </intent-filter>
    </activity>

    ...
</application>
```

#### 5. Create Zalo Service for using in your application: (web has not implemented yet)

```
import { Injectable } from '@angular/core';
import { Plugins } from '@capacitor/core';
import { isPlatform } from '@ionic/angular';
import {
  ShareInput,
  ZaloAuthCapacitorPlugin,
  ZaloAuthCapacitorPluginPlugin,
} from 'zalo-auth-capacitor-plugin';

@Injectable({ providedIn: 'root' })
export class LoginZaloService {
  private _zaloLogin: ZaloAuthCapacitorPluginPlugin;

  constructor() {
    this._setupZaloLogin();
  }
  async login() {
    return this._zaloLogin.login();
  }
  async getUserProfileFromZalo() {
    return this._zaloLogin.getUserProfile();
  }
  logout() {
    return this._zaloLogin.logout();
  }

  share(input: ShareInput) {
    return this._zaloLogin.share(input);
  }

  private async _setupZaloLogin() {
    if (isPlatform('desktop')) {
      this._zaloLogin = ZaloAuthCapacitorPlugin;
    } else {
      this._zaloLogin = Plugins.ZaloAuthCapacitorPlugin as ZaloAuthCapacitorPluginPlugin;
    }
  }
}

```



### 🎉 Happy to check your Pull Request at anytime.
