package com.ssy.api.service;

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
     *
     * @param multipartFile
     * @return
     */
    RestResult convertorWithBgmNoOriginCommon(MultipartFile multipartFile);
}
