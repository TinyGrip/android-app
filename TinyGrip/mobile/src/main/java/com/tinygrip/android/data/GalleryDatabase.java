package com.tinygrip.android.data;

import com.tinygrip.android.data.api.GalleryService;
import com.tinygrip.android.data.api.model.request.Section;
import com.tinygrip.android.data.api.model.request.Sort;
import com.tinygrip.android.data.api.model.response.Image;
import com.tinygrip.android.data.api.transforms.GalleryToImageList;
import com.tinygrip.android.data.rx.EndObserver;
import com.tinygrip.android.ui.ApplicationScope;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Poor-man's in-memory cache of responses. Must be accessed on the main thread.
 */
@ApplicationScope
public class GalleryDatabase {
    private final GalleryService galleryService;

    private final Map<Section, List<Image>> galleryCache = new LinkedHashMap<>();
    private final Map<Section, PublishSubject<List<Image>>> galleryRequests = new LinkedHashMap<>();

    @Inject
    public GalleryDatabase(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    // TODO pull underlying logic into a re-usable component for debouncing and caching last value.
    public Subscription loadGallery(final Section section, Observer<List<Image>> observer) {
        List<Image> images = galleryCache.get(section);
        if (images != null) {
            // We have a cached value. Emit it immediately.
            observer.onNext(images);
        }

        PublishSubject<List<Image>> galleryRequest = galleryRequests.get(section);
        if (galleryRequest != null) {
            // There's an in-flight network request for this section already. Join it.
            return galleryRequest.subscribe(observer);
        }

        galleryRequest = PublishSubject.create();
        galleryRequests.put(section, galleryRequest);

        Subscription subscription = galleryRequest.subscribe(observer);

        galleryRequest.subscribe(new EndObserver<List<Image>>() {
            @Override
            public void onEnd() {
                galleryRequests.remove(section);
            }

            @Override
            public void onNext(List<Image> images) {
                galleryCache.put(section, images);
            }
        });

        // Warning: Gross shit follows! Where you at Java 8?
        galleryService.listGallery(section, Sort.VIRAL, 1)
                .map(new GalleryToImageList())
                .flatMap(new Func1<List<Image>, Observable<Image>>() {
                    @Override
                    public Observable<Image> call(List<Image> images) {
                        return Observable.from(images);
                    }
                })
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return !image.is_album; // No albums.
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(galleryRequest);

        return subscription;
    }
}