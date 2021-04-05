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

    /**
     * 从数据库中，通过ID来获取到用户对象
     * @param id 用户的ID，即电话号码
     * @return 对应的用户对象
     */
    @Select("select * from miaosha_user where id = #{id}")
    User getUserById(@Param("id")long id);
}
