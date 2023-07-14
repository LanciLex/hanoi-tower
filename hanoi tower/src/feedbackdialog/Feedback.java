package feedbackdialog;

/**
 * 将反馈表转换为一个类，表中的字段转换为类的属性，表中的一条记录就是一个对象
 * @author LanciLex
 * @create 2022-05-25 13:01
 */
public class Feedback {
    private String nickName;
    private String feedback;
    private String nowDateTime;

    public Feedback(String nickName, String feedback, String nowDateTime) {
        this.nickName = nickName;
        this.feedback = feedback;
        this.nowDateTime = nowDateTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getNowDateTime(){
        return nowDateTime;
    }

    public void setLocalDateTime(String nowDateTime){
        this.nowDateTime=nowDateTime;
    }
}
