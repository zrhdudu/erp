package com.d.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.d.erp.dao.UserDao;
import com.d.erp.entity.User;
import com.d.erp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:11:13
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
