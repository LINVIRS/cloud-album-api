package com.ssy.api.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mysql.cj.protocol.ResultBuilder;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.ShareAlbum;
import com.ssy.api.SQLservice.entity.User;
import com.ssy.api.SQLservice.repository.AlbumRepository;
import com.ssy.api.SQLservice.repository.ShareAlbumRepository;
import com.ssy.api.SQLservice.repository.UserRepository;
import com.ssy.api.SQLservice.vo.ShareAlbumVo;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.ShareAlbumService;
import com.ssy.api.util.RandomString;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ShareAlbumServiceImpl
 *
 * @author wf
 * @date 2021/4/30 15:08
 * @description
 */
@Service
@Transactional(rollbackOn = RuntimeException.class)
public class ShareAlbumServiceImpl implements ShareAlbumService {
    @Resource
    private ShareAlbumRepository shareAlbumRepository;
    @Resource
    private AlbumRepository albumRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    /**
     * 修改id 修改时间2021.7.13
     */
    public List<ShareAlbumVo> selectAllShareAlbum(int userId) {
        List<ShareAlbum> albums = shareAlbumRepository.selectAll(userId);
        List<ShareAlbumVo> shareAlbumVos = new ArrayList<>();
        if(albums.size()==0){
            return shareAlbumVos;
        }

        albums.stream().forEach(shareAlbum -> {
            User  user =new User();
             user = userRepository.findById(shareAlbum.getCreatorId()).get();
            Albums albums1 = albumRepository.findById(shareAlbum.getAlbumId()).get();
           if (albums1.getIsDelete()== CommonConstant.DELFlag&&albums1.getPhotoNumber()!=(null)){
               int photoNumber = Integer.parseInt(albums1.getPhotoNumber());
               shareAlbumVos.add(ShareAlbumVo.builder().name(albums1.getName())
                       .id(shareAlbum.getAlbumId()).keyWord(shareAlbum.getKeyWord())
                       .creator(user)
                       .updateTime(shareAlbum.getUpdateTime())
                       .createTime(shareAlbum.getCreateTime())
                       .cover(albums1.getCover())
                       .photoNumber(photoNumber).build());
           }
        });

        return shareAlbumVos;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ShareAlbumVo creatorShareAlbum(int userId, int albumId) {
        //查询改相册是否已被分享
        ShareAlbumVo shareAlbumVo = new ShareAlbumVo();
        ShareAlbum shareAlbum = new ShareAlbum();
        shareAlbum = shareAlbumRepository.searchOneByCreatorIdAndAlbumId(userId, albumId);
        Albums albums = albumRepository.findById(albumId).get();
        if(shareAlbum == null) {
             shareAlbum = shareAlbumRepository.save(ShareAlbum.builder().creatorId(userId)
                    .albumId(albumId).isDelete(0).createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .creatorId(userId)
                    .joinUserId(String.valueOf(userId))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .keyWord(RandomString.generateRandomStr(32)).build());
            shareAlbumVo = ShareAlbumVo.builder().id(shareAlbum.getId()).creator(userRepository.findById(userId).get())
                    .createTime(shareAlbum.getCreateTime()).updateTime(shareAlbum.getUpdateTime())
                    .cover(albums.getCover()).name(albums.getName()).photoNumber(Integer.parseInt(albums.getPhotoNumber()))
                    .keyWord(shareAlbum.getKeyWord()).build();
            return shareAlbumVo;
        }
        shareAlbumVo = ShareAlbumVo.builder().id(shareAlbum.getId()).creator(userRepository.findById(userId).get())
                .createTime(shareAlbum.getCreateTime()).updateTime(shareAlbum.getUpdateTime())
                .cover(albums.getCover()).name(albums.getName()).photoNumber(Integer.parseInt(albums.getPhotoNumber()))
                .keyWord(shareAlbum.getKeyWord()).build();
        return shareAlbumVo;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteShareAlbum(int id) {
        ShareAlbum shareAlbum = shareAlbumRepository.getOne(id);
        shareAlbum.setIsDelete(1);
        shareAlbumRepository.saveAndFlush(shareAlbum);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RestResult joinShareAlbum(String keyWord, int joinUserId) {
        ShareAlbum shareAlbum = shareAlbumRepository.getOneByKeyWord(keyWord);
        ShareAlbum shareAlbum2 = shareAlbumRepository.searchOneByJoinUserIdAndAlbumId(joinUserId, keyWord);
        Albums albums = albumRepository.findById(shareAlbum.getAlbumId()).get();
        ShareAlbumVo shareAlbumVo = new ShareAlbumVo();
        if(shareAlbum2 != null) {
            return new RestResultBuilder<>().success("已存在");
        }
        ShareAlbum shareAlbum1 = shareAlbumRepository.saveAndFlush(
                ShareAlbum.builder()
                .id(shareAlbum.getId())
                .createTime(shareAlbum.getCreateTime())
                .updateTime(shareAlbum.getUpdateTime())
                .albumId(shareAlbum.getAlbumId())
                .keyWord(shareAlbum.getKeyWord())
                .creatorId(shareAlbum.getCreatorId())
                .joinUserId(shareAlbum.getJoinUserId() + "," + joinUserId)
                .isDelete(0)
                .build());
        shareAlbumVo = ShareAlbumVo.builder().id(shareAlbum1.getId()).name(albums.getName())
                .cover(albums.getCover())
                .photoNumber(Integer.parseInt(albums.getPhotoNumber()))
                .createTime(shareAlbum1.getCreateTime())
                .updateTime(shareAlbum1.getUpdateTime())
                .creator(userRepository.findById(shareAlbum1.getCreatorId()).get())
                .keyWord(shareAlbum1.getKeyWord())
                .build();
        return new RestResultBuilder<>().success(shareAlbumVo);
    }
}
