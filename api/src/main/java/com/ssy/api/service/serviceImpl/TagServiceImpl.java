package com.ssy.api.service.serviceImpl;


import com.ssy.api.SQLservice.entity.Tag;
import com.ssy.api.SQLservice.repository.TagRepository;
import com.ssy.api.SQLservice.vo.TagVo;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public List<Tag> selectTagsById(int[] ids) {
        List idList = Arrays.asList(ids);
        List<Tag> tags = tagRepository.findAllById(idList);
        StringBuilder idStr = new StringBuilder();
        tags.forEach(tag -> {
            idStr.append(tag.getName());
        });
        return tags;

    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public RestResult addPhotoTag(String tagName, String description, Integer userId) {
        Tag tag = tagRepository.save(Tag.builder()
                .name(tagName)
                .description(description)
                .createTime(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .userId(userId)
                .isDelete(0)
                .build());
        return new RestResultBuilder<>().success(tag);
    }

    @Override
    public RestResult deleteTag(int tagId) {
        return new RestResultBuilder<>().success(tagRepository.deleteTag(tagId));
    }

    @Override
    public RestResult findUserTags(int userId) {
        List<TagVo> tagList = tagRepository.findTagList(userId);
        return new RestResultBuilder<>().success(tagList);

    }
}
