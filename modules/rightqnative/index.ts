import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to RightQNative.web.ts
// and on native platforms to RightQNative.ts
import RightQNativeModule from './src/RightQNativeModule';

export function hello(): string {
  return RightQNativeModule.hello();
}

export function startBroker(): boolean {
  return RightQNativeModule.startBroker();
}

export async function setValueAsync(value: string) {
  return await RightQNativeModule.setValueAsync(value);
}

// const emitter = new EventEmitter(RightQNativeModule ?? NativeModulesProxy?.RightQNative);

// export function addChangeListener(listener: (event: any) => void): Subscription {
//   return emitter.addListener<any>('onChange', listener);
// }
