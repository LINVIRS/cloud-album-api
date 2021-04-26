package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.entity.Tag;
import com.ssy.api.SQLservice.repository.TagRepository;
import com.ssy.api.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
