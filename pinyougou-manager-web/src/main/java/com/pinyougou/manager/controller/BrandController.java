package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
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
     * @Param: []
     * @return: java.util.List<com.pinyougou.pojo.TbBrand>
     * @Author: Giovani
     * @Date: 2018/8/4 18:01
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

}
