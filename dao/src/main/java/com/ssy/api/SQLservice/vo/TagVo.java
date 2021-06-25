package com.ssy.api.SQLservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName: TagVo
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/26 17:13:23 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagVo {
    private int id;
    private int userId;
    private  String name;
    private  String description;
    private Timestamp createTime;
}
