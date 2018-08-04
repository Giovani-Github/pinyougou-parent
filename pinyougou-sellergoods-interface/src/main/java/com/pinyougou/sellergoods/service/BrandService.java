package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;

import java.util.List;

/**
 * 品牌服务层接口
 *
 * @Author Giovani
 * @Create: 2018/8/4 11:23
 */
public interface BrandService {

    /**
     * 返回全部品牌列表
     *
     * @Param: []
     * @return: java.util.List<com.pinyougou.pojo.TbBrand>
     * @Author: Giovani
     * @Date: 2018/8/4 11:24
     */
    public List<TbBrand> findAll();

}
