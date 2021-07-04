package com.ssy.api.service.serviceImpl;

import com.querydsl.core.Tuple;
import com.ssy.api.SQLservice.entity.Identify;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.IdentifyRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.redis.JWTRedisDAO;
import com.ssy.api.redis.RedisConfig;
import com.ssy.api.redis.RedisService;
import com.ssy.api.redis.constant.RedisConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.IdentifyService;
import com.ssy.api.util.allPictureUtils.ECloudSignatureTest;
import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.repository.PictureRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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
    @Resource
    private PhotoRepository photoRepository;
    @Resource
    private RedisService redisService;
    @Resource
    private IdentifyRepository identifyRepository;
    @Resource
   private PictureRepository pictureRepository;
    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public RestResult identifyPicture( Integer userId) {
        Object o = redisService.get(RedisConstant.PICTURE_IDENTIFY, String.valueOf(userId));
      List<Integer> idList =new ArrayList<>();
        if (o!=null){
         Arrays.stream(o.toString().replace(" ","").split(",")).forEach(i->{
             int i1 = Integer.parseInt(i);
             idList.add(i1);
         });
        }
        List<Photo> photo = photoRepository.findPhotoNotIdentify(userId,idList);
        if (photo.size()==0){
            return  new RestResultBuilder<>().success();
        }
        photo.stream().forEach(i->{
            if (i.getUrl()!=null){
                Identify identify =new Identify();
                //获取识别结果
                String s = ECloudSignatureTest.IdentifyImages(i.getUrl());
                PictureDocument pictureDocument =PictureDocument.builder()
                        .id(i.getId())
                        .url(i.getUrl())
                        .identifyResult(s)
                        .userId(userId)
                        .createTime(String.valueOf(i.getCreateTime()))
                        .photoName(i.getPhotoName())
                        .build();
         pictureRepository.save(pictureDocument);
                if (s.isEmpty()){
                    identify.setType(null);
                    identify.setIdentifyResult(null);
                    identify.setUserId(userId);
                    identify.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                    identify.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                    identify.setUserId(userId);
                    identify.setPhotoId(i.getId());
                    identify.setIsDelete(CommonConstant.DELFlag);
                    identifyRepository.save(identify);
                }
                identify.setIdentifyResult(s);
                if (s.contains("交通")){
                    identify.setType("交通");
                }else if(s.contains("人")){
                    identify.setType("人");
                }
                else if(s.contains("建筑")){
                    identify.setType("建筑");
                }  else if(s.contains("文本")){
                    identify.setType("文本");
                } else if(s.contains("风景")){
                    identify.setType("风景");
                }
                else if(s.contains("食物")){
                    identify.setType("食物");
                }  else if(s.contains("天气")){
                    identify.setType("天气状况");
                }
                else if(s.contains("动物")){
                    identify.setType("动物");
                } else if(s.contains("植物")){
                    identify.setType("植物");
                }else {
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

        });
        List<Integer> ids = photo.stream().map(p -> p.getId()).collect(Collectors.toList());
          idList.addAll(ids);
        String idsString = idList.toString().substring(1, idList.toString().lastIndexOf("]"));
        redisService.set(RedisConstant.PICTURE_IDENTIFY, String.valueOf(userId),idsString,-1);
        return  new RestResultBuilder<>().success();
    }

    @Override
    public RestResult findPictureByType(String type,Integer userId,Integer pageSize,Integer pageIndex) {
        List<Photo> photoList = identifyRepository.searchUserPicture(userId, type, pageSize, pageIndex);
        return new RestResultBuilder<>().success(photoList);
    }

    @Override
    public RestResult findPictureType(Integer userId) {
        List<String> pictureType = identifyRepository.findPictureType(userId);
        List<Object> list =new ArrayList<>();
       pictureType.stream().forEach(i->{
           Map<String, Object> map =new HashMap<>();
           Tuple tuple = identifyRepository.findPictureCover(userId, "食物");
           map.put("type",i);
           map.put("pictureCover",tuple.get(0,String.class));
           map.put("time",tuple.get(1,String.class));
           list.add(map);
       });

        return new RestResultBuilder<>().success(list);
    }
}
