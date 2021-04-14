package com.ssy.api.utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ssy.api.constant.ParameterConstant;

/**
 * Created by kevin
 */
public class SendMsgUtil {

    /**
     *
     * @param phone 手机号
     * @param smsType 模板
     * @param msg 消息内容json
     * @return
     */
    public static int sendMsg(String phone, String smsType, String msg){
        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ParameterConstant.APPKEY, ParameterConstant.SECRET);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("江苏中医在线");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsType);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(msg);
        // 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", ParameterConstant.SMS_URL);
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            // e.printStackTrace();
        }
        if(null != sendSmsResponse.getCode() && sendSmsResponse.getCode().equals("OK")) {
            return 1;
        }
        return  0;
    }

}
