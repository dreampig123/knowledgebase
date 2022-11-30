package org.pegcode.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.pegcode.dao.entity.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author peg
 * @since 2022-11-09
 */
public interface TbUserService extends IService<TbUser> {

    TbUser getUserByCode(String userCode);

}
