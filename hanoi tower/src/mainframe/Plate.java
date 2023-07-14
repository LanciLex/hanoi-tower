package mainframe;

import javax.swing.*;
import java.awt.*;

/**
 * 定义了圆盘的相关属性
 * @author LanciLex
 * @create 2022-05-23 11:19
 */
public class Plate extends JButton {

    private int weight;//圆盘的权值，权值大的放在下面，另外权值越大的圆盘宽度越宽
    private TowerLocation towerLocation;//圆盘所在的塔的放置位置

    /**
     * 构造器，可以设置生成的圆盘按钮的背景颜色
     */
    public Plate() {
        this.setBackground(new Color(0, 0, 0));
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTowerLocation(TowerLocation towerLocation){//将圆盘与所在塔绑定，为后续鼠标操作做准备
        this.towerLocation = towerLocation;
    }

    public TowerLocation getTowerLocation(){
        return towerLocation;
    }
}

