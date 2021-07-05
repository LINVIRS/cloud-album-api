package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ShareAlbum
 *
 * @author wf
 * @date 2021/4/30 16:49
 * @description
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "share_album", schema = "cloud-album", catalog = "")
public class ShareAlbum {
    private int id;
    private int creatorId;
    private String joinUserId;
    private int albumId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int isDelete;
    private String keyWord;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creator_id")
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "join_user_id")
    public String getJoinUserId() {
        return joinUserId;
    }

    public void setJoinUserId(String joinUserId) {
        this.joinUserId = joinUserId;
    }

    @Basic
    @Column(name = "album_id")
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    @Basic
    @Column(name = "is_delete")
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column(name = "key_word")
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShareAlbum that = (ShareAlbum) o;

        if (id != that.id) return false;
        if (creatorId != that.creatorId) return false;
        if (albumId != that.albumId) return false;
        if (isDelete != that.isDelete) return false;
        if (joinUserId != null ? !joinUserId.equals(that.joinUserId) : that.joinUserId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (keyWord != null ? !keyWord.equals(that.keyWord) : that.keyWord != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + creatorId;
        result = 31 * result + (joinUserId != null ? joinUserId.hashCode() : 0);
        result = 31 * result + albumId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + isDelete;
        result = 31 * result + (keyWord != null ? keyWord.hashCode() : 0);
        return result;
    }
}
