package feedbackdialog;

import java.sql.Connection;

/** 此接口规定了对反馈表的操作中所需的抽象方法，具体实现请看实现类
 * @author LanciLex
 * @create 2022-05-26 10:39
 */
public interface FeedbackDAO {
    int insert(Connection connection, Feedback feedback);
}
