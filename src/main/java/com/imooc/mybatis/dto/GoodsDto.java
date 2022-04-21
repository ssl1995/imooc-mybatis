package com.imooc.mybatis.dto;

import com.imooc.mybatis.entity.Category;
import com.imooc.mybatis.entity.Goods;

/**
 * @author SongShengLin
 * @date 2022/4/12 09:13
 * @description
 */
public class GoodsDto {

    private Goods goods = new Goods();

    private Category category = new Category();

    private String test;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "goods=" + goods +
                ", category=" + category +
                ", test='" + test + '\'' +
                '}';
    }
}
