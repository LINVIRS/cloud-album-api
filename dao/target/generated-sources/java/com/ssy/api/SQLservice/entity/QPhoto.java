package com.ssy.api.SQLservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoto is a Querydsl query type for Photo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhoto extends EntityPathBase<Photo> {

    private static final long serialVersionUID = 1171442424L;

    public static final QPhoto photo = new QPhoto("photo");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isDelete = createNumber("isDelete", Integer.class);

    public final NumberPath<Integer> isUpload = createNumber("isUpload", Integer.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Integer> tagId = createNumber("tagId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final StringPath url = createString("url");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QPhoto(String variable) {
        super(Photo.class, forVariable(variable));
    }

    public QPhoto(Path<? extends Photo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoto(PathMetadata metadata) {
        super(Photo.class, metadata);
    }

}

