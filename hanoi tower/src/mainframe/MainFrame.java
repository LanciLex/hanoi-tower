package mainframe;

import cheatdialog.CheatDialog;
import feedbackdialog.FeedbackDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏主界面框架
 * @author LanciLex
 * @create 2022-05-23 15:44
 */
public class MainFrame extends JFrame implements ActionListener {
    TowerView towerView;
    JMenuBar menuBar;
    JMenu selectGrade, gameSettings, helpMenu;
    JMenuItem grade1, grade2, grade3;
    JMenuItem reset, setColor;
    JMenuItem cheat, feedback;


    public MainFrame() {
        setTitle("汉诺塔小游戏");
        setIconImage(new ImageIcon("img\\hanoi.png").getImage());//设置软件图标
        towerView = new TowerView();
        towerView.setPlateNumber(3);
        towerView.setPlateMaxWidth(200);
        towerView.setPlateMinWidth(110);
        towerView.setPlateHeight(30);
        towerView.init();
        add(towerView, BorderLayout.CENTER);
        add(new MusicPlay(), BorderLayout.SOUTH);
        selectGrade = new JMenu("挑战段位");
        gameSettings = new JMenu("游戏设置");
        helpMenu = new JMenu("用户帮助");
        grade1 = new JMenuItem("青铜", new ImageIcon("img\\grade01.png"));
        grade1.addActionListener(this);
        grade2 = new JMenuItem("白银", new ImageIcon("img\\grade02.png"));
        grade2.addActionListener(this);
        grade3 = new JMenuItem("黄金", new ImageIcon("img\\grade03.png"));
        grade3.addActionListener(this);
        reset = new JMenuItem("重置游戏");
        reset.addActionListener(this);
        setColor = new JMenuItem("圆盘颜色");
        setColor.addActionListener(this);
        cheat = new JMenuItem("查看秘籍");
        cheat.addActionListener(this);
        feedback = new JMenuItem("提交反馈");
        feedback.addActionListener(this);
        selectGrade.add(grade1);
        selectGrade.add(grade2);
        selectGrade.add(grade3);
        gameSettings.add(reset);
        gameSettings.add(setColor);
        helpMenu.add(cheat);
        helpMenu.add(feedback);
        menuBar = new JMenuBar();
        menuBar.add(selectGrade);
        menuBar.add(gameSettings);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        setVisible(true);
        setBounds(400, 100, 800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == grade1) {
            towerView.setPlateNumber(3);
            towerView.init();
        } else if (e.getSource() == grade2) {
            towerView.setPlateNumber(4);
            towerView.init();
        } else if (e.getSource() == grade3) {
            towerView.setPlateNumber(5);
            towerView.init();
        } else if (e.getSource() == reset) {
            int plateNumber = towerView.getPlateNumber();
            towerView.setPlateNumber(plateNumber);
            towerView.init();
        } else if (e.getSource() == setColor) {
            Plate[] plates = towerView.getPlates();
            Color newColor = JColorChooser.showDialog(this, "设置圆盘颜色", Color.BLACK);
            if (newColor != null) {
                for (int i = 0; i < plates.length; i++) {
                    plates[i].setBackground(newColor);
                }
            }
        } else if (e.getSource() == cheat) {
            CheatDialog cheatDialog = new CheatDialog();
        } else if (e.getSource() == feedback) {
            FeedbackDialog feedbackDialog = new FeedbackDialog();
        }
    }
}
