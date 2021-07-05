package com.ssy.api.service.serviceImpl;

import com.querydsl.core.Tuple;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.Identify;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.Video;
import com.ssy.api.SQLservice.repository.AlbumRepository;
import com.ssy.api.SQLservice.repository.IdentifyRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.SQLservice.repository.VideoRepository;
import com.ssy.api.SQLservice.vo.IdentifyVo;
import com.ssy.api.SQLservice.vo.PhotoClassification;
import com.ssy.api.SQLservice.vo.VideoVO;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.redis.RedisService;
import com.ssy.api.redis.constant.RedisConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.IdentifyService;
import com.ssy.api.service.VideoService;
import com.ssy.api.util.allPictureUtils.ECloudSignatureTest;
import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.repository.PictureRepository;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: IdentifyServiceImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 17:07:04 
 * @Version: V1.0
 **/
@Service
public class IdentifyServiceImpl implements IdentifyService {
    String[] musicPath = {"/Users/yy/Music/智能云相册/俱乐部.mp3", "/Users/yy/Music/智能云相册/振奋.mp3",
            "/Users/yy/Music/智能云相册/浪潮.mp3", "/Users/yy/Music/智能云相册/欢乐.mp3",
            "/Users/yy/Music/智能云相册/优雅.mp3", "/Users/yy/Music/智能云相册/宁静.mp3",
            "/Users/yy/Music/智能云相册/舒适.mp3", "/Users/yy/Music/智能云相册/庄重.mp3",
            "/Users/yy/Music/智能云相册/恬静.mp3", "/Users/yy/Music/智能云相册/the tumbled sea - Ø.mp3",
            "/Users/yy/Music/智能云相册/紧张.mp3", "/Users/yy/Music/智能云相册/bitter.mp3"};
    @Resource
    private PhotoRepository photoRepository;
    @Resource
    private RedisService redisService;
    @Resource
    private IdentifyRepository identifyRepository;
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private AlbumRepository albumRepository;
    @Resource
    private VideoService videoService;
    @Resource
    private VideoRepository videoRepository;

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public RestResult identifyPicture(Integer userId) {
        Object o = redisService.get(RedisConstant.PICTURE_IDENTIFY, String.valueOf(userId));
        List<Integer> idList = new ArrayList<>();
        if (o != null) {
            Arrays.stream(o.toString().replace(" ", "").split(",")).forEach(i -> {
                int i1 = Integer.parseInt(i);
                idList.add(i1);
            });
        }
        List<Photo> photo = photoRepository.findPhotoNotIdentify(userId, idList);
        if (photo.size() != 0) {
            photo.stream().forEach(i -> {
                if (i.getUrl() != null || !i.getUrl().isEmpty()) {
                    Identify identify = new Identify();
                    String s = "";
                    //获取识别结果
                    if (i.getUrl().substring(i.getUrl().lastIndexOf(".") + 1).equals("JPG") ||
                            i.getUrl().substring(i.getUrl().lastIndexOf(".") + 1).equals("PNG") ||
                            i.getUrl().substring(i.getUrl().lastIndexOf(".") + 1).equals("BMP")) {
                        s = ECloudSignatureTest.IdentifyImages(i.getUrl());
                    }

                    System.out.println(s);
                    if (s != null && !s.isEmpty() && !s.equals("")) {
//                    identify.setType(null);
//                    identify.setIdentifyResult(null);
//                    identify.setUserId(userId);
//                    identify.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//                    identify.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
//                    identify.setUserId(userId);
//                    identify.setPhotoId(i.getId());
//                    identify.setIsDelete(CommonConstant.DELFlag);
//                    identifyRepository.save(identify);
//                } else {
                        PictureDocument pictureDocument = PictureDocument.builder()
                                .id(i.getId())
                                .url(i.getUrl())
                                .identifyResult(s)
                                .userId(userId)
                                .createTime(String.valueOf(i.getCreateTime()))
                                .photoName(i.getPhotoName())
                                .build();
                        pictureRepository.save(pictureDocument);
                        identify.setIdentifyResult(s);
                        if (s.contains("交通")) {
                            identify.setType("交通");
                        } else if (s.contains("动物")) {
                            identify.setType("动物");
                        } else if (s.contains("建筑")) {
                            identify.setType("建筑");
                        } else if (s.contains("文本")) {
                            identify.setType("文本");
                        } else if (s.contains("风景")) {
                            identify.setType("风景");
                        } else if (s.contains("食物")) {
                            identify.setType("食物");
                        } else if (s.contains("天气")) {
                            identify.setType("天气状况");
                        } else if (s.contains("植物")) {
                            identify.setType("植物");
                        } else if (s.contains("人,")) {
                            identify.setType("人");
                        } else if (s.contains("电子,")) {
                            identify.setType("电子设备");
                        } else {
                            identify.setType("其他");
                        }
                        identify.setUserId(userId);
                        identify.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                        identify.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                        identify.setUserId(userId);
                        identify.setPhotoId(i.getId());
                        identify.setIsDelete(CommonConstant.DELFlag);
                        identifyRepository.save(identify);
                    }
                }

            });
            List<Integer> ids = photo.stream().map(p -> p.getId()).collect(Collectors.toList());
            idList.addAll(ids);
            if (idList.size() != 0) {
                String idsString = idList.toString().substring(1, idList.toString().lastIndexOf("]"));
                redisService.set(RedisConstant.PICTURE_IDENTIFY, String.valueOf(userId), idsString, -1);
            }

        }

        //进行相册创建
        //查询所有type
        List<String> pictureType = identifyRepository.findPictureType(userId);
        pictureType.forEach(i -> {
            Tuple tuple = identifyRepository.findPictureCover(userId, i);
            //相册id
            Object id = redisService.get(RedisConstant.ALBUMS_ID_LIST + ":" + userId, i);
            List<Integer> pictureId = identifyRepository.findPictureId(userId, i);
            String idsList = "";
            if (pictureId.size() != 0) {
                idsList = pictureId.toString().substring(1, pictureId.toString().lastIndexOf("]"))
                        .replace(" ", "");
            }
            //相册为空
            if (id == null) {
                Albums albums = new Albums();
                albums.setName(i);
                albums.setUserId(userId);
                albums.setPhotoId(idsList);
                albums.setPhotoNumber(String.valueOf(pictureId.size()));
                albums.setCreateType(1);
                albums.setTotalCapacity(0);
                albums.setTagId(0);
                albums.setIsDelete(0);
                albums.setType(0);
                albums.setCover(tuple.get(0, String.class));
                albums.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                albums.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                Albums save = albumRepository.save(albums);
                redisService.set(RedisConstant.ALBUMS_ID_LIST + ":" + userId, i, String.valueOf(albums.getId()), -1);
            } else {
                albumRepository.updateAlbum(Integer.parseInt((String) id), tuple.get(0, String.class),
                        idsList, String.valueOf(pictureId.size()));
            }
        });
        return new RestResultBuilder<>().success();
    }

