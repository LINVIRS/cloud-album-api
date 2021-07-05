package com.ssy.api.service;

import com.ssy.api.SQLservice.vo.IdentifyVo;
import com.ssy.api.result.RestResult;

/**
 * @interface: IdentifyService
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 17:06:56 
 * @Version: V1.0
 **/
public interface IdentifyService {
    /**
     * 识别图片并且往识别表里添加数据
     * @return
     */
    RestResult identifyPicture(Integer userId);


    /**
     * 根据分类查询照片
     * @param type
     * @return
     */
    RestResult findPictureByType( IdentifyVo identifyVo );


    /**
     * 查询所有类型
     * @param userId
     * @return
     */
    RestResult findPictureType(Integer userId);


    /**
     * 查询某个分类所有照片Url
     * @param userId
     * @return
     */
    RestResult findTypePicture(Integer userId);
}
