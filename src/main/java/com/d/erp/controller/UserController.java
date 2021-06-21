package com.d.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.d.erp.entity.User;
import com.d.erp.service.UserService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:11:14
 */
@Api(tags = "user")
@RestController
@RequestMapping("user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @ApiOperation("user 分页查询所有数据")
    @GetMapping
    public R selectAll(Page<User> page, User user) {
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("user 通过主键查询单条数据")
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @ApiOperation("user 新增数据")
    @PostMapping
    public R insert(@RequestBody User user) {
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @ApiOperation("user 修改数据")
    @PutMapping
    public R update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @ApiOperation("user 删除数据")
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userService.removeByIds(idList));
    }
}
