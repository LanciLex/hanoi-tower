package feedbackdialog;

import loginframe.BaseDAO;

import java.sql.Connection;

/**
 * 接口的实现类，使用了BaseDAO中的通用增删改操作方法
 * @author LanciLex
 * @create 2022-05-26 10:40
 */
public class FeedbackDAOImpl extends BaseDAO<Feedback> implements FeedbackDAO {

    @Override
    public int insert(Connection connection, Feedback feedback) {
        String sql="insert into feedback(nickname,feedback,time)values(?,?,?)";
        return update(connection, sql, feedback.getNickName(), feedback.getFeedback(), feedback.getNowDateTime());
    }
}
