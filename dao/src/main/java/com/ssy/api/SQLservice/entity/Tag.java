package com.ssy.api.SQLservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName: Tag
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/26 12:38:05 
 * @Version: V1.0
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    private int id;
    private String name;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer isDelete;
    private Integer userId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id && Objects.equals(name, tag.name) && Objects.equals(description, tag.description) && Objects.equals(createTime, tag.createTime) && Objects.equals(updateTime, tag.updateTime) && Objects.equals(isDelete, tag.isDelete) && Objects.equals(userId, tag.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createTime, updateTime, isDelete, userId);
    }
}
