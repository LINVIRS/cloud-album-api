package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.entity.Identify;
import com.ssy.api.SQLservice.entity.Photo;

import java.util.List;

/**
 * @interface: IdentifyDao
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 16:57:56 
 * @Version: V1.0
 **/
public interface IdentifyDao {


    /**
     * 根据类型查询照片
     * @param userId
     * @param type
     * @return
     */

    List<Photo> searchUserPicture(Integer userId, String type,Integer pageSize,Integer pageIndex);


    /**
     * 查询所有图片分类类型
     * @param userId
     * @return
     */
   List<String> findPictureType (Integer userId);
}
