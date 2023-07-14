package mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 鼠标控制圆盘移动等操作的类
 *
 * @author LanciLex
 * @create 2022-05-24 19:30
 */
public class Movement implements MouseListener, MouseMotionListener {
    private TowerLocation[] towerLocationA, towerLocationB, towerLocationC;
    private TowerLocation startLocation, endLocation;
    private int x0, y0;
    private Container container;
    private boolean moveState;//表示当前鼠标点击的圆盘的可移动性

    public Movement(Container container) {
        this.container = container;
    }

    public void setTowerLocationA(TowerLocation[] towerLocationA) {
        this.towerLocationA = towerLocationA;
    }

    public void setTowerLocationB(TowerLocation[] towerLocationB) {
        this.towerLocationB = towerLocationB;
    }

    public void setTowerLocationC(TowerLocation[] towerLocationC) {
        this.towerLocationC = towerLocationC;
    }

    /**
     * 实现在组件上按下鼠标键触发的鼠标事件
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        moveState = false;//发现问题，如果此处没有这条语句的话将出错
        Plate plate = (Plate) e.getSource();
        startLocation = plate.getTowerLocation();
        //获取鼠标在事件源中的位置坐标
        x0 = e.getX();
        y0 = e.getY();
        int m;
        for (int i = 0; i < towerLocationA.length; i++) {
            if (TowerLocation.consistency(towerLocationA[i], startLocation)) {
                m = i;
                if (m > 0 && (towerLocationA[m - 1].getIsLocated() == false)) {
                    moveState = true;
                    break;
                } else if (m == 0) {
                    moveState = true;
                    break;
                }
            }
        }
        for (int i = 0; i < towerLocationB.length; i++) {
            if (TowerLocation.consistency(towerLocationB[i], startLocation)) {
                m = i;
                if (m > 0 && (towerLocationB[m - 1].getIsLocated() == false)) {
                    moveState = true;
                    break;
                } else if (m == 0) {
                    moveState = true;
                    break;
                }
            }
        }
        for (int i = 0; i < towerLocationC.length; i++) {
            if (TowerLocation.consistency(towerLocationC[i], startLocation)) {
                m = i;
                if (m > 0 && (towerLocationC[m - 1].getIsLocated() == false)) {
                    moveState = true;
                    break;
                } else if (m == 0) {
                    moveState = true;
                    break;
                }
            }
        }
    }

    /**
     * 实现拖动鼠标拉拽圆盘的操作方法
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Plate plate = (Plate) e.getSource();
        //获取圆盘左上角顶点的左边
        int leftX = plate.getBounds().x;
        int leftY = plate.getBounds().y;
        //获取鼠标的坐标
        int x = e.getX();
        int y = e.getY();
        leftX += x;
        leftY += y;
        if (moveState == true) {
            plate.setLocation(leftX - x0, leftY - y0);
        }
    }

    /**
     * 实现鼠标点击圆盘拖拽后又释放时的判定操作
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Plate plate = (Plate) e.getSource();
        Rectangle rectangle = plate.getBounds();
        boolean isLocated = false;//记录圆盘是否处于被放置状态
        int x, y;
        for (int i = 0; i < towerLocationA.length; i++) {
            x = towerLocationA[i].getX();
            y = towerLocationA[i].getY();
            if (rectangle.contains(x, y)) {
                endLocation = towerLocationA[i];
                if (i == towerLocationA.length - 1 && endLocation.getIsLocated() == false) {
                    isLocated = true;
                    break;
                } else if (i < towerLocationA.length - 1 && towerLocationA[i + 1].getIsLocated() == true
                        && endLocation.getIsLocated() == false
                        && towerLocationA[i + 1].getPlate().getWeight()
                        > plate.getWeight()) {
                    isLocated = true;
                    break;
                }
            }
        }
        for (int i = 0; i < towerLocationB.length; i++) {
            x = towerLocationB[i].getX();
            y = towerLocationB[i].getY();
            if (rectangle.contains(x, y)) {
                endLocation = towerLocationB[i];
                if (i == towerLocationB.length - 1 && endLocation.getIsLocated() == false) {
                    isLocated = true;
                    break;
                } else if (i < towerLocationB.length - 1 && towerLocationB[i + 1].getIsLocated() == true
                        && endLocation.getIsLocated() == false
                        && towerLocationB[i + 1].getPlate().getWeight()
                        > plate.getWeight()) {
                    isLocated = true;
                    break;
                }
            }
        }
        for (int i = 0; i < towerLocationC.length; i++) {
            x = towerLocationC[i].getX();
            y = towerLocationC[i].getY();
            if (rectangle.contains(x, y)) {
                endLocation = towerLocationC[i];
                if (i == towerLocationC.length - 1 && endLocation.getIsLocated() == false) {
                    isLocated = true;
                    break;
                } else if (i < towerLocationC.length - 1 && towerLocationC[i + 1].getIsLocated() == true
                        && endLocation.getIsLocated() == false
                        && towerLocationC[i + 1].getPlate().getWeight()
                        > plate.getWeight()) {
                    isLocated = true;
                    break;
                }
            }
        }

        if (endLocation != null && isLocated == true) {
            endLocation.placeIn(plate, container);
            startLocation.setIsLocated(false);
        } else {//如果此处不能放置圆盘，那么圆盘将回到一开始的位置
            startLocation.placeIn(plate, container);
        }

        //判断游戏成功的条件是：B塔或C塔的最上方被放置了圆盘
        if (towerLocationB[0].getIsLocated() == true || towerLocationC[0].getIsLocated() == true) {
            JOptionPane.showMessageDialog(null, "恭喜你完成了本次挑战", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
