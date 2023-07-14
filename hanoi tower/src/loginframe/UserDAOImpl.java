package loginframe;

import java.sql.Connection;

/**
 * 接口的实现类，使用了BaseDAO中的通用增删改操作方法以及查询特殊值的通用的方法
 * @author LanciLex
 * @create 2022-05-25 13:57
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public void insert(Connection connection, User user) {
        String sql="insert into users(name,password)values(?,?)";
        update(connection,sql,user.getUserName(),user.getUserPassword());
    }

    @Override
    public String getPassword(Connection connection, String userName) {
        String sql="select password from users where name = ?";
        return getValue(connection,sql,userName);
    }
}
