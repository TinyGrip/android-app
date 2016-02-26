
package com.tinygrip.android.domain.interactor;

import rx.Subscriber;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }
}
