declare module '@capacitor/core' {
  interface PluginRegistry {
    ZaloAuthPlugin: ZaloAuthPluginPlugin;
  }
}

export interface Errorable {
  readonly error?: {
    code: string;
    message: string;
  };
}
export interface LoginResponse extends Errorable {
  readonly success: boolean;
  readonly token?: string;
}

export interface UserProfile extends Errorable {
  readonly success: boolean;
  readonly id: string;
  readonly name: string;
  readonly firstName?: string;
  readonly lastName?: string;
  readonly gender?: string;
  readonly birthday?: string;
  readonly phone?: string;
  readonly avatar?: string;
}

export interface ZaloAuthPluginPlugin {
  login(): Promise<LoginResponse>;
  getUserProfile(): Promise<UserProfile>;
  logout(): Promise<void>;
}
