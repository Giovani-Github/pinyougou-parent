package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌信息数据请求处理
 *
 * @Author Giovani
 * @Create: 2018/8/4 11:30
 */
@RestController // @Controller 和@ResponseBody的结合，不用每个方法都写responseBody
@RequestMapping("/brand")
public class BrandController {

    @Reference // 通过dobbox在注册中心里查找出服务提供方的brandService进行注入
    private BrandService brandService;

    /**
     * 查询所有品牌的数据（json格式）
     * 访问地址:http://localhost:9101/brand/findAll.do
     *
     * @param
     * @return: java.util.List<com.pinyougou.pojo.TbBrand>
     * @Author: Giovani
     * @Date: 2018/8/4 18:01
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    /**
     * 分页查询所有品牌数据(json格式)
     *
     * @param page 当前页
     * @param rows 查询记录数
     * @return entity.PageResult
     * @Author Giovani
     * @Date 2018/8/5 15:35
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return brandService.findPage(page, rows);
    }

    /**
     * 添加品牌信息
     *
     * @param
     * @return entity.Result
     * @Author Giovani
     * @Date 2018/8/5 16:07
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand) {

        try {
            brandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }

    }

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     * @return com.pinyougou.pojo.TbBrand
     * @Author Giovani
     * @Date 2018/8/5 16:55
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);
    }

    /**
     * 更新品牌信息
     *
     * @param
     * @return entity.Result
     * @Author Giovani
     * @Date 2018/8/5 16:57
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {

        try {
            brandService.update(brand);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }

    }

    /**
     * 根据id数组，批量删除品牌
     *
     * @param ids 品牌id数组
     * @return entity.Result
     * @Author Giovani
     * @Date 2018/8/5 17:39
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 根据条件分页查询
     *
     * @param brand 条件
     * @param page  当前页
     * @param rows  当前页查询记录数
     * @return entity.PageResult
     * @Author Giovani
     * @Date 2018/8/5 18:09
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, int page, int rows) {
        return brandService.findPage(page, rows, brand);
    }

}
