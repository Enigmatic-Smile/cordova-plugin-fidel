package com.fidelcordovalibrary.events;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.fidelcordovalibrary.adapters.abstraction.DataProcessor;

public final class ErrorEventEmitter implements DataProcessor<WritableMap> {

    private ReactApplicationContext reactContext;

    public ErrorEventEmitter(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override
    public void process(WritableMap data) {
        reactContext
                .getJSModule(RCTDeviceEventEmitter.class)
                .emit("CardLinkFailed", data);
    }
}
