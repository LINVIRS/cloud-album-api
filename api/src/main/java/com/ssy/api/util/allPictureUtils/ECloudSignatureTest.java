package com.ssy.api.util.allPictureUtils;

import com.chinamobile.cmss.sdk.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.ECloudServerException;
import com.chinamobile.cmss.sdk.IECloudClient;
import com.chinamobile.cmss.sdk.http.constant.*;
import com.chinamobile.cmss.sdk.http.signature.Credential;
import com.chinamobile.cmss.sdk.request.*;
import com.chinamobile.cmss.sdk.response.EngineImageCarDetectResponse;
import com.chinamobile.cmss.sdk.response.EngineImageClassifyDetectResponse;
import com.chinamobile.cmss.sdk.response.bean.EngineCar;
import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.chinamobile.cmss.sdk.util.JacksonUtil;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.util.allPictureUtils.util.ImageBase64Util;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: sa
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/06/21 10:42:16 
 * @Version: V1.0
 **/
public class ECloudSignatureTest {
    public static void main(String[] args) throws IOException {
        System.out.println(imgBase64("http://121.5.235.153:7777/group1/M00/00/04/rBEABGDd1i2AJKHfAKsJSzdQC4E383.JPG"));
//        System.out.println(countChineseWords("'[{\"classes\":\"服装,人,时尚,春暖花开的季节,植物,照片,美丽,夏天,鞋类,蓝色的,树,裙子,时尚配饰,街头时尚,女人,女孩,\"}]'"));
//        [{"classes":"建筑学,白天,建筑物,商业建筑,地标,都市区,人类住区,塔楼,界线,摩天大广告,公司总部,总公司,城市,都会,天空,市区,正面,混合使用,设计,光,房地产,基础设施,材料特性,灯光,财产,蓝色的,共管公寓,采光,对称,市中心,快照,塔,"}]
    }
    public static String IdentifyImages(String url) {
        //企业云账户：请申请
        Credential credential = new Credential(ParameterConstant.PHOTOAK, ParameterConstant.PHOTOSK);
        //初始化ECloud client,Region 为部署资源池OP网关地址枚举类，可自行扩展
        IECloudClient ecloudClient = new ECloudDefaultClient(credential, Region.POOL_SZ);
        Map<String, Integer>map =new HashMap<>();
        //待定义产品request
        try {
            //通用物品识别
            EngineImageClassifyDetectPostRequest request = new EngineImageClassifyDetectPostRequest();
            //图片base64 ，注意不要包含 {data:image/jpeg;base64,}
            //获取图片base64
            String imgBase64String = imgBase64(url);
            if (imgBase64String.equals("fail")){
                return null;
            }
            request.setImage(imgBase64String);
            request.setUserId("");
            //通用物品检测
            EngineImageClassifyDetectResponse response = ecloudClient.call(request);
            if ("OK".equals(response.getState())) {
                //通用物品检测
                List<EngineClassify> body = response.getBody();
                //关键词
                String keyString = JacksonUtil.toJson(body);
                String substring = keyString.substring(keyString.indexOf(":")+2, keyString.lastIndexOf("}")-1);
              return  substring;
            }
        } catch (IOException | ECloudServerException | IllegalAccessException e) {
            //todo exception process...
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一张网络图片转化成Base64字符串
     * @param imgURL
     * @return
     */
    public static String imgBase64(String imgURL) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            // 创建URL
            URL url = new URL(imgURL);
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "fail";//连接失败/链接失效/图片不存在
            }
            int size= conn.getContentLength();
            //超过3
            if (size/1024>3072){
                ImageBase64Util.resizeImage(imgURL);
            }
            InputStream inStream = conn.getInputStream();
            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outPut.toByteArray());
    }

public static  Map<String, Integer> countChineseWords(String wordsInput) {
    String substring = wordsInput.substring(wordsInput.indexOf(":")+2, wordsInput.lastIndexOf("}")-1);
    String[] words = substring.split(",");
//快速排序
    words = quickSort(words, 0, words.length - 1);
    Map<String, Integer> map =new HashMap<>();
    for (int i = 0; i < words.length; i++) {
        Integer counts=1;
        for (int j = i+1; j < words.length; j++) {
            Integer wordsCount = map.get(words[i]);
            if (wordsCount==null){
                map.put(words[i],counts);
            }
            //如果I字符串数字少于J
            if (words[i].length()<=words[j].length()){
                //判断是否包含
                if ( words[j].contains(words[i])){

                    map.put(words[i],counts++);
                }

            }
        }
    }
    LinkedHashMap<String, Integer> valueResult = new LinkedHashMap<>();
    map.entrySet().stream()
            .sorted(Map.Entry
                    .comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(b->valueResult.put(b.getKey(), b.getValue()));

    return valueResult;
}

    /**
     * 快速排序
     * @param arr
     * @return
     */
    public  static String[] quickSort(String [] arr,int low,int high){
        int i,j;
        String p, temp;
        if(low >= high) {
            return null;
        }
        //p就是基准数,这里就是每个数组的第一个
        p = arr[low];
        i = low;
        j = high;
        while(i < j) {
            //右边当发现小于p的值时停止循环
            while(arr[j].length() >= p.length() && i < j) {
                j--;
            }
            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）
            //左边当发现大于p的值时停止循环
            while(arr[i].length()<= p.length() && i < j) {
                i++;
            }
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i]= p;
        quickSort(arr,low,j-1);  //对左边快排
        quickSort(arr,j+1,high); //对右边快排
      return  arr;
    }
}

