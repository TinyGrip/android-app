package com.tinygrip.android.data.entity.mapper;

import android.support.annotation.Nullable;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.LocationEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.Location;
import com.tinygrip.android.domain.model.area.PreviewArea;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform any related Area entities into Domain layer models
 */
@Singleton
public class AreaEntityDataMapper {

    private final LinkEntityDataMapper linkEntityDataMapper;

    @Inject
    public AreaEntityDataMapper(LinkEntityDataMapper linkEntityDataMapper) {
        this.linkEntityDataMapper = linkEntityDataMapper;
    }

    /**
     * Transform a {@link DataPageEntity<PreviewArea>} into an {@link DataPage<PreviewArea>}.
     *
     * @param previewAreasDataPageEntity Object to be transformed.
     * @return {@link DataPage<PreviewArea>} if valid {@link DataPageEntity<PreviewArea>} otherwise null.
     */
    public DataPage<PreviewArea> transform(DataPageEntity<PreviewAreaEntity> previewAreasDataPageEntity) {
        DataPage<PreviewArea> previewAreaDataPage = null;
        if (previewAreasDataPageEntity != null) {
            previewAreaDataPage = new DataPage<>(previewAreasDataPageEntity.getTotalAmount(),
                                                 this.transform(previewAreasDataPageEntity.getItems()));
            previewAreaDataPage.setNext(this.linkEntityDataMapper.transform(previewAreasDataPageEntity.getNext()));
            previewAreaDataPage.setPrev(this.linkEntityDataMapper.transform(previewAreasDataPageEntity.getPrev()));
        }

        return previewAreaDataPage;
    }

    public Collection<PreviewArea> transform(Collection<PreviewAreaEntity> entityList) {
        Collection<PreviewArea> collection = new ArrayList<>();
        if (entityList != null) {
            for (PreviewAreaEntity entity : entityList) {
                collection.add(this.transform(entity));
            }
        }

        return collection;
    }

    @Nullable
    public PreviewArea transform(PreviewAreaEntity entity) {
        PreviewArea area = null;
        if (entity != null) {
            area = new PreviewArea(entity.getName(),
                                   this.transform(entity.getLocation()),
                                   this.linkEntityDataMapper.transform(entity.getSelf()));
            area.setRating(entity.getRating());
        }

        return area;
    }

    @Nullable
    public Location transform(LocationEntity entity) {
        Location location = null;
        if (entity != null) {
            location = new Location(entity.getLatitude(), entity.getLongitude());
        }

        return location;
    }
}
