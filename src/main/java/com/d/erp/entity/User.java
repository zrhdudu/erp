package com.d.erp.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * (User)表实体类
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:11:13
 */

@ApiModel(value = "User", description = "接收表实体类")
@SuppressWarnings("serial")
@Builder
@Data
@Alias("user")
public class User extends Model<User> {

    @Tolerate
    public User() {
    }


    @ApiModelProperty(value = "")
    @TableId()
    private String username;


    @ApiModelProperty(value = "")
    private String passwd;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "")
    private Date createTimee;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.username;
    }
}
