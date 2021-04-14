package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.entity.UserLike;
import com.ssy.api.SQLservice.repository.UserLikeRepository;
import com.ssy.api.service.UserLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserLikeServiceImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/01 09:23:36 
 * @Version: V1.0
 **/
@Service
public class UserLikeServiceImpl implements UserLikeService {
    @Resource
    private UserLikeRepository userLikeRepository;
    @Override
    public List<UserLike> findAll() {
        return userLikeRepository.findAllUser();
    }
}
