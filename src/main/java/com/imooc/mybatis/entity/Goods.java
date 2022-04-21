package com.imooc.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/4/12 00:44
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {
    private Integer goodsId;//商品编号
    private String title;//标题
    private String subTitle;//子标题
    private Float originalCost;//原始价格
    private Float currentPrice;//当前价格
    private Float discount;//折扣率
    private Integer isFreeDelivery;//是否包邮 ,1-包邮 0-不包邮
    private Integer categoryId;//分类编号

    /**
     * 商品商品表：一对多
     */
    private List<GoodsDetail> goodsDetails;

}
