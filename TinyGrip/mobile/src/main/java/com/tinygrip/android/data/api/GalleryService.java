package com.tinygrip.android.data.api;

import com.tinygrip.android.data.api.model.request.Section;
import com.tinygrip.android.data.api.model.request.Sort;
import com.tinygrip.android.data.api.model.response.Gallery;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GalleryService {
    @GET("/gallery/{section}/{sort}/{page}")
    Observable<Gallery> listGallery(@Path("section") Section section,
                                    @Path("sort") Sort sort,
                                    @Path("page") int page);
}