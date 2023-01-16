package org.pegcode.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.core.entity.TbFile;
import org.pegcode.core.mapper.TbFileMapper;
import org.pegcode.core.service.TbFileService;
import org.springframework.stereotype.Service;

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

}