    @Override
    public RestResult findPictureByType(IdentifyVo identifyVo) {
        List<Photo> photoList = identifyRepository.searchUserPicture(identifyVo.getUserId(), identifyVo.getContent(), identifyVo.getPageSize(), identifyVo.getPageIndex());
        Map<String, Object> albumInfo = new HashMap<>();
        Map<String, List<Photo>> collect = photoList.stream().collect(
                Collectors.groupingBy(p ->
                        p.getCreateTime().toString().substring(0, 10)
                ));
        List<PhotoClassification> list = new ArrayList<>();
        collect.entrySet().stream().forEach(i -> {
            PhotoClassification photoClassification = new PhotoClassification();
            photoClassification.setList(i.getValue());
            photoClassification.setDate(i.getKey());
            list.add(photoClassification);
        });
        return new RestResultBuilder<>().success(list);

    }

    @Override
    public RestResult findPictureType(Integer userId) {
        List<String> pictureType = identifyRepository.findPictureType(userId);
        List<Object> list = new ArrayList<>();
        pictureType.stream().forEach(i -> {
            Map<String, Object> map = new HashMap<>();
            Tuple tuple = identifyRepository.findPictureCover(userId, i);
            map.put("type", i);
            map.put("pictureCover", tuple.get(0, String.class));
            map.put("time", tuple.get(1, String.class));
            list.add(map);
        });
        return new RestResultBuilder<>().success(list);
    }

    @Override
    public RestResult findTypePicture(Integer userId) {
        //查询用户所有type
        List<String> pictureType = identifyRepository.findPictureType(userId);
        pictureType.stream().forEach(i -> {
            //取出所有照片url
            List<String> pictureUrls = identifyRepository.findPictureUrl(userId, i);
            if (pictureUrls.size() > 10) {
                pictureUrls.subList(0, 10);
            }
            //生成视频
            RestResult restResult = videoService.pictureToVideo(VideoVO.builder().list(pictureUrls).build());
            String url = restResult.getMsg();
            String video = null;
            try {
                int random_index = (int) (Math.random() * musicPath.length);
                File pdfFile = new File(musicPath[random_index]);
                FileInputStream fileInputStream = new FileInputStream(pdfFile);
                MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                // 添加背景音乐
                RestResult restResult1 = videoService.convertorWithBgmNoOriginCommon(url, multipartFile);
                video = restResult1.getMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //视频存入视频表中
            if (video != null) {
                videoRepository.save(Video.builder()
                        .cover(pictureUrls.get(0))
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .duration(0)
                        .url(video)
                        .updateTime(new Timestamp(System.currentTimeMillis()))
                        .name("")
                        .build());
            }
        });
        return null;
    }
}
