package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: Albums
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:32:15 
 * @Version: V1.0
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Albums {
    private int id;
    private Integer userId;
    private Integer type;
    private Integer createType;
    private String photoId;
    private String name;
    private String photoNumber;
    private String cover;
    private Integer totalCapacity;
    private Integer tagId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int isDelete;

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

    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cover", nullable = true)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "create_type", nullable = true)
    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    @Basic
    @Column(name = "photo_id", nullable = true, length = 255)
    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "photo_number", nullable = true, length = 255)
    public String getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(String photoNumber) {
        this.photoNumber = photoNumber;
    }

    @Basic
    @Column(name = "total_capacity", nullable = true)
    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Basic
    @Column(name = "tag_id", nullable = true)
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
    @Column(name = "is_delete", nullable = false)
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Albums albums = (Albums) o;
        return id == albums.id &&
                isDelete == albums.isDelete &&
                Objects.equals(userId, albums.userId) &&
                Objects.equals(type, albums.type) &&
                Objects.equals(createType, albums.createType) &&
                Objects.equals(photoId, albums.photoId) &&
                Objects.equals(name, albums.name) &&
                Objects.equals(photoNumber, albums.photoNumber) &&
                Objects.equals(totalCapacity, albums.totalCapacity) &&
                Objects.equals(tagId, albums.tagId) &&
                Objects.equals(createTime, albums.createTime) &&
                Objects.equals(updateTime, albums.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, type, createType, photoId, name, photoNumber, totalCapacity, tagId, createTime, updateTime, isDelete);
    }

    @Override
    public String toString() {
        return "Albums{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", createType=" + createType +
                ", photoId='" + photoId + '\'' +
                ", name='" + name + '\'' +
                ", photoNumber='" + photoNumber + '\'' +
                ", cover='" + cover + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", tagId=" + tagId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
