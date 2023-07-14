package loginframe;

import mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * 用户注册登录的界面
 * @author LanciLex
 * @create 2022-05-25 12:22
 */
public class LoginFrame extends JFrame implements ActionListener {
    UserDAOImpl userDAO;
    JTextField userName;
    JPasswordField userPassWord;
    Box boxH;
    Box boxVOne, boxVTwo;
    JButton login;
    JButton noLogin;
    JButton register;

    public LoginFrame() {
        setTitle("用户登录界面");
        setIconImage(new ImageIcon("img\\hanoi.png").getImage());
        setBounds(600, 250, 350, 250);
        userDAO = new UserDAOImpl();
        setLayout(new FlowLayout());
        userName = new JTextField(20);
        userPassWord = new JPasswordField(20);
        login = new JButton("登录");
        login.addActionListener(this);
        register = new JButton("注册");
        register.addActionListener(this);
        noLogin = new JButton("试玩");
        noLogin.addActionListener(this);
        boxH = Box.createHorizontalBox();
        boxVOne = Box.createVerticalBox();
        boxVTwo = Box.createVerticalBox();
        boxVOne.add(Box.createVerticalStrut(40));
        boxVOne.add(new JLabel("用户姓名："));
        boxVOne.add(Box.createVerticalStrut(10));
        boxVOne.add(new JLabel("登录密码："));
        boxVTwo.add(Box.createVerticalStrut(40));
        boxVTwo.add(userName);
        boxVTwo.add(Box.createVerticalStrut(10));
        boxVTwo.add(userPassWord);
        boxH.add(boxVOne);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(boxVTwo);
        add(boxH);
        add(login);
        add(register);
        add(noLogin);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String name = userName.getText();
            String password = String.valueOf(userPassWord.getPassword());
            Connection connection = null;
            try {
                connection = JDBCUtils.getConnection();
                String userDAOPassword = userDAO.getPassword(connection, name);
                if (userDAOPassword == null) {
                    JOptionPane.showMessageDialog(null, "用户名不存在，登录失败", "警告", JOptionPane.WARNING_MESSAGE);
                } else if (userDAOPassword.equals(password)) {//发现问题：String类型对比要用equals()
                    JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new MainFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误，登录失败", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                JDBCUtils.closeResource(connection, null);
            }

        } else if (e.getSource() == register) {
            String name = userName.getText();
            String password = String.valueOf(userPassWord.getPassword());
            User user = new User(name, password);
            Connection connection = null;
            try {
                connection = JDBCUtils.getConnection();
                userDAO.insert(connection, user);
                JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                JDBCUtils.closeResource(connection, null);
            }

        } else if (e.getSource() == noLogin) {
            JOptionPane.showMessageDialog(null, "开始试玩", "提示", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new MainFrame();
        }
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }
}
