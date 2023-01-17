package org.pegcode.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.pegcode.core.entity.TbCatalogue;
import org.pegcode.core.vo.request.CatalogueRequest;
import org.pegcode.core.vo.request.FileRequest;

/**
 * <p>
 * 目录表 服务类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
public interface TbCatalogueService extends IService<TbCatalogue> {

    int addCatalogue(CatalogueRequest catalogueRequest);

    TbCatalogue getCatalogueById(long id);

}
