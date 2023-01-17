package org.pegcode.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.pegcode.core.entity.TbFile;
import org.pegcode.core.vo.request.FileRequest;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
public interface TbFileService extends IService<TbFile> {

    String addFile(FileRequest fileRequest);

    void addFileList(List<FileRequest> fileRequests,String docCode);

}
