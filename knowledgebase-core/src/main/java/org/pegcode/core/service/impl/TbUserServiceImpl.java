package org.pegcode.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pegcode.core.entity.TbUser;
import org.pegcode.core.mapper.TbUserMapper;
import org.pegcode.core.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getUserByCode(String userCode) {
        QueryWrapper<TbUser> condition = new QueryWrapper<>();
        condition.eq("code", userCode);
        TbUser tbUser = tbUserMapper.selectOne(condition);
        return tbUser;
    }
}
