package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.AlbumQueryDto;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.AlbumRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.SQLservice.vo.AlbumVo;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private PhotoRepository photoRepository;

    @Override
    @Transactional
    public RestResult addPhotoTOAlbum(List<Integer> ids, Integer AlbumId) {
        Albums albums = albumRepository.findById(AlbumId).get();
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> integers = ids.subList(1, ids.size());
        for (Integer id : integers) {
            stringBuilder.append(id).append(",");
        }
        String s = albums.getPhotoId() + "," + stringBuilder.substring(0, stringBuilder.length() - 1);
        albums.setPhotoId(s);
        albumRepository.saveAndFlush(albums);
        return new RestResultBuilder<>().success("成功");
    }

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
        albums.setCover(albumDto.getCover());
        albums.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        albums.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        Albums save = albumRepository.save(albums);
        AlbumVo albumVo = AlbumVo.builder()
                .id(save.getId())
                .name(albums.getName())
                .userId(albums.getUserId())
                .cover(albums.getCover())
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
        System.out.println(albums);
        AlbumDto albumDto = AlbumDto.builder()
                .name(albums.getName())
                .photoId(albums.getPhotoId())
                .id(albumId).isDelete(1).build();
        int n = albumRepository.updateAlbumById(albumDto, albumId);
        return new RestResultBuilder<>().success(n);
    }

    @Override
    public RestResult getAllAlbumsByUserId(AlbumQueryDto dto) {
        List<Albums> albums = albumRepository.getAllAlbumsByUserId(dto);
        System.out.println(albums.get(0));
        return new RestResultBuilder<>().success(albums);
    }

    @Override
    public RestResult getAlbumDetailById(int albumId) {
        Albums albums = albumRepository.getAlbumDetailById(albumId);
        if (albums == null) {
            return new RestResultBuilder<>().success("数据不存在");
        }
        Map<String, Object> albumInfo = new HashMap<>();
        int photoNumber = 0;
        String[] photoIds = albums.getPhotoId().split(",");
        List<Photo> photoList = new ArrayList<>();

        if(albums.getPhotoId().length() > 0 && albums.getPhotoId() != null) {
            System.out.println("获取到的图片id是: " + albums.getPhotoId());
            for(String id : photoIds) {
                Photo photo = photoRepository.findDetailById(Integer.parseInt(id));
                photoList.add(photo);
            }
            photoNumber = photoIds.length;
        }
        Map<String, List<Photo>> resultMap = new HashMap<>();
        for (Photo photo : photoList) {
            if(photo == null) {
                continue;
            }
            String time = photo.getCreateTime().toString().substring(0, 11);
            if (resultMap.containsKey(time)) {
                resultMap.get(time).add(photo);
            } else {
                List<Photo> photoList1 = new ArrayList<Photo>();
                photoList1.add(photo);
                resultMap.put(time, photoList1);
            }
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Photo photo : photoList) {
            if(photo == null) {
                continue;
            }
            String time = photo.getCreateTime().toString().substring(0, 11);
            List<Photo> photoList1 = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            if (resultMap.containsKey(time)) {
                map.put("date", time);
                photoList1.addAll(resultMap.get(time));
                if (photoList1.get(0).getUrl() != null) {
                    map.put("list", photoList1);
                    resultList.add(map);
                }
            }
        }
        albumInfo.put("photoList", resultList);
        albumInfo.put("photoNumber", photoNumber);
        albumInfo.put("createTime", albums.getCreateTime());
        albumInfo.put("name", albums.getName());
        albumInfo.put("albumId", albums.getId());
        albumInfo.put("photoId", albums.getPhotoId());
        return new RestResultBuilder<>().success(albumInfo);
    }
}

