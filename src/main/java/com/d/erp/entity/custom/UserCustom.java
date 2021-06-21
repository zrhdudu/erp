package com.d.erp.entity.custom;

import java.util.Date;
import java.io.Serializable;

import com.d.erp.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:11:14
 */

@ApiModel(value = "UserCustom", description = "接收表实体扩展类")
@SuppressWarnings("serial")
@Data
@Alias("userCustom")
public class UserCustom extends User {

}
