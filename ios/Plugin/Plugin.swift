import Foundation
import Capacitor
import ZaloSDK

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ZaloAuthPlugin)
public class ZaloAuthPlugin: CAPPlugin {
    func isEqual<T: Equatable>(type: T.Type, a: Any, b: Any) -> Bool {
        guard let a = a as? T, let b = b as? T else { return false }
        return a == b
    }
    
    @objc func login(_ call: CAPPluginCall) {
        ZaloSDK.sharedInstance()?.authenticateZalo(
            with: ZAZaloSDKAuthenTypeViaZaloAppOnly,
            parentController: self.bridge.viewController,
            handler: {
                response in //callback kết quả đăng nhập
                if ((response?.isSucess) != nil) {
                    call.success([
                        "success": true,
                        "token": response?.oauthCode ?? ""
                    ])
                } else  {
                    call.success([
                        "success": false,
                        "error": [
                            "code":"ERROR_001",
                            "message":"Authenticate failed. Progress dismissed"
                        ]
                    ])
                }
            }
        );
    }
    @objc func getUserProfile(_ call: CAPPluginCall) {
        ZaloSDK.sharedInstance()?.getZaloUserProfile(callback: {
            response in
            if((response?.isSucess) != nil) {
                guard let pictureData = response?.data["picture"] as? [String: [String: String]] else { return }

                call.success([
                    "id": response?.data["id"] ?? "",
                    "name": response?.data["name"] ?? "",
                    "gender": response?.data["gender"] ?? "",
                    "birthday": response?.data["birthday"] ?? "",
                    "avatar": pictureData["data"]?["url"] ?? "",
                ])
                
            } else {
                call.success([
                    "success": false,
                    "error": [
                        "code":"ERROR_002",
                        "message":"Get User Profile failed. Are you sure using the same account?"
                    ]
                ])
            }
        })
    }
    @objc func logout(_ call: CAPPluginCall) {
        ZaloSDK.sharedInstance()?.unauthenticate()
    }
}
