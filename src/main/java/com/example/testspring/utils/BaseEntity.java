package com.example.testspring.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel("实体对象的公共字段")

public class BaseEntity
{

    @ApiModelProperty("状态（0正常 1删除）")
    private Boolean deleted;
    @ApiModelProperty("创建者")
    private String createBy;
    @TableField(
            fill = FieldFill.INSERT
    )
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;
    @ApiModelProperty("更新者")
    private String updateBy;
    @TableField(
            fill = FieldFill.UPDATE
    )
    @ApiModelProperty("更新时间")
    private LocalDateTime updateDate;

    public BaseEntity() {
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    public void setCreateDate(final LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateDate(final LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity other = (BaseEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$deleted = this.getDeleted();
                    Object other$deleted = other.getDeleted();
                    if (this$deleted == null) {
                        if (other$deleted == null) {
                            break label71;
                        }
                    } else if (this$deleted.equals(other$deleted)) {
                        break label71;
                    }

                    return false;
                }

                Object this$createBy = this.getCreateBy();
                Object other$createBy = other.getCreateBy();
                if (this$createBy == null) {
                    if (other$createBy != null) {
                        return false;
                    }
                } else if (!this$createBy.equals(other$createBy)) {
                    return false;
                }

                label57: {
                    Object this$createDate = this.getCreateDate();
                    Object other$createDate = other.getCreateDate();
                    if (this$createDate == null) {
                        if (other$createDate == null) {
                            break label57;
                        }
                    } else if (this$createDate.equals(other$createDate)) {
                        break label57;
                    }

                    return false;
                }

                Object this$updateBy = this.getUpdateBy();
                Object other$updateBy = other.getUpdateBy();
                if (this$updateBy == null) {
                    if (other$updateBy != null) {
                        return false;
                    }
                } else if (!this$updateBy.equals(other$updateBy)) {
                    return false;
                }

                Object this$updateDate = this.getUpdateDate();
                Object other$updateDate = other.getUpdateDate();
                if (this$updateDate == null) {
                    if (other$updateDate == null) {
                        return true;
                    }
                } else if (this$updateDate.equals(other$updateDate)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $deleted = this.getDeleted();
        result = result * 59 + ($deleted == null ? 43 : $deleted.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $createDate = this.getCreateDate();
        result = result * 59 + ($createDate == null ? 43 : $createDate.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateDate = this.getUpdateDate();
        result = result * 59 + ($updateDate == null ? 43 : $updateDate.hashCode());
        return result;
    }

    public String toString() {
        return "BaseEntity(deleted=" + this.getDeleted() + ", createBy=" + this.getCreateBy() + ", createDate=" + this.getCreateDate() + ", updateBy=" + this.getUpdateBy() + ", updateDate=" + this.getUpdateDate() + ")";
    }

}
