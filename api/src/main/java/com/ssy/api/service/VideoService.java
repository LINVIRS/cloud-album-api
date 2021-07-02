package com.ssy.api.service;


import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.result.RestResult;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    /**
     * 合并视频
     *
     * @param multipartFiles
     * @return
     */
    RestResult mergeVideo(MultipartFile[] multipartFiles);

    /**
     * 视频合并音频，给视频加上背景音乐，并不保留视频原声
<<<<<<< HEAD
=======
     *
>>>>>>> d6f4878fad3e115c4975102b2a23b04e56c02974
     * @param inputVideo
     * @param inputMusic
     * @return
     */
    RestResult convertorWithBgmNoOriginCommon(MultipartFile inputVideo, MultipartFile inputMusic);


    /**
     * 图片生成视频
     *
     * @param multipartFile
     * @return
     */
    RestResult pictureToVideo(MultipartFile[] multipartFile);


    /**
     * 分页查询所有视频
     *
     * @param pageDto
     * @return
     */
    RestResult findAll(PageDto pageDto);
}
