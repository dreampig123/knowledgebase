package org.pegcode.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Options;
import org.pegcode.core.entity.TbOnlineDocument;
import org.pegcode.core.vo.request.DocumentRequest;
import org.pegcode.core.vo.request.OnlineDocumentRequest;

import java.util.List;

/**
 * <p>
 * 在线编辑文档 服务类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
public interface TbOnlineDocumentService extends IService<TbOnlineDocument> {
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    String addOnlineDoc(OnlineDocumentRequest onlineDocumentRequest);

    Boolean delOnlineDocByCode(String docCode);

    Boolean upOnlineDocByCode(TbOnlineDocument onlineDoc);

    List<TbOnlineDocument> listOnlineDocs();
    //TODO 分页

    String addDocMain(DocumentRequest documentRequest);
}
