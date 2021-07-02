package com.ssy.api.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @Author gaohaojie
 * @Date 2020/2/24
 * @Des:
 */
public class BaseService {
    /**
     * 查询工厂实体
     */
    protected JPAQueryFactory queryFactory;

    @Autowired
    protected EntityManager entityManager;

    /**
     * 实例化控制器完成后执行该方法实例化JPAQueryFactory
     */
    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
