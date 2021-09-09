package com.example.servlet.mapper;

import com.example.servlet.entity.History;
import com.example.servlet.entity.Stars;
import com.example.servlet.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

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

    @Insert("insert into usermessage values(#{id},#{username},#{password},#{email},#{nickname},#{selfie})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void register(User user);

    @Select("select u.id from usermessage u where u.username=#{username} and password=#{password}")
    Long login(User user);

    @Update("update usermessage set selfie=#{num} where username=#{username}")
    void mod_selfie(String username, int num);

    @Update("update usermessage set nickname=#{nickname} where username=#{username}")
    void mod_nickname(String username, String nickname);

    @Update("update usermessage set email=#{email} where username=#{username}")
    void mod_email(String username, String email);

    @Insert("insert into userHistory (userId,insUri,detail,time,times) values(#{userId},#{insUri},#{detail},#{time},1)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addHistory(History history);

    @Delete("delete from userHistory where userId=#{userId} and insUri=#{insUri}")
    void rmHistory(History history);

    @Update("update userHistory set times=times+1  where userId=#{userId} and insUri=#{insUri}")
    int incrHistory(History history);

    @Select("select u.insUri,u.detail,u.time,u.times from userHistory u where u.userId=#{userId}")
    List<History> getHistoryCloud(Long userId);

    @Select("select u.insUri,u.detail,u.time from userHistory u where u.userId=#{userId}")
    List<History> getHistory(Long userId);

    @Insert("insert into stars (userId,insUri,detail) values(#{userId},#{insUri},#{detail})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addStars(Stars stars);

    @Delete("delete from stars where userId=#{userId} and insUri=#{insUri}")
    int rmStars(Stars stars);

    @Select("select u.insUri,u.detail from stars u where u.userId=#{userId}")
    List<History> getStars(Long userId);

    @Select("select u.insUri,u.detail from stars u where u.userId=#{userId} and u.insUri=#{insUri}")
    List<History> searchStars(Long userId, String insUri);
}
