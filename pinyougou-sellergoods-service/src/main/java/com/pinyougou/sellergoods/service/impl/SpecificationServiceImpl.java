package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    /**
     * 查询全部
     */
    @Override

    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Specification specification) {

        // 插入规格
        specificationMapper.insert(specification.getSpecification());

        // 循环插入规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            // 设置规格id
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }

    }

    /**
     * 修改
     */
    @Override
    public void update(Specification specification) {
        // 保存修改的规格数据
        specificationMapper.updateByPrimaryKey(specification.getSpecification());

        // 先删除原本的规格选项数据
        TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        specificationOptionMapper.deleteByExample(specificationOptionExample);

        // 循环插入规格选项数据
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            // 设置规格选项所属规格id
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();

        // 查询出规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        // 查询出规格选项
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId()); // 根据规格id查询出规格选项
        List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);

        // 构建组合实体类返回结果
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(tbSpecificationOptions);
        return specification;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            // 删除规格
            specificationMapper.deleteByPrimaryKey(id);

            // 循环删除规格选项
            TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(specificationOptionExample);
        }
    }

    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }

        }

        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
