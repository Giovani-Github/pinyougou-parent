package com.pinyougou.cart.service;

import com.pinyougou.pojogroup.Cart;

import java.util.List;

/**
 * 购物车服务接口
 *
 * @Author Giovani
 * @Create: 2018/8/22 14:42
 */
public interface CartService {

    /**
     * 添加商品到购物车
     *
     * @param cartList 购物车;
     * @param itemId   商品id， item，sku表的id
     * @param num      商品数量
     * @return java.util.List<com.pinyougou.pojogroup.Cart>
     * @Author Giovani
     * @Date 2018/8/22 14:42
     */
    public List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num);
}
