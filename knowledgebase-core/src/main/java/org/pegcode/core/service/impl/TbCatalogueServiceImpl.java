package org.pegcode.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.core.entity.TbCatalogue;
import org.pegcode.core.mapper.TbCatalogueMapper;
import org.pegcode.core.service.TbCatalogueService;
import org.pegcode.core.vo.request.CatalogueRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 目录表 服务实现类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@Service
public class TbCatalogueServiceImpl extends ServiceImpl<TbCatalogueMapper, TbCatalogue> implements TbCatalogueService {

    private static Logger logger = LoggerFactory.getLogger(TbCatalogueServiceImpl.class);


    @Autowired
    private TbCatalogueMapper catalogueMapper;

    @Override
    public int addCatalogue(CatalogueRequest catalogueRequest) {
        String catalogueName = catalogueRequest.getCatalogueName();
        QueryWrapper<TbCatalogue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catalogue_name", catalogueName);
        List<TbCatalogue> catalogues = catalogueMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(catalogues)) {
            logger.info("已存在同名目录【{}】，请重新输入!",catalogueName);
            return 0;
        }
        TbCatalogue tbCatalogue = new TbCatalogue();
        BeanCopier beanCopier = BeanCopier.create(CatalogueRequest.class, TbCatalogue.class, false);
        beanCopier.copy(catalogueRequest, tbCatalogue, null);
        int insert = catalogueMapper.insert(tbCatalogue);
        if (insert > 0) {
            return tbCatalogue.getId();
        }
        return 0;
    }

    //Controller 校验参数
    @Override
    public TbCatalogue getCatalogueById(long id) {
        return catalogueMapper.selectById(id);
    }
}
