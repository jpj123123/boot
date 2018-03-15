package jpj.boot.dao;

import jpj.boot.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String userName);

    int updateDelUser(User upUser);

    List<User> listUser();

    List<User> listAllSaleUser();
}