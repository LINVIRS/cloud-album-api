package com.ssy.api.SQLservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserVo
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:40:00 
 * @Version: V1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private  String phone;
    private  int sex;
    private  String IDCard;
    private  int level;
    private String account;
    private String nickname;
    private String avatar;
    private  String address;
    private String token;
}
