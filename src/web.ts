import { WebPlugin } from '@capacitor/core';
import { ZaloAuthCapacitorPluginPlugin } from './definitions';

export class ZaloAuthCapacitorPluginWeb
  extends WebPlugin
  implements ZaloAuthCapacitorPluginPlugin {
  constructor() {
    super({
      name: 'ZaloAuthCapacitorPlugin',
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
  async share() {
    console.log('[share] Web version is not supported');
    return { success: false };
  }
}

const ZaloAuthCapacitorPlugin = new ZaloAuthCapacitorPluginWeb();

export { ZaloAuthCapacitorPlugin };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(ZaloAuthCapacitorPlugin);
