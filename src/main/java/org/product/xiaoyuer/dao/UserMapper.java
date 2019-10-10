package org.product.xiaoyuer.dao;

import org.apache.ibatis.annotations.Mapper;
import org.product.xiaoyuer.entity.User;

@Mapper
public interface UserMapper {
    User findUserById(int id);

    User findUserByEmail(String email);

    User findUserByUserName(String username);

    int insertUser(User user);

    int updateStatus(int status,int id);

    int updateHeader(String header,int id);

    int updatePassword(String password,int id);

}
