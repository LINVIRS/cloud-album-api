package com.ssy.api.SQLservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FaceStore
 *
 * @author wf
 * @date 2021/4/26 16:27
 * @description
 */
@Entity
@Table(name = "face_store", schema = "cloud-album", catalog = "")
public class FaceStore {
    private int faceStoreId;
    private Integer albumId;
    private String name;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String faceId;

    @Id
    @Column(name = "face_store_id")
    public int getFaceStoreId() {
        return faceStoreId;
    }

    public void setFaceStoreId(int faceStoreId) {
        this.faceStoreId = faceStoreId;
    }

    @Basic
    @Column(name = "album_id")
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FaceStore faceStore = (FaceStore) o;

        if (faceStoreId != faceStore.faceStoreId) return false;
        if (albumId != null ? !albumId.equals(faceStore.albumId) : faceStore.albumId != null) return false;
        if (name != null ? !name.equals(faceStore.name) : faceStore.name != null) return false;
        if (description != null ? !description.equals(faceStore.description) : faceStore.description != null)
            return false;
        if (createTime != null ? !createTime.equals(faceStore.createTime) : faceStore.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(faceStore.updateTime) : faceStore.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = faceStoreId;
        result = 31 * result + (albumId != null ? albumId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "face_id")
    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }
}
