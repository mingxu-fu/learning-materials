package com.yingxue.lesson.service;

import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.vo.req.RegisterReqVO;
import com.yingxue.lesson.vo.req.UpdateUserReqVO;

/**
 * @ClassName: UserService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface UserService {
    SysUser getUserInfo(String id);

    String register(RegisterReqVO vo);

    String updateUserInfo(UpdateUserReqVO vo);

    String deletedUserInfo(String id);

}
