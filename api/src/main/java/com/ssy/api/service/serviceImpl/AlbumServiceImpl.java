package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.dto.QueryDto;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.repository.AlbumRepository;
import com.ssy.api.SQLservice.vo.AlbumVo;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * AlbumServiceImpl
 *
 * @author wf
 * @date 2021/4/7 15:48
 * @description
 */
@Transactional(rollbackOn = RuntimeException.class)
@Service
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumRepository albumRepository;

    @Override
    public RestResult createAlbumByUserId(AlbumDto albumDto) {
        Albums albums = new Albums();
        albums.setName(albumDto.getName());
        albums.setUserId(albumDto.getUserId());
        albums.setPhotoId("");
        albums.setPhotoNumber("0");
        albums.setCreateType(0);
        albums.setTotalCapacity(0);
        albums.setTagId(0);
        albums.setIsDelete(0);
        albums.setType(0);
        albums.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        albums.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        Albums save = albumRepository.save(albums);
        AlbumVo albumVo = AlbumVo.builder()
                .id(save.getId())
                .name(albums.getName())
                .userId(albums.getUserId())
                .photoId(albums.getPhotoId())
                .photoNumber(albums.getPhotoNumber())
                .createType(albums.getCreateType())
                .totalCapacity(albums.getTotalCapacity())
                .tagId(albums.getTagId())
                .type(albums.getType())
                .updateTime(albums.getUpdateTime())
                .createTime(albums.getCreateTime())
                .build();
        return new RestResultBuilder<>().success(albumVo);
    }

    @Override
    public RestResult modifyAlbumById(AlbumDto albumDto) {
        return new RestResultBuilder<>().success(albumRepository.updateAlbumById(albumDto, albumDto.getId()));
    }

    @Override
    public RestResult deleteAlbumById(int albumId) {
        Albums albums = albumRepository.getOne(albumId);
        System.out.println("传递过来的id是: " + albumId);
        AlbumDto albumDto = AlbumDto.builder()
                .name(albums.getName())
                .photoId(albums.getPhotoId())
                .id(albumId).isDelete(1).build();
        int n = albumRepository.updateAlbumById(albumDto, albumId);
        return new RestResultBuilder<>().success(n);
    }

    @Override
    public RestResult getAllAlbumsByUserId(QueryDto queryDto, int userId, String sortStr, PageDto pageDto) {
        return new RestResultBuilder<>().success(albumRepository.getAllAlbumsByUserId(queryDto, userId, sortStr, pageDto));
    }

    @Override
    public RestResult getAlbumDetailById(int albumId) {
        return new RestResultBuilder<>().success(albumRepository.getAlbumDetailById(albumId));
    }
}

