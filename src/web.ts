import { WebPlugin } from '@capacitor/core';
import { ZaloAuthPluginPlugin } from './definitions';

export class ZaloAuthPluginWeb
  extends WebPlugin
  implements ZaloAuthPluginPlugin {
  constructor() {
    super({
      name: 'ZaloAuthPlugin',
      platforms: ['web'],
    });
  }

  getOAuthToken() {
    return Promise.resolve({ token: 'Hello Web Plugin' });
  }

  async login() {
    console.log('[login] Web version is not supported');
    return Promise.resolve({
      success: false,
    });
  }
  async getUserProfile() {
    console.log('[getUserProfile] Web version is not supported');
    return { success: false, id: '', name: '' };
  }
  async logout() {
    console.log('[logout] Web version is not supported');
  }
}

const ZaloAuthPlugin = new ZaloAuthPluginWeb();

export { ZaloAuthPlugin };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(ZaloAuthPlugin);
