package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserDataDto @Description: 个人资料修改 @Author: WangLinLIN @Date:
 * 2021/04/07 10:28:11  @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataDto {
    private int id;
    // 账户
    private String account;
    // 性别
    private int sex;
    // 昵称
    private String nickName;
    // 头像
    private String avatar;
    // 地址
    private String address;
}
