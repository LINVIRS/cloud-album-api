package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DownLoadFileDto
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/11 20:39:43 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownLoadFileDto {
    private String url;
    private  String fullPath;
    private String  fileName;
}
