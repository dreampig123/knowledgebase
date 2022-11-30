package org.pegcode.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pegcode.common.entity.response.ResponseEntity;
import org.pegcode.core.service.TbUserService;
import org.pegcode.dao.entity.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author peg
 * @since 2022-11-09
 */
//@RestController ：从 Spring 4.0 以后产生的，用来将 json/xml数据发送到前台页面，而不是返回视图页面。它相当于 @Controller 和 @ResponseBody。
@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class TbUserController {

    //@Value("${student.name:${studnet.age:/student.room}}")
    //@Resource  byName/byType
    //@Qualifier
    @Autowired
    private TbUserService tbUserService;

    //自定义数据类型的绑定
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    //@RequestMapping
    //@RequestParam(value = "userId", required = true)
    @ApiOperation(value = "根据ID获取用户", notes = "userId为Long型")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TbUser> getUserById(@PathVariable("id") long userId) {
        return ResponseEntity.success(tbUserService.getById(userId));
    }

    @ApiOperation(value = "根据CODE获取用户", notes = "code为String型")
    @GetMapping(value = "/{code}")
    public ResponseEntity<TbUser> getUserByCode(@PathVariable("code") String userCode) {
        return ResponseEntity.success(tbUserService.getUserByCode(userCode));
    }

    //@CrossOrigin
    @ApiOperation(value = "新增用户")
    @ExceptionHandler(RuntimeException.class)
    @PostMapping(value = "/add")
    public ResponseEntity addUser(@RequestBody TbUser tbUser) {
        return ResponseEntity.success(tbUserService.save(tbUser));
    }

    @ApiOperation(value = "根据ID删除用户")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delUserById(@PathVariable("id") long userId) {
        return ResponseEntity.success(tbUserService.removeById(userId));
    }

    @ApiOperation(value = "根据ID修改用户")
    @PutMapping(value = "")
    public ResponseEntity updateUserById(@RequestBody TbUser tbUser) {
        return ResponseEntity.success(tbUserService.updateById(tbUser));
    }

    @ApiOperation(value = "获取用户列表(不分页)")
    @PostMapping(value = "/list")
    public ResponseEntity getUsersList() {
        return ResponseEntity.success(tbUserService.list());
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public ResponseEntity register() {
        return null;
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResponseEntity login() {
        return null;
    }
}

