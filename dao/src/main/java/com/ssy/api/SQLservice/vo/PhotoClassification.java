package com.ssy.api.SQLservice.vo;

import com.ssy.api.SQLservice.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: PhotoClassfication
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/05/08 16:03:50 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoClassification {
private String yearName;
private List<Photo> photoList;
}
