package com.ssy.api.SQLservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlbums is a Querydsl query type for Albums
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAlbums extends EntityPathBase<Albums> {

    private static final long serialVersionUID = 1528847326L;

    public static final QAlbums albums = new QAlbums("albums");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> createType = createNumber("createType", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isDelete = createNumber("isDelete", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath photoId = createString("photoId");

    public final StringPath photoNumber = createString("photoNumber");

    public final NumberPath<Integer> tagId = createNumber("tagId", Integer.class);

    public final NumberPath<Integer> totalCapacity = createNumber("totalCapacity", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QAlbums(String variable) {
        super(Albums.class, forVariable(variable));
    }

    public QAlbums(Path<? extends Albums> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlbums(PathMetadata metadata) {
        super(Albums.class, metadata);
    }

}

