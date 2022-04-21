package com.imooc.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SongShengLin
 * @date 2022/4/20 17:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDetail {

    private Integer gdId;

    private Integer goodsId;

    private String gdPicUrl;

    private Integer gdOrder;

    /**
     * 商品表：多对一的属性
     */
    private Goods goods;

}
