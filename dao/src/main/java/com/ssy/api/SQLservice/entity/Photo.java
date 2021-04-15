package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: Photo
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:32:17 
 * @Version: V1.0
 **/
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    private int id;
    private String url;
    private Integer isUpload;
    private String tagId;
    private String longitude;
    private String latitude;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer isDelete;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "is_upload", nullable = true)
    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }

    @Basic
    @Column(name = "tag_id", nullable = true)
    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "longitude", nullable = true, length = 255)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = true, length = 255)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
        Photo photo = (Photo) o;
        return id == photo.id &&
                Objects.equals(url, photo.url) &&
                Objects.equals(isUpload, photo.isUpload) &&
                Objects.equals(tagId, photo.tagId) &&
                Objects.equals(longitude, photo.longitude) &&
                Objects.equals(latitude, photo.latitude) &&
                Objects.equals(userId, photo.userId) &&
                Objects.equals(createTime, photo.createTime) &&
                Objects.equals(updateTime, photo.updateTime) &&
                Objects.equals(isDelete, photo.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, isUpload, tagId, longitude, latitude, userId, createTime, updateTime, isDelete);
    }
}
