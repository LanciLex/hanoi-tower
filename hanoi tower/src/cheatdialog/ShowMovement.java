package cheatdialog;

/**
 * 实现在文本域中显示操作秘籍的类
 * @author LanciLex
 * @create 2022-05-26 20:33
 */
public class ShowMovement {
    private int moveTimes;//记录移动的次数
    private String s="操作如下：";//显示秘籍操作的内容字符串

    public int getMoveTimes() {
        return moveTimes;
    }

    public void setMoveTimes(int moveTimes) {
        this.moveTimes = moveTimes;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    //记录圆盘移动记录的方法
    public  void showMovePlate(int plateNumber, char x, char y) {
        s += "\n"+"第" + (++moveTimes) + "次移动：" + "把" + plateNumber + "号圆盘从" + x + "移到->" + y;
    }

    /**
     * 递归实现圆盘正确移动的方法
     * @param plateNumber 圆盘数量
     * @param a 圆盘a的名字
     * @param b 圆盘b的名字
     * @param c 圆盘c的名字
     */
    public  void movePlate(int plateNumber, char a, char b, char c) {
        if (plateNumber == 1) {
            showMovePlate(1, a, c);//当圆盘只有一个时，将圆盘从a移动到c
        } else {
            //递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            movePlate(plateNumber - 1, a, c, b);
            //把A塔上编号为n的圆盘移到C上
            showMovePlate(plateNumber, a, c);
            //递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
            movePlate(plateNumber - 1, b, a, c);
        }
    }
}
