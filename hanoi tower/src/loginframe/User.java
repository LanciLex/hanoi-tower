package loginframe;

/**
 * 将用户表转换为一个类，表中的字段转换为类的属性，表中的一条记录就是一个对象
 * @author LanciLex
 * @create 2022-05-25 12:49
 */
public class User {
    private String userName;
    private String userPassword;

    public User(String userName,String userPassword){
        this.userName=userName;
        this.userPassword=userPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
