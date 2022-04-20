package dao;

import entityClass.User;
import exception.BaseException;
import exception.NoUserException;

/**
 * @Author : ߲����
 * @Description :
 * @Date : 2021/11/23 15:58
 */
public interface UserDao extends BaseDao<User>{
    /**
     *@Description Ѱ�Ҷ���
     *@Param  [user]
     *@Return entityClass.User
     *@Exception
     **/
    @Override
    User findByName(User user) throws BaseException;
}
