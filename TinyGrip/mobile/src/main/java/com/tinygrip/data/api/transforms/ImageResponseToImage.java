package com.tinygrip.data.api.transforms;

import com.tinygrip.data.api.model.response.Image;
import com.tinygrip.data.api.model.response.ImageResponse;

import rx.functions.Func1;

public class ImageResponseToImage implements Func1<ImageResponse, Image> {
    @Override
    public Image call(ImageResponse imageResponse) {
        return imageResponse.data;
    }
}
