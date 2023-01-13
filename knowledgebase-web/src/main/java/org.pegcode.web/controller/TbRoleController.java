package org.pegcode.web.controller;


import io.swagger.annotations.ApiOperation;
import org.pegcode.common.entity.response.ResponseEntity;
import org.pegcode.core.entity.TbRole;
import org.pegcode.core.entity.TbUser;
import org.pegcode.core.service.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/role")
public class TbRoleController {

    @Autowired
    private TbRoleService tbRoleService;

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/add")
    public ResponseEntity addRole(@RequestBody TbRole tbRole) {
        return ResponseEntity.success(tbRoleService.save(tbRole));
    }

    @ApiOperation(value = "根据ID删除角色")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delRoleById(@PathVariable("id") long roleId) {
        return ResponseEntity.success(tbRoleService.removeById(roleId));
    }

    @ApiOperation(value = "根据ID修改角色")
    @PutMapping(value = "")
    public ResponseEntity updateRoleById(@RequestBody TbRole tbRole) {
        return ResponseEntity.success(tbRoleService.updateById(tbRole));
    }

    @ApiOperation(value = "获取角色列表(不分页)")
    @PostMapping(value = "/list")
    public ResponseEntity getUsersList() {
        return ResponseEntity.success(tbRoleService.list());
    }

}

