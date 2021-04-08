package com.ssy.api.SQLservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUdRecord is a Querydsl query type for UdRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUdRecord extends EntityPathBase<UdRecord> {

    private static final long serialVersionUID = 1856697658L;

    public static final QUdRecord udRecord = new QUdRecord("udRecord");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> isDelete = createNumber("isDelete", Integer.class);

    public final StringPath photoId = createString("photoId");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUdRecord(String variable) {
        super(UdRecord.class, forVariable(variable));
    }

    public QUdRecord(Path<? extends UdRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUdRecord(PathMetadata metadata) {
        super(UdRecord.class, metadata);
    }

}

