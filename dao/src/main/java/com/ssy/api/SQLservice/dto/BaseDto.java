package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Integer page = 1;
    private Integer pageSize = 10;

    public Integer getPage() {
        return page * pageSize;
    }

    public void setPage(Integer page) {
        this.page = page - 1;
    }
}