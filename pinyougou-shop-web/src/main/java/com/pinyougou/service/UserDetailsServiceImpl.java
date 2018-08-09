package com.pinyougou.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * 自定义认证类
 *
 * @Author Giovani
 * @Create: 2018/8/9 17:20
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    // 因为没有com.pinyougou.service配置到dobbox的注解扫描中，所以需要手动注入
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 角色列表
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        // 添加ROLE_SELLER商家角色
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));

        // 得到商家对象
        TbSeller seller = sellerService.findOne(username);

        if (seller != null) {
            if (seller.getStatus().equals("1")) {
                // 根据商家名从服务器查出商家详情，封装成user对象，返回回去，sercurity自动与用户输入的数据进行比对。
                return new User(username, seller.getPassword(), grantedAuthorities);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}
