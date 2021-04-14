package com.ssy.api.SQLservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: UserLike
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/01 09:15:36 
 * @Version: V1.0
 **/
@Entity
@Table(name = "t_user_like", schema = "cloud-album", catalog = "")
public class UserLike {
    private int pkId;
    private int userId;
    private int contentId;
    private byte delFlag;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "pk_id", nullable = false)
    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "content_id", nullable = false)
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "del_flag", nullable = false)
    public byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(byte delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
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
        UserLike userLike = (UserLike) o;
        return pkId == userLike.pkId &&
                userId == userLike.userId &&
                contentId == userLike.contentId &&
                delFlag == userLike.delFlag &&
                Objects.equals(createTime, userLike.createTime) &&
                Objects.equals(updateTime, userLike.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pkId, userId, contentId, delFlag, createTime, updateTime);
    }
}
