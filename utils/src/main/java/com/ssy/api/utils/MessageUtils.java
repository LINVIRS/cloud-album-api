package com.ssy.api.utils;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
/**
 * @ClassName: MessageUtils
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/14 11:07:16 
 * @Version: V1.0
 **/
public class MessageUtils {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public  static void sendMessage(String phoneNumber,String code){

        com.aliyun.dysmsapi20170525.Client client = null;
        try {
            client = MessageUtils.createClient("LTAI4FpzLFy8uA2PWAXH8cwQ", "XLTomRADcglUJ5IgRrHxWKJMjPqg8b");
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phoneNumber)
                    .setSignName("蜂王")
                    .setTemplateCode("SMS_179226026")
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

