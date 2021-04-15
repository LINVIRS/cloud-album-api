package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.UdRecordDao;
import com.ssy.api.SQLservice.entity.UdRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UdRecordRepository extends JpaRepository<UdRecord, Integer>, UdRecordDao {
}
