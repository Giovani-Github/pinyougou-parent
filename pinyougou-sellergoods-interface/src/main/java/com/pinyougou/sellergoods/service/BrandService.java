package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

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

    /**
     * 返回分页查询结果
     *
     * @param pageNum  当前页
     * @param pageSize 查询记录数
     * @return: entity.PageResult
     * @Author: Giovani
     * @Date: 2018/8/5 15:20
     */
    public PageResult findPage(int pageNum, int pageSize);

    /**
     * 增加品牌信息
     *
     * @param brand 品牌实体类
     * @return void
     * @Author Giovani
     * @Date 2018/8/5 16:02
     */
    public void add(TbBrand brand);

    /**
     * 更新品牌信息
     *
     * @param brand 新的品牌信息
     * @return void
     * @Author Giovani
     * @Date 2018/8/5 16:48
     */
    public void update(TbBrand brand);

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     * @return com.pinyougou.pojo.TbBrand
     * @Author Giovani
     * @Date 2018/8/5 16:49
     */
    public TbBrand findOne(Long id);

    /**
     * 根据id数组，批量删除品牌
     *
     * @param ids id数组
     * @return void
     * @Author Giovani
     * @Date 2018/8/5 17:36
     */
    public void delete(Long[] ids);

    /**
     * 返回分页查询结果，并根据条件查询
     *
     * @param pageNum  当前页
     * @param pageSize 查询记录数
     * @param brand    条件
     * @return entity.PageResult
     * @Author Giovani
     * @Date 2018/8/5 18:03
     */
    public PageResult findPage(int pageNum, int pageSize, TbBrand brand);
}
