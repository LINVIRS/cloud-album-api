package com.ssy.api.util;

import com.ssy.api.exception.ApiException;
import com.ssy.api.exception.IncompleteParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/** */
@Slf4j
public class CheckParamsUtil {

  /**
   * 判断参数是否为空
   *
   * @param params
   */
  public static void isNotBlank(String... params) {
    for (String param : params) {
      if (StringUtils.isBlank(param) || "null".equals(param)) {
        try {
          throw new IncompleteParamException();
        } catch (IncompleteParamException e) {
          log.info("参数判断异常");
        }
      }
    }
  }

  /**
   * 判断整型参数是否为空
   *
   * @param params
   */
  public static void numIsBlank(Integer... params) {
    for (Integer param : params) {
      if (param == null) {
        try {
          throw new IncompleteParamException();
        } catch (IncompleteParamException e) {
          log.info("参数判断异常");
        }
      }
    }
  }

  /**
   * 校验手机号
   *
   * @param phone
   */
  public static void checkPhone(String phone) throws ApiException {
    if (StringUtils.isBlank(phone) || phone.length() != 11 || phone.indexOf("1") != 0) {
      throw new ApiException("手机号格式有误");
    }
  }

  /**
   * 判断字符串中是否含有表情
   *
   * @param source
   * @return
   */
  public static boolean containsEmoji(String source) {
    int len = source.length();
    boolean isEmoji = false;
    for (int i = 0; i < len; i++) {
      char hs = source.charAt(i);
      if (0xd800 <= hs && hs <= 0xdbff) {
        if (source.length() > 1) {
          char ls = source.charAt(i + 1);
          int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
          if (0x1d000 <= uc && uc <= 0x1f77f) {
            return true;
          }
        }
      } else {
        // non surrogate
        if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
          return true;
        } else if (0x2B05 <= hs && hs <= 0x2b07) {
          return true;
        } else if (0x2934 <= hs && hs <= 0x2935) {
          return true;
        } else if (0x3297 <= hs && hs <= 0x3299) {
          return true;
        } else if (hs == 0xa9
            || hs == 0xae
            || hs == 0x303d
            || hs == 0x3030
            || hs == 0x2b55
            || hs == 0x2b1c
            || hs == 0x2b1b
            || hs == 0x2b50
            || hs == 0x231a) {
          return true;
        }
        if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
          char ls = source.charAt(i + 1);
          if (ls == 0x20e3) {
            return true;
          }
        }
      }
    }
    return isEmoji;
  }

  /**
   * 参数校验
   *
   * @param publicDto
   * @return
   */

  //    public static void CheckParams(PublicDto publicDto) {
  //        try {
  //        if (StringUtil.isEmpty(publicDto.getToken())) {
  //
  //                throw new CheckParamException("Token不能为空");
  //
  //        }
  //        if (StringUtil.isEmpty(publicDto.getDeviceToken())) {
  //            throw new CheckParamException("DeviceToken不能为空");
  //        }
  //        if (StringUtil.isEmpty(publicDto.getClientType())) {
  //            throw new CheckParamException("ClietType不能为空");
  //        }
  //        if (StringUtil.isEmpty(publicDto.getApiVer())) {
  //            throw new CheckParamException("ApiVer不能为空");
  //        }
  //        if (StringUtil.isEmpty(publicDto.getClientVer())) {
  //            throw new CheckParamException("ClientVer不能为空");
  //        }
  //
  //        if (StringUtil.isEmpty(publicDto.getTimestamp())) {
  //            throw new CheckParamException("Timestamp不能为空");
  //        }
  //
  //        } catch (CheckParamException e) {
  //            e.printStackTrace();
  //        }
  //
  //    }
}
