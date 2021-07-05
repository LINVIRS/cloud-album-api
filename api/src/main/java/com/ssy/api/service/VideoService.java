package com.ssy.api.service;


import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.vo.VideoVO;
import com.ssy.api.result.RestResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     *
     * @param inputVideo
     * @param inputMusic
     * @return
     */
    RestResult convertorWithBgmNoOriginCommon(MultipartFile inputVideo, MultipartFile inputMusic);

    /**
     * 视频合并音频，给视频加上背景音乐，并不保留视频原声,传网络视频
     *
     * @param inputVideo
     * @param inputMusic
     * @return
     */
    RestResult convertorWithBgmNoOriginCommon(String inputVideo, MultipartFile inputMusic);

    /**
     * 图片生成视频
     *
     * @param multipartFile
     * @return
     */
    RestResult pictureToVideo(MultipartFile[] multipartFile);

    /**
     * 图片生成视频
     *
     * @param videoVO
     * @return
     */
    RestResult pictureToVideo(VideoVO videoVO);


    /**
     * 分页查询所有视频
     *
     * @param pageDto
     * @return
     */
    RestResult findAll(PageDto pageDto);
}
