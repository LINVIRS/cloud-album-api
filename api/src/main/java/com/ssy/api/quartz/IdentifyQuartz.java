//package com.ssy.api.quartz;
//
//import com.alibaba.fastjson.JSON;
//import com.ssy.api.SQLservice.entity.Photo;
//import com.ssy.api.SQLservice.repository.PhotoRepository;
//import com.ssy.api.SQLservice.repository.UserRepository;
//import com.ssy.api.redis.JWTRedisDAO;
//import com.ssy.api.redis.RedisService;
//import com.ssy.api.redis.constant.RedisConstant;
//import com.ssy.api.result.RestResult;
//import com.ssy.api.service.IdentifyService;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName: Identify
// * @Description: TODO
// * @Author: WangLinLIN
// * @Date: 2021/07/01 16:36:58 
// * @Version: V1.0
// **/
//@Component
//public class IdentifyQuartz {
//
//    @Resource
//    private UserRepository userRepository;
//    @Resource
//    private IdentifyService identifyService;
//    // 每30分钟执行一次 定时器  执行分类操作
////    @PostConstruct
//    @Scheduled(cron = "0 0/10 * * * ? ")
//    public void execute() {
//        System.out.println(
//                "*******************************************定时器启动***********************************");
//      //查询所有userid
//        List<Integer> allId = userRepository.findAllId();
//        allId.stream().forEach(i->{
//            identifyService.identifyPicture(i);
//        });
//        System.out.println(
//                "*******************************************定时器结束***********************************");
//    }
//}
