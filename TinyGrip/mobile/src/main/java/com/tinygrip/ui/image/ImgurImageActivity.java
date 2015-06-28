package com.tinygrip.ui.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.tinygrip.R;
import com.tinygrip.TinyGripComponent;
import com.tinygrip.base.HasComponent;
import com.tinygrip.base.mvp.BaseActivity;
import com.tinygrip.base.mvp.BasePresenter;
import com.tinygrip.base.navigation.activity.ActivityScreen;
import com.tinygrip.data.api.model.response.Image;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Bind;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class ImgurImageActivity extends BaseActivity implements HasComponent<ImgurImageComponent> {

    @Inject Presenter presenter;

    @Bind(R.id.imgur_image_view)
    ImgurImageView view;

    private String imageId;
    private ImgurImageComponent imgurImageComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityScreen.setTransitionView(this.view);
    }

    @Override
    protected void onCreateComponent(TinyGripComponent tinyGripComponent) {
        imgurImageComponent = DaggerImgurImageComponent.builder().
                tinyGripComponent(tinyGripComponent).
                imgurImageModule(new ImgurImageModule(imageId)).build();
        imgurImageComponent.inject(this);
    }

    @Override
    protected void onExtractParams(@NonNull Bundle params) {
        super.onExtractParams(params);
        imageId = getIntent().getStringExtra(Screen.BF_IMAGE_ID);
    }

    @Override
    protected void onDestroy() {
        imgurImageComponent = null;
        super.onDestroy();
    }

    @Override
    protected int viewId() {
        return R.id.imgur_image_view;
    }

    @Override
    protected int layoutId() {
        return R.layout.imgur_image_view;
    }

    @Override
    protected BasePresenter<? extends View> presenter() {
        return presenter;
    }


    @Override
    public ImgurImageComponent getComponent() {
        return imgurImageComponent;
    }

    @ImgurImageScope
    public static class Presenter extends BasePresenter<ImgurImageView> {

        private final Observable<Image> imageObservable;
        private Subscription subscription;

        @Inject
        public Presenter(Observable<Image> imageObservable) {
            this.imageObservable = imageObservable;
        }

        @Override
        protected void onLoad() {
            super.onLoad();
            Timber.d("Loading image");
            getView().showLoading();
            subscription = imageObservable.
                subscribe(
                    new Action1<Image>() {
                        @Override
                        public void call(Image image) {
                            Timber.d("Image loaded with id %s", image.toString());
                            getView().bindTo(image);
                            getView().showContent();
                        }
                    },
                    new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Timber.e(throwable, "Image loading error");
                        }
                    }
                );
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            subscription.unsubscribe();
        }
    }

    public static class Screen extends ActivityScreen {

        private static final String BF_IMAGE_ID = "ImgurImageActivity.imageId";

        private final String imageId;

        public Screen(String imageId) {
            this.imageId = imageId;
        }

        @Override
        protected void configureIntent(@NonNull Intent intent) {
            intent.putExtra(BF_IMAGE_ID, imageId);
        }

        @Override
        protected Class<? extends Activity> activityClass() {
            return ImgurImageActivity.class;
        }
    }
}
