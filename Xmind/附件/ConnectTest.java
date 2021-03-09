package JDBCTest;

import java.sql.*;

/**
 * @author yangxu
 * @version 1.0
 * @date 2020/3/4 18:40
 */
public class ConnectTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("org.apache.hive.jdbc.HiveDriver");

        //创建连接  url,driverClass,username,password
        Connection connection = DriverManager.getConnection("jdbc:hive2://hadoop102:10000", "atguigu", "2222");

        //准备sql
        String sql="select * from work.dept";//表名要带前缀，指明属于哪个库，否则默认是default库

        PreparedStatement ps = connection.prepareStatement(sql);

        //执行查询
        ResultSet resultSet = ps.executeQuery();

        //遍历结果
        while (resultSet.next()){

            System.out.println("dname:"+resultSet.getString("name")+"   deptno:"+
                    resultSet.getInt("id"));
        }
        //关闭资源
        resultSet.close();
        ps.close();
        connection.close();
    }
}

