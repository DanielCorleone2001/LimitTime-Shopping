package com.daniel.dao;

import com.daniel.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Package: com.daniel.dao
 * @ClassName: UserDao
 * @Author: daniel
 * @CreateTime: 2021/3/28 18:39
 * @Description:
 */
@Mapper
public interface UserDao {

    @Select("select * from miaosha_user where id = #{id}")
    User getById(@Param("id")long id);
}
