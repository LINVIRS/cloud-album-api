package com.ssy.api.service;


import com.ssy.api.SQLservice.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> selectTagsById(int[] ids);
}
