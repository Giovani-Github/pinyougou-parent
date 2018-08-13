package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.TbContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告信息请求处理类
 *
 * @Author Giovani
 * @Create: 2018/8/13 15:31
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    /**
     * 根据广告分类ID查询广告列表
     *
     * @param
     * @return java.util.List<com.pinyougou.pojo.TbContent>
     * @Author Giovani
     * @Date 2018/8/13 15:32
     */
    @RequestMapping("/findByCategoryId")
    public List<TbContent> findByCategoryId(Long categoryId) {
        return contentService.findByCategoryId(categoryId);
    }
}

