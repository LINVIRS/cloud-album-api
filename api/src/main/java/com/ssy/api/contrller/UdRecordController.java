package com.ssy.api.contrller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.SQLservice.dto.UdRecordDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.UdRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/udrecord")
@Api(tags = "记录API接口")
public class UdRecordController {
    @Resource
    private UdRecordService udRecordService;

    @ApiOperation(value = "保存记录", httpMethod = "POST", notes = "保存记录")
    @PostMapping("/saveall")
    public RestResult save(@RequestBody String photoDtosStr) {
        // 反斜杠
        String s = photoDtosStr.replaceAll("\\\\", "");
        JSONObject jsonObject = JSON.parseObject(s);
        List<UdRecordDto> userDtos = JSON.parseArray(jsonObject.getString("str"), UdRecordDto.class);
        return udRecordService.saveAll(userDtos);
    }

    @ApiOperation(value = "查询最近一周内上传记录", httpMethod = "POST", notes = "查询记录")
    @PostMapping("/recent/{id}")
    public RestResult selectAllByUserId(@PathVariable int id) {
        return udRecordService.selectAll(id);
    }
}
