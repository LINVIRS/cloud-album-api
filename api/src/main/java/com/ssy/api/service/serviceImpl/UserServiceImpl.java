package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.UserDataDto;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.entity.User;
import com.ssy.api.SQLservice.repository.UserRepository;
import com.ssy.api.SQLservice.vo.UserTokenVO;
import com.ssy.api.SQLservice.vo.UserVo;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.enums.PasswordConstant;
import com.ssy.api.redis.CodeRedisDao;
import com.ssy.api.redis.TokenRedisDao;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.result.ResultCode;
import com.ssy.api.service.UserService;
import com.ssy.api.utils.JWTUtils;
import com.ssy.api.utils.MD5Util;
import com.ssy.api.utils.SHA256Util;
import com.ssy.api.utils.SnowflakeIdWorker;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 18:10:00 
 * @Version: V1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private TokenRedisDao tokenRedisDao;
@Resource
private CodeRedisDao codeRedisDao;
    @Override
    @Transactional(rollbackFor = Exception.class)

    public RestResult UseRegistered(UserDto userDto) {

        //查找用户
        User userByPhone = userRepository.findUserByPhone(userDto.getPhoneNumber());
        if (userByPhone!=null){
            return new RestResultBuilder<>().error("用户手机号已经存在 请登录");
        }
        //判断验证码
        // 从redis中获取code 与用户输入的对比 (暂时未接入短信服务 所以以假数据代替)
        codeRedisDao.saveUserCode(
                ParameterConstant.SMS_CODE_REGISTER_TYPE, userDto.getPhoneNumber(), "1234");
        String userCode =
                codeRedisDao.getUserCode(
                        ParameterConstant.SMS_CODE_REGISTER_TYPE, userDto.getPhoneNumber());
        if (userCode==null){
            return new RestResultBuilder<>().error("验证码失效,请重新申请");
        }
        if (userDto.getVerificationCode().equals(userCode)){
            User user =new User();
            //生产随机盐
            SnowflakeIdWorker snowflakeIdWorker =new SnowflakeIdWorker(0,0);
            long random = snowflakeIdWorker.nextId();
            user.setPhone(userDto.getPhoneNumber());
            user.setPassword(SHA256Util.SHA256(userDto.getPassword(),MD5Util.MD5Encode(String.valueOf(random),"UTF-8")));
            user.setAccount(userDto.getAccount());
            user.setSlat(MD5Util.MD5Encode(String.valueOf(random),"UTF-8"));
            user.setSex(2);
            user.setIdCard("");
            user.setNickname("默认用户");
            user.setLevel(0);
            user.setIsDelete(CommonConstant.DELFlag);
            user.setAddress("0000");
            user.setAvatar("http://img01.yohoboys.com/contentimg/2018/11/22/13/0187be5a52edcdc999f749b9e24c7815fb.jpg");
            user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            User save = userRepository.save(user);
            // token
            UserTokenVO userTokenVO = new UserTokenVO(user.getId());
            String token = JWTUtils.generateToken(ParameterConstant.JWT_SECRET, userTokenVO.toMap());
            // 前缀为盐 值
            tokenRedisDao.saveToken(ParameterConstant.WX_TOKEN_PREFIX, user.getId(), token);
            UserVo userVo = UserVo.builder().avatar(user.getAvatar())
                    .address(user.getAddress()).id(user.getId())
                    .nickname(user.getNickname()).phone(user.getPhone())
                    .sex(user.getSex()).token(token).IDCard(user.getIdCard()).level(user.getLevel()).account(user.getAccount())
                    .build();
            return new RestResultBuilder<>().success(userVo);
        }
        return new RestResultBuilder<>().error("验证码错误");
    }

    @Override
    public RestResult UserLogin(UserDto userDto) {
        //手机号验证码登录
       if (userDto.getStatus()==CommonConstant.PHONE_AND_VERIFICATION){
           User user = userRepository.findUserByPhone(userDto.getPhoneNumber());
           if (user==null){
               return new RestResultBuilder<>().error("用户不存在,请注册");
           }
           // 从redis中获取code 与用户输入的对比 (暂时未接入短信服务 所以以假数据代替)
           codeRedisDao.saveUserCode(
                   ParameterConstant.SMS_CODE_USER_LOGIN_TYPE, userDto.getPhoneNumber(), "1234");
           String userCode =
                   codeRedisDao.getUserCode(
                           ParameterConstant.SMS_CODE_USER_LOGIN_TYPE, userDto.getPhoneNumber());
           if (userCode==null){
               return new RestResultBuilder<>().error("验证码失效,请重新申请");
           }
           if (userDto.getVerificationCode().equals(userCode)){
               // token
               UserTokenVO userTokenVO = new UserTokenVO(user.getId());
               String token = JWTUtils.generateToken(ParameterConstant.JWT_SECRET, userTokenVO.toMap());
               // 前缀为盐 值
               tokenRedisDao.saveToken(ParameterConstant.WX_TOKEN_PREFIX, user.getId(), token);
               UserVo userVo = UserVo.builder().avatar(user.getAvatar())
                       .address(user.getAddress()).id(user.getId())
                       .nickname(user.getNickname()).phone(user.getPhone())
                       .sex(user.getSex()).token(token).IDCard(user.getIdCard()).level(user.getLevel()).account(user.getAccount())
                       .build();
               return new RestResultBuilder<>().success(userVo);
           }else {
               return new RestResultBuilder<>().error("验证码错误,请输入正确验证码");
           }
       }else if(userDto.getStatus()==CommonConstant.PHONE_AND_PASSWORD){
          //手机号或者密码登录
           User user = userRepository.userLogin(userDto);
           if (user==null){
               return  new RestResultBuilder<>().error("账户不存在,请注册");
           }else {
               //密码比对一致
               if (user.getPassword().equals(SHA256Util.SHA256(userDto.getPassword(), user.getSlat()))){
                   // token
                   UserTokenVO userTokenVO = new UserTokenVO(user.getId());
                   String token = JWTUtils.generateToken(ParameterConstant.JWT_SECRET, userTokenVO.toMap());
                   // 前缀为盐 值
                   tokenRedisDao.saveToken(ParameterConstant.WX_TOKEN_PREFIX, user.getId(), token);
                   UserVo userVo = UserVo.builder().avatar(user.getAvatar())
                           .address(user.getAddress()).id(user.getId())
                           .nickname(user.getNickname()).phone(user.getPhone())
                           .sex(user.getSex()).token(token).IDCard(user.getIdCard()).level(user.getLevel()).account(user.getAccount())
                           .build();
                   return new RestResultBuilder<>().success(userVo);
               }
           }
       }
        return null;
    }


    @Override
    public RestResult UserAcountCheck(String account) {
        User userByAccount = userRepository.findUserByAccount(account);
        if (userByAccount!=null){
            //用户已经存在
            return new RestResultBuilder<>().failure(ResultCode.ACCOUNT_EXIST);
        }
        return new RestResultBuilder<>().success("账户可以使用");
    }

    @Override
    public UserVo findUserData(int userId) {
      return  userRepository.findUserData(userId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateUserData(UserDataDto userDataDto) {
        return userRepository.updateUserData(userDataDto);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RestResult UserLogout(int userId) {
        //暂时执行清空 用户token操操
        tokenRedisDao.removeToken(ParameterConstant.WX_TOKEN_PREFIX,userId);
        return new  RestResultBuilder<>().success();
    }
}
