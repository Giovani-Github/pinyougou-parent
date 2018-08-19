package com.pinyougou.page.service;

/**
 * 商品详细网页静态化接口
 *
 * @Author Giovani
 * @Create: 2018/8/18 14:11
 */
public interface ItemPageService {

    /**
     * 使用freemarker生成商品详细页
     *
     * @param goodsId
     */
    public boolean genItemHtml(Long goodsId);

    /**
     * 删除商品详细页
     *
     * @param goodsIds
     * @return
     */
    public boolean deleteItemHtml(Long[] goodsIds);

}
