package com.fidelcordovalibrary.events;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.fidel.sdk.Fidel;
import com.fidel.sdk.LinkResult;
import com.fidel.sdk.LinkResultError;
import com.fidelcordovalibrary.adapters.abstraction.DataConverter;
import com.fidelcordovalibrary.adapters.abstraction.DataProcessor;

public final class CallbackActivityEventListener
        extends BaseActivityEventListener
        implements CallbackInput {

    private final DataConverter<Object, WritableMap> linkResultConverter;
    private final DataProcessor<WritableMap> errorHandler;
    private Callback callback;

    public CallbackActivityEventListener(DataConverter<Object, WritableMap> linkResultConverter,
                                         DataProcessor<WritableMap> errorHandler) {
        this.linkResultConverter = linkResultConverter;
        this.errorHandler = errorHandler;
    }

    @Override
    public void onActivityResult(Activity activity,
                                 int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(activity, requestCode, resultCode, data);
        if (requestCode == Fidel.FIDEL_LINK_CARD_REQUEST_CODE) {
            LinkResult result = data.getParcelableExtra(Fidel.FIDEL_LINK_CARD_RESULT_CARD);
            LinkResultError error = result.getError();
            if (error == null) {
                Log.i("i","In onActivityResult, result is: " + result);
                WritableMap convertedLinkResult = linkResultConverter.getConvertedDataFor(result);
                callback.invoke(null, convertedLinkResult);
            }
            else {
                WritableMap convertedError = linkResultConverter.getConvertedDataFor(error);
                errorHandler.process(convertedError);
            }
        }
    }

    @Override
    public void callbackIsReady(Callback callback) {
        this.callback = callback;
    }
}
