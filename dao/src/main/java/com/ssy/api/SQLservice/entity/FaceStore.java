package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "face_store", schema = "cloud-album", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceStore {
    private int faceStoreId;
    private Integer albumId;
    private String name;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return faceStoreId == faceStore.faceStoreId && Objects.equals(albumId, faceStore.albumId) && Objects.equals(name, faceStore.name) && Objects.equals(description, faceStore.description) && Objects.equals(createTime, faceStore.createTime) && Objects.equals(updateTime, faceStore.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faceStoreId, albumId, name, description, createTime, updateTime);
    }
}
