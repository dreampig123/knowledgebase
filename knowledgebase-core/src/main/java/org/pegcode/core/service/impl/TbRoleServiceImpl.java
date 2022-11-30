package org.pegcode.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.core.service.TbRoleService;
import org.pegcode.dao.entity.TbRole;
import org.pegcode.dao.mapper.TbRoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author peg
 * @since 2022-11-09
 */
@Service
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements TbRoleService {

}
