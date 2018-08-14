package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
import java.util.Map;

// 由于solrTemplate 是另一个web应用程序中提供的，需要访问另一个应用程序，所以可能会导致时间比较长
// 加timeout是防止服务消费者在调用本提供者的时候，因为本提供者需要访问另一个应用程序时间会长一点
// 服务消费者在调用本提供者的时候，时间超过三秒，就报超时
@Service(timeout = 5000)
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map search(Map searchMap) {

        Map map = new HashMap();

        Query query = new SimpleQuery("*:*");

        /**
         * 前端接收的json格式{keywords:""}
         * 搜索的时候有可能是根据商品名称，或者品牌名称等等。
         * 这时候偶，把所有字段都配置到复制域里，那么就方便了，只需要通过配置到的复制域名称item_keywords
         * 进行搜索，就可以匹配所有配置好的字段
         * 在D:\solrhome\collection1\conf\schema.xml中，配置好复制域
         * 自动进行分词搜索，所以条件直接写匹配，无需写包含
         * item_keywords：复制域中配置的名称
         */
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);

        // 转换为json是：{row: "sku列表"}
        map.put("rows", page.getContent());

        return map;
    }

}
