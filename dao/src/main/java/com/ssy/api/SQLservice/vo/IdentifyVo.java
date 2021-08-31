package com.ssy.api.SQLservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: IdentifyVo
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/05 09:51:12 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentifyVo {
    private Integer userId;
    private   String content;
    private  Integer pageSize;
    private  Integer pageIndex;
}
