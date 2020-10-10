package com.yingxue.lesson.service;

import com.yingxue.lesson.vo.req.UserOwnRoleReqVO;

import java.util.List;

/**
 * @ClassName: UserRoleService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface UserRoleService {

    List<String> getRoleIdsByUserId(String userId);
    void addUserRoleInfo(UserOwnRoleReqVO vo);
    List<String> getUserIdsByRoleIds(List<String> roleIds);
    List<String> getUserIdsBtRoleId(String roleId);
    int removeUserRoleId(String roleId);
}
