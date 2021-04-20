package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Face {
    private String faceId;
    private String url;
    private String name;
    private Integer photoId;
    private Double confidence;
    private String extraInfo;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "face_id")
    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "photo_id")
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "confidence")
    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Basic
    @Column(name = "extra_info")
    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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
        Face face = (Face) o;
        return Objects.equals(faceId, face.faceId) && Objects.equals(url, face.url) && Objects.equals(name, face.name) && Objects.equals(photoId, face.photoId) && Objects.equals(confidence, face.confidence) && Objects.equals(extraInfo, face.extraInfo) && Objects.equals(createTime, face.createTime) && Objects.equals(updateTime, face.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faceId, url, name, photoId, confidence, extraInfo, createTime, updateTime);
    }
}
