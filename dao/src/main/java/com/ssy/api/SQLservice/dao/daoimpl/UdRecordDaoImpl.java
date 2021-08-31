package com.ssy.api.SQLservice.dao.daoimpl;

import com.ssy.api.SQLservice.dao.UdRecordDao;
import com.ssy.api.SQLservice.entity.QUdRecord;
import com.ssy.api.SQLservice.entity.UdRecord;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class UdRecordDaoImpl extends BaseService implements UdRecordDao {

    @Override
    public List<UdRecord> selectAll(int userId) {
        QUdRecord qUdRecord = QUdRecord.udRecord;
        System.out.println("获取的前一周的日期是: " + Timestamp.valueOf(LocalDateTime.now().plusDays(-7)));
        System.out.println("获取到的当前的日期是: " + Timestamp.valueOf(LocalDateTime.now()));
        return queryFactory.select(qUdRecord).from(qUdRecord)
                .where(qUdRecord.userId.eq(userId).and(qUdRecord.isDelete.eq(CommonConstant.DELFlag))
                .and(qUdRecord.updateTime.between(Timestamp.valueOf(LocalDateTime.now().plusDays(-7)), Timestamp.valueOf(LocalDateTime.now()))))
                .orderBy(qUdRecord.createTime.asc()).fetch();
    }
}
