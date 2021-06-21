package com.d.erp.entity.custom;

import java.util.Date;
import java.io.Serializable;

import com.d.erp.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import lombok.Data;

/**
 * (Goods)表实体类
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:09:01
 */

@ApiModel(value = "GoodsCustom", description = "接收表实体扩展类")
@SuppressWarnings("serial")
@Data
@Alias("goodsCustom")
public class GoodsCustom extends Goods {

}
