package com.ssy.api.SQLservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLike is a Querydsl query type for UserLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLike extends EntityPathBase<UserLike> {

    private static final long serialVersionUID = -1455347844L;

    public static final QUserLike userLike = new QUserLike("userLike");

    public final NumberPath<Integer> contentId = createNumber("contentId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Byte> delFlag = createNumber("delFlag", Byte.class);

    public final NumberPath<Integer> pkId = createNumber("pkId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserLike(String variable) {
        super(UserLike.class, forVariable(variable));
    }

    public QUserLike(Path<? extends UserLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLike(PathMetadata metadata) {
        super(UserLike.class, metadata);
    }

}

