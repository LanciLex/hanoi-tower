package mainframe;

import javax.swing.*;
import java.awt.*;

/**
 * 汉诺塔游戏的主要图形绘制
 * @author LanciLex
 * @create 2022-05-23 13:34
 */
public class TowerView extends JPanel {
    private int plateNumber;//圆盘的个数
    private int plateMaxWidth, plateMinWidth, plateHeight;//圆盘的最大宽度、最小宽度与高度
    private Plate[] plates;
    private TowerLocation[] towerLocationA, towerLocationB, towerLocationC;//三座塔的对象数组，数组中的每个元素是可放置圆盘的位置
    private Movement movement;

    public TowerView() {
        movement = new Movement(this);
        setLayout(null);//这样可以使用自定义布局
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public int getPlateMaxWidth() {
        return plateMaxWidth;
    }

    public void setPlateMaxWidth(int plateMaxWidth) {
        this.plateMaxWidth = plateMaxWidth;
    }

    public int getPlateMinWidth() {
        return plateMinWidth;
    }

    public void setPlateMinWidth(int plateMinWidth) {
        this.plateMinWidth = plateMinWidth;
    }

    public int getPlateHeight() {
        return plateHeight;
    }

    public void setPlateHeight(int plateHeight) {
        this.plateHeight = plateHeight;
    }

    public Plate[] getPlates() {
        return plates;
    }

    /**
     * 初始化塔和圆盘的属性
     */
    public void init() {
        removeAll();//发现问题：如果不清除之前的圆盘，那么在选择挑战段位或者点击重置后，会造成显示错误
        plates = new Plate[getPlateNumber()];
        towerLocationA = new TowerLocation[getPlateNumber()];
        towerLocationB = new TowerLocation[getPlateNumber()];
        towerLocationC = new TowerLocation[getPlateNumber()];
        int length = (getPlateMaxWidth() - getPlateMinWidth()) / getPlateNumber();//求出每一小份的长度
        for (int i = 0; i < getPlateNumber(); i++) {
            plates[i] = new Plate();
            plates[i].setWeight(i);
            plates[i].setSize(getPlateMinWidth() + i * length, getPlateHeight());//设置每个圆盘的宽度与高度

            //给每个圆盘事件源注册监视器
            plates[i].addMouseListener(movement);
            plates[i].addMouseMotionListener(movement);

            //分别设置每个塔可以放置圆盘的位置
            towerLocationA[i] = new TowerLocation(getPlateMaxWidth(), 100 + (i + 1) * getPlateHeight());
            towerLocationB[i] = new TowerLocation(2 * getPlateMaxWidth(), 100 + (i + 1) * getPlateHeight());
            towerLocationC[i] = new TowerLocation(3 * getPlateMaxWidth(), 100 + (i + 1) * getPlateHeight());

            towerLocationA[i].placeIn(plates[i], this);//初始情况是在塔A上放置圆盘
        }
        movement.setTowerLocationA(towerLocationA);
        movement.setTowerLocationB(towerLocationB);
        movement.setTowerLocationC(towerLocationC);

        //发现问题：如果不重新绘制的话，重置后会发现上次图形残留
        repaint();//重绘

    }

    /**
     * 重写父类中的paintComponent(Graphics g)方法，绘制塔A、B和C的中心柱子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//发现问题，如果此处没有这个语句，图形将会出现错误
        int xA = towerLocationA[0].getX();
        int y0 = towerLocationA[0].getY() - getPlateHeight() / 2;
        int y1 = towerLocationA[towerLocationA.length - 1].getY() + getPlateHeight() / 2;
        g.setColor(new Color(128, 133, 139));//设置画笔的颜色
        g.fillRect(xA - 5, y0 - 20, 10, y1 - y0 + 20);//画出塔A中心的矩形柱子
        //画出塔B中心的柱子
        int xB = towerLocationB[0].getX();
        g.fillRect(xB - 5, y0 - 20, 10, y1 - y0 + 20);
        //画出塔C中心的柱子
        int xC = towerLocationC[0].getX();
        g.fillRect(xC - 5, y0 - 20, 10, y1 - y0 + 20);
    }
}
