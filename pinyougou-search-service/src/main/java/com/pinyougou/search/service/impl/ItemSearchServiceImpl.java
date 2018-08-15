package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;

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
    public Map<String, Object> search(Map searchMap) {
        Map<String, Object> map = new HashMap<>();

        //1.查询列表
        map.putAll(searchList(searchMap));
        
        return map;
    }

    /**
     * 根据关键字从solr中搜索item（sku）列表
     *
     * @param
     * @return java.util.Map
     * @Author Giovani
     * @Date 2018/8/15 14:12
     */
    private Map searchList(Map searchMap) {
        Map map = new HashMap();

        // 高亮条件设置
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title");//设置高亮的域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀
        highlightOptions.setSimplePostfix("</em>");//高亮后缀
        query.setHighlightOptions(highlightOptions);//设置高亮选项

        /**
         * 按照关键字查询
         *
         * 搜索的时候有可能是根据商品名称，或者品牌名称等等。
         * 这时候偶，把所有字段都配置到复制域里，那么就方便了，只需要通过配置到的复制域名称item_keywords
         * 进行搜索，就可以匹配所有配置好的字段
         * 在D:\solrhome\collection1\conf\schema.xml中，配置好复制域
         * 自动进行分词搜索，所以条件直接写匹配，无需写包含
         * item_keywords：复制域中配置的名称
         */
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        // 通过关键字搜索出高亮列表，设置高亮
        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
        for (HighlightEntry<TbItem> h : page.getHighlighted()) {//循环高亮入口集合
            TbItem item = h.getEntity();//获取原实体类
            if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
                item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
            }
        }
        map.put("rows", page.getContent());
        return map;
    }

}
