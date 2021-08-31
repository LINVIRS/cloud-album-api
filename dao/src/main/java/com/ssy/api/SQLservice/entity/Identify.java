package com.ssy.api.SQLservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: Identify
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 19:59:02 
 * @Version: V1.0
 **/
@Entity
public class Identify {
    private int id;
    private String type;
    private String identifyResult;
    private Integer userId;
    private Integer photoId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer isDelete;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "identify_result", nullable = true, length = 255)
    public String getIdentifyResult() {
        return identifyResult;
    }

    public void setIdentifyResult(String identifyResult) {
        this.identifyResult = identifyResult;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "photo_id", nullable = true)
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "is_delete", nullable = true)
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identify identify = (Identify) o;
        return id == identify.id && Objects.equals(type, identify.type) && Objects.equals(identifyResult, identify.identifyResult) && Objects.equals(userId, identify.userId) && Objects.equals(photoId, identify.photoId) && Objects.equals(createTime, identify.createTime) && Objects.equals(updateTime, identify.updateTime) && Objects.equals(isDelete, identify.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, identifyResult, userId, photoId, createTime, updateTime, isDelete);
    }
}
