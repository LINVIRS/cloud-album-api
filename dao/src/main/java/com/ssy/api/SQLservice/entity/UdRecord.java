package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: UdRecord
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:32:19 
 * @Version: V1.0
 **/
@Entity
@Table(name = "ud_record", schema = "cloud-album", catalog = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UdRecord {
    private int id;
    private Integer type;
    private Integer status;
    private String photoId;
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
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        UdRecord udRecord = (UdRecord) o;
        return id == udRecord.id &&
                Objects.equals(type, udRecord.type) &&
                Objects.equals(status, udRecord.status) &&
                Objects.equals(photoId, udRecord.photoId) &&
                Objects.equals(userId, udRecord.userId) &&
                Objects.equals(createTime, udRecord.createTime) &&
                Objects.equals(updateTime, udRecord.updateTime) &&
                Objects.equals(isDelete, udRecord.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, status, photoId, userId, createTime, updateTime, isDelete);
    }
}
