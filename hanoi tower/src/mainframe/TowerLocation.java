package mainframe;

import java.awt.*;

/**
 * 汉诺塔的可放置圆盘的位置相关信息
 * @author LanciLex
 * @create 2022-05-23 13:13
 */
public class TowerLocation {
    private int x, y;//塔上可放置圆盘的坐标位置
    private boolean isLocated;//存储这个位置上是否有圆盘放置
    private Plate plate;//放置这个位置上的圆盘

    public TowerLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getIsLocated() {
        return isLocated;
    }

    public void setIsLocated(boolean Located) {
        this.isLocated = Located;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public void placeIn(Component component, Container container) {//将圆盘放置在塔上
        plate = (Plate) component;
        container.setLayout(null);
        container.add(plate);
        int width = plate.getBounds().width;
        int height = plate.getBounds().height;
        plate.setBounds(getX() - width / 2, getY() - height / 2, width, height);
        setIsLocated(true);
        plate.setTowerLocation(this);//将圆盘与所在塔的可放置位置绑定，为后续鼠标操作做准备
        container.validate();
    }

    /**
     * 静态方法，判断两个位置是否一致
     * @param towerLocationA
     * @param towerLocationB
     * @return
     */
    public static boolean consistency(TowerLocation towerLocationA, TowerLocation towerLocationB) {
        if (towerLocationA.getX() == towerLocationB.getX() && towerLocationA.getY() == towerLocationB.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
