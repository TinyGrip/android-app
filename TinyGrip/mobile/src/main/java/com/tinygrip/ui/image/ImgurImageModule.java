package com.tinygrip.ui.image;

import android.support.annotation.NonNull;

import com.tinygrip.data.api.ImageService;
import com.tinygrip.data.api.model.response.Image;
import com.tinygrip.data.api.transforms.ImageResponseToImage;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ImgurImageModule {
    private final @NonNull String imageId;

    public ImgurImageModule(String imageId) {
        this.imageId = imageId;
    }

    @Provides
    Observable<Image> provideImageObservable(ImageService imageService) {
        return imageService.image(imageId).
                map(new ImageResponseToImage()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }
}
