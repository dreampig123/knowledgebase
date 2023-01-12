package org.pegcode.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.core.entity.TbUser;
import org.pegcode.core.mapper.TbUserMapper;
import org.pegcode.core.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-12
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
