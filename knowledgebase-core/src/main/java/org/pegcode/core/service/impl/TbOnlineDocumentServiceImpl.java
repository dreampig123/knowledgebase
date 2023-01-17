package org.pegcode.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.pegcode.common.utils.CommonUtil;
import org.pegcode.core.entity.TbCatalogue;
import org.pegcode.core.entity.TbOnlineDocument;
import org.pegcode.core.mapper.TbOnlineDocumentMapper;
import org.pegcode.core.service.TbCatalogueService;
import org.pegcode.core.service.TbFileService;
import org.pegcode.core.service.TbOnlineDocumentService;
import org.pegcode.core.vo.request.DocumentRequest;
import org.pegcode.core.vo.request.FileRequest;
import org.pegcode.core.vo.request.OnlineDocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 在线编辑文档 服务实现类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@Service
public class TbOnlineDocumentServiceImpl extends ServiceImpl<TbOnlineDocumentMapper, TbOnlineDocument> implements TbOnlineDocumentService {

    @Autowired
    private TbFileService fileService;
    @Autowired
    private TbCatalogueService catalogueService;
    @Autowired
    private TbOnlineDocumentMapper onlineDocumentMapper;

    //事务回滚
    @Override
    public String addOnlineDoc(OnlineDocumentRequest onlineDocumentRequest) {
        TbOnlineDocument tbOnlineDocument = new TbOnlineDocument();
        BeanCopier beanCopier = BeanCopier.create(OnlineDocumentRequest.class, TbOnlineDocument.class, false);
        beanCopier.copy(onlineDocumentRequest, tbOnlineDocument, null);
        tbOnlineDocument.setDocCode(CommonUtil.createOnlineDocCode());
        tbOnlineDocument.setCatalogueId(Long.parseLong(onlineDocumentRequest.getCatalogueId()));
        int insert = onlineDocumentMapper.insert(tbOnlineDocument);
        if (insert > 0) {
            return tbOnlineDocument.getDocCode();
        }
        return "";
    }

    @Override
    public Boolean delOnlineDocByCode(String docCode) {
        if (StringUtils.isNotEmpty(docCode)) {
            QueryWrapper<TbOnlineDocument> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("doc_code", docCode);
            int delete = onlineDocumentMapper.delete(queryWrapper);
            if (delete > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean upOnlineDocByCode(TbOnlineDocument onlineDoc) {
        String docCode = onlineDoc.getDocCode();
        if (StringUtils.isNotEmpty(docCode)) {
            QueryWrapper<TbOnlineDocument> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("doc_code", docCode);
            int update = onlineDocumentMapper.update(onlineDoc, queryWrapper);
            if (update > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TbOnlineDocument> listOnlineDocs() {
        return onlineDocumentMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public String addDocMain(DocumentRequest documentRequest) {
        String olDocCode = "";
        String catalogueId = documentRequest.getCatalogueId();
        TbCatalogue catalogue = catalogueService.getCatalogueById(Integer.parseInt(catalogueId));
        //目录存在
        if (null != catalogue) {
            OnlineDocumentRequest onlineDocumentRequest = new OnlineDocumentRequest();
            BeanCopier beanCopier = BeanCopier.create(DocumentRequest.class, OnlineDocumentRequest.class, false);
            beanCopier.copy(documentRequest, onlineDocumentRequest, null);
            olDocCode = addOnlineDoc(onlineDocumentRequest);
            //插入在线文档成功
            if (StringUtils.isNotEmpty(olDocCode)) {
                List<FileRequest> fileList = documentRequest.getFileList();
                //批量插入附属文件
                fileService.addFileList(fileList, olDocCode);
            }
        }
        return olDocCode;
    }
}
