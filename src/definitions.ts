declare module '@capacitor/core' {
  interface PluginRegistry {
    ZaloAuthCapacitorPlugin: ZaloAuthCapacitorPluginPlugin;
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
  readonly gender?: string;
  readonly birthday?: string;
  readonly avatar?: string;
}

export interface ZaloAuthCapacitorPluginPlugin {
  login(): Promise<LoginResponse>;
  getUserProfile(): Promise<UserProfile>;
  logout(): Promise<void>;
}
