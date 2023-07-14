package feedbackdialog;

import loginframe.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 反馈信息对话框
 * @author LanciLex
 * @create 2022-05-26 11:03
 */
public class FeedbackDialog extends JDialog implements ActionListener {

    FeedbackDAOImpl feedbackDAO;
    JTextField currentTime, nickName;
    JTextArea comment;
    JButton submit;
    JButton cancel;
    Box boxV, boxH1, boxH2, boxH3, boxH4;
    Timer timer;
    DateTimeFormatter formatter;

    public FeedbackDialog() {
        setTitle("提交反馈");
        setIconImage(new ImageIcon("img\\hanoi.png").getImage());
        setBounds(600, 250, 350, 250);
        setLayout(new FlowLayout());
        feedbackDAO = new FeedbackDAOImpl();
        currentTime = new JTextField(20);
        //使用DateTimeFormatter对当前日期与时间进行格式化
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //使用Timer计时器，每隔一秒触发一次事件
        timer = new Timer(1000, this);
        timer.start();
        nickName = new JTextField(20);
        comment = new JTextArea(4, 20);
        submit = new JButton("提交");
        submit.addActionListener(this);
        cancel = new JButton("取消");
        cancel.addActionListener(this);
        boxV = Box.createVerticalBox();
        boxH1 = Box.createHorizontalBox();
        boxH2 = Box.createHorizontalBox();
        boxH3 = Box.createHorizontalBox();
        boxH4 = Box.createHorizontalBox();
        boxH1.add(new JLabel("昵称："));
        boxH1.add(Box.createHorizontalStrut(10));
        boxH1.add(nickName);
        boxH2.add(new JLabel("反馈："));
        boxH2.add(Box.createHorizontalStrut(10));
        boxH2.add(comment);
        boxH3.add(new JLabel("时间："));
        boxH3.add(Box.createHorizontalStrut(10));
        boxH3.add(currentTime);
        boxH4.add(submit);
        boxH4.add(Box.createHorizontalStrut(10));
        boxH4.add(cancel);
        boxV.add(Box.createVerticalStrut(40));
        boxV.add(boxH1);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(boxH2);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(boxH3);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(boxH4);
        add(boxV);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String nickNameText = nickName.getText();
            String commentText = comment.getText();
            String nowDateTime = LocalDateTime.now().format(formatter);//获取当前时间并格式化
            Feedback feedback = new Feedback(nickNameText, commentText, nowDateTime);
            Connection connection = null;
            try {
                connection = JDBCUtils.getConnection();
                //此处check的值若为1，则表示插入信息成功。若为0则表示插入信息失败
                int check = feedbackDAO.insert(connection, feedback);
                if (connection == null || check == 0) {
                    JOptionPane.showMessageDialog(this, "提交失败", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "提交成功", "通知", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                JDBCUtils.closeResource(connection, null);
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
        } else if (e.getSource() == timer) {
            //每隔一秒，更新一次文本框中的时间信息
            String nowDateTime = LocalDateTime.now().format(formatter);
            currentTime.setText(nowDateTime);
        }
    }
}
