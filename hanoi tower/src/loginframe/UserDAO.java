package loginframe;

import java.sql.Connection;

/**
 * 此接口规定了对用户表的操作中所需的抽象方法，具体实现请看实现类
 * @author LanciLex
 * @create 2022-05-25 13:49
 */
public interface UserDAO {
    void insert(Connection connection, User user);

    String getPassword(Connection connection,String userName);
}
