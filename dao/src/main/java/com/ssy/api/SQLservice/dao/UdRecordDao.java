package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.entity.UdRecord;

import java.util.List;

public interface UdRecordDao {

    /**
     * 获取该用户最近上传记录
     * @param userId 用户id
     * @return List
     */
    List<UdRecord> selectAll(int userId);
}
