package org.pegcode.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.common.utils.CommonUtil;
import org.pegcode.core.entity.TbFile;
import org.pegcode.core.mapper.TbFileMapper;
import org.pegcode.core.service.TbFileService;
import org.pegcode.core.vo.request.FileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@Service
public class TbFileServiceImpl extends ServiceImpl<TbFileMapper, TbFile> implements TbFileService {

    @Autowired
    private TbFileMapper fileMapper;

    @Override
    public String addFile(FileRequest fileRequest) {
        TbFile tbFile = new TbFile();
        BeanCopier beanCopier = BeanCopier.create(FileRequest.class, TbFile.class, false);
        beanCopier.copy(fileRequest, tbFile, null);
        tbFile.setFileCode(CommonUtil.createFileCode());
        int insert = fileMapper.insert(tbFile);
        if (insert > 0) {
            return tbFile.getFileCode();
        }
        return "";
    }

    @Override
    public void addFileList(List<FileRequest> fileRequests, String docCode) {
        if (!CollectionUtils.isEmpty(fileRequests)) {
            List<TbFile> collect = fileRequests.stream().map(i -> {
                TbFile tbFile = new TbFile();
                BeanCopier beanCopier = BeanCopier.create(FileRequest.class, TbFile.class, false);
                beanCopier.copy(i, tbFile, null);
                tbFile.setFileCode(CommonUtil.createFileCode());
                tbFile.setOnlineDocCode(docCode);
                return tbFile;
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }
}
