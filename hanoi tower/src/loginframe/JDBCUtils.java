package loginframe;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 *
 * @author LanciLex
 * @create 2022-04-23 18:19
 */


public class JDBCUtils {

    /**
     * 获取数据库的连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        // 1.读取配置文件中的4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driverClass = pro.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }


    /**
     * 关闭连接和Statement的操作
     *
     * @param connection
     * @param ps
     */
    public static void closeResource(Connection connection, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //通用的增删改操作
    public static int commonUpdate(String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();

            //2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);

            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //4.执行
            //方式一：
            //ps.execute();
            /*如果执行的是查询操作，有返回结果，则此方法返回true
            如果执行的是增删改操作，没有返回结果，则此方法返回false*/
            //方式二：
            return ps.executeUpdate();//执行成功返回正数，执行失败返回0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(connection, ps);
        }
        return 0;
    }

    //重载方法，关闭资源操作，包含ResultSet
    public static void closeResource(Connection conn,Statement ps,ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
