package com.example.servlet.mapper;

import com.example.servlet.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     *查看用户名是否已经存在
     * @param username
     * @return
     */
    @Select("select u.username,u.password from usermessage u where u.username=#{username}")
    User findUserByName(@Param("username") String username);

    @Select("SELECT * FROM usermessage WHERE id=#{id}")
    User findUserById(@Param("id") Long id);


    /**
     * 注册
     * @param user
     */
    @Insert("insert into usermessage values(#{id},#{username},#{password},#{email})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void register(User user);


    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select u.id from usermessage u where u.username=#{username} and password=#{password}")
    Long login(User user);

    @Update("update usermessage set selfie=#{num} where username=#{username}")
    void mod_selfie(String username, int num);

    @Update("update usermessage set nickname=#{nickname} where username=#{username}")
    void mod_nickname(String username, String nickname);

    @Update("update usermessage set email=#{email} where username=#{username}")
    void mod_email(String username, String email);
}
