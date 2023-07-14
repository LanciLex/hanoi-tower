package cheatdialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 查看秘籍对话框
 * @author LanciLex
 * @create 2022-05-26 15:55
 */
public class CheatDialog extends JDialog implements ActionListener {
    ShowMovement showMovement;
    JComboBox<String> comboBox;
    String grade1, grade2, grade3;
    JTextArea answer;
    JScrollPane scrollPane;
    JButton showButton, saveButton;
    Box boxV, boxH1, boxH2, boxH3;
    JFileChooser fileChooser;

    public CheatDialog() {
        setTitle("查看秘籍");
        setIconImage(new ImageIcon("img\\hanoi.png").getImage());
        setBounds(400, 150, 500, 400);
        setLayout(new FlowLayout());
        grade1 = "青铜（3个圆盘）";
        grade2 = "白银（4个圆盘）";
        grade3 = "黄金（5个圆盘）";
        comboBox = new JComboBox<String>();
        comboBox.addItem(grade1);
        comboBox.addItem(grade2);
        comboBox.addItem(grade3);
        answer = new JTextArea(10, 35);
        scrollPane = new JScrollPane(answer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        showButton = new JButton("显示");
        showButton.addActionListener(this);
        saveButton = new JButton("保存");
        saveButton.addActionListener(this);
        boxV = Box.createVerticalBox();
        boxH1 = Box.createHorizontalBox();
        boxH2 = Box.createHorizontalBox();
        boxH3 = Box.createHorizontalBox();
        boxH1.add(new JLabel("选择级别："));
        boxH1.add(Box.createHorizontalStrut(10));
        boxH1.add(comboBox);
        boxH2.add(new JLabel("秘籍在此："));
        boxH2.add(Box.createHorizontalStrut(10));
        boxH2.add(scrollPane);
        boxH3.add(showButton);
        boxH3.add(Box.createHorizontalStrut(10));
        boxH3.add(saveButton);
        boxV.add(Box.createVerticalStrut(40));
        boxV.add(boxH1);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(boxH2);
        boxV.add(Box.createVerticalStrut(10));
        boxV.add(boxH3);
        add(boxV);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(.txt)", "txt");
        fileChooser.setFileFilter(filter);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showButton) {
            if (comboBox.getSelectedItem() == grade1) {
                showMovement = new ShowMovement();
                showMovement.movePlate(3, 'A', 'B', 'C');
                answer.setText(showMovement.getS());//将秘籍内容显示到文本域中
            } else if (comboBox.getSelectedItem() == grade2) {
                showMovement = new ShowMovement();
                showMovement.movePlate(4, 'A', 'B', 'C');
                answer.setText(showMovement.getS());
            } else if (comboBox.getSelectedItem() == grade3) {
                showMovement = new ShowMovement();
                showMovement.movePlate(5, 'A', 'B', 'C');
                answer.setText(showMovement.getS());
            }
        } else if (e.getSource() == saveButton) {//实现文件目录的选择和保存的功能
            int state = fileChooser.showSaveDialog(this);
            if (state == JFileChooser.APPROVE_OPTION) {
                FileWriter fileWriter = null;
                BufferedWriter bufferedWriter = null;
                try {
                    File dir = fileChooser.getCurrentDirectory();
                    String name = fileChooser.getSelectedFile().getName();
                    File file = new File(dir, name);
                    fileWriter = new FileWriter(file);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(answer.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    try {
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}
