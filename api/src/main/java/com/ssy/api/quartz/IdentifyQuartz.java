package com.ssy.api.quartz;

import com.ssy.api.SQLservice.repository.UserRepository;
import com.ssy.api.service.IdentifyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: Identify
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 16:36:58 
 * @Version: V1.0
 **/
@Component
public class IdentifyQuartz {

    @Resource
    private UserRepository userRepository;
    @Resource
    private IdentifyService identifyService;

    // 每30分钟执行一次 定时器  执行分类操作
    @PostConstruct
    @Scheduled(cron = "0 0/3 * * * ? ")
    public void execute() {
        //查询所有userid
        List<Integer> allId = userRepository.findAllId();
        allId.stream().forEach(i -> {
            identifyService.identifyPicture(i);
        });
    }
}
