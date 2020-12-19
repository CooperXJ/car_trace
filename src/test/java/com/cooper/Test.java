package com.cooper;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 导入大批量CSV文件
 *
 */
public class Test {
     
    /**
     * jdbc所属，暂不使用
     */
    private final static String url = "jdbc:mysql://localhost:3306/java_task?useSSL=true&characterEncoding=utf8";
    private final static String name = "root";
    private final static String pwd = "12345678";
    private static Connection conn;
    private static PreparedStatement ps;
     
    /**
     * 解析csv文件并插入到数据库中，暂不使用(jdbc)
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
         
        Test test = new Test();
         
//        // psBatch 时间统计 - 开始
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String startTime = sdf.format(new Date());
//        System.out.println("psBatch 开始时间为：" + startTime);
//        System.out.println("psBatch 开始执行...");
//
////        // 使用PreparedStatement批量插入
////        int idx = test.psBatch();
////
////        // 统计时间 - 结束
////        System.out.println("psBatch 执行完成，共插入" + idx + "条数据");
////        String endTime = sdf.format(new Date());
////        System.out.println("psBatch 结束时间为：" + endTime);
////
////        System.out.println();
//
//        // 时间统计 - 开始
//        startTime = sdf.format(new Date());
//        System.out.println("sqlBatch 开始时间为：" + startTime);
//        System.out.println("sqlBatch 开始执行...");
//
//        // 使用SQL语句批量插入
//        int idx = 192465600;
//
//        // 统计时间 - 结束
//        System.out.println("sqlBatch 执行完成，共插入" + idx + "条数据");
//        String endTime = sdf.format(new Date());
//        System.out.println("sqlBatch 结束时间为：" + endTime);
        test.testSelect();
    }
     
    /**
     * 使用PreparedStatement批量插入
     *
     * @return
     *
     * @throws Exception
     */
    private int psBatch() throws Exception {
         
        int idx = 0;// 行数
         
        try {
            // 读取txt文件
            FileInputStream fis = new FileInputStream("C:/Users/chen/Desktop/data/ceshi .csv");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
 
            String line;// 行数据
            String[] column = new String[4];// 列数据
             
            // 获取数据库连接
            conn = getConnection();
            // 设置不自动提交
            conn.setAutoCommit(false);
             
            // SQL
            String sql = "insert into test (name, `desc`, column1, column2, column3, column4) "
                    + "values (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
             
            while ((line = br.readLine()) != null) {// 循环读取每一行
                idx++;// 计数
                column = line.split(",");
                ps.setString(1, column[0]);
                if (column.length >= 2 && column[1] != null) {
                    ps.setString(2, column[1]);
                } else {
                    ps.setString(2, "");
                }
                if (column.length >= 3 && column[2] != null) {
                    ps.setString(3, column[2]);
                } else {
                    ps.setString(3, "");
                }
                if (column.length >= 4 && column[3] != null) {
                    ps.setString(4, column[3]);
                } else {
                    ps.setString(4, "");
                }
                ps.setString(5, "type");
                ps.setString(6, "1");
                ps.addBatch();
                if (idx % 10000 == 0) {
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
            if (idx % 10000 != 0) {
                ps.executeBatch();
                conn.commit();
                ps.clearBatch();
            }
        } catch (Exception e) {
            System.out.println("第" + idx + "前一万条数据插入出错...");
        } finally {
            try {
                if (ps != null) {
                    // 关闭连接
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
         
        return idx;
         
    }
     
    /**
     * 使用sql语句批量插入
     * @Author 薛进
     * @return
     *
     * @throws Exception
     */
    private int sqlBatch() {
         
        int idx = 0;// 行数
         
        try {
             
            // 读取CSV文件
            FileInputStream fis = new FileInputStream("/Users/cooper/Desktop/test.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
             
            String line;// 行数据
            String[] column = new String[9];// 列数据
             
            // 获取数据库连接
            conn = getConnection();
             
            // SQL
            StringBuffer sql = new StringBuffer("insert into car_trace (car_flag, trigger_event, status, time, longitude, latitude,speed,position,GPS_flag) "
                    + "values ");
             
            while ((line = br.readLine()) != null) {// 循环读取每一行
                idx++;// 计数
                column = line.split(",");
                sql.append("('" + column[0] + "', '");
                if (column.length >= 2 && column[1] != null) {
                    sql.append(column[1] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 3 && column[2] != null) {
                    sql.append(column[2] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 4 && column[3] != null) {
                    sql.append(column[3] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 5 && column[4] != null) {
                    sql.append(column[4] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 6 && column[5] != null) {
                    sql.append(column[5] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 7 && column[6] != null) {
                    sql.append(column[6] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 8 && column[7] != null) {
                    sql.append(column[7] + "', '");
                } else {
                    sql.append("', '");
                }
                if (column.length >= 9 && column[8] != null) {
                    sql.append(column[8] + "' ");
                } else {
                    sql.append("', '");
                }
                sql.append("),");
                if (idx % 10000 == 0) {
                    String executeSql = sql.toString().substring(0, sql.toString().lastIndexOf(","));
                    ps = conn.prepareStatement(executeSql);
                    ps.executeUpdate();
                    sql = new StringBuffer("insert into car_trace (car_flag, trigger_event, status, time, longitude, latitude,speed,position,GPS_flag) "
                            + "values ");
                }
            }
            if (idx % 10000 != 0) {
                String executeSql = sql.toString().substring(0, sql.toString().lastIndexOf(","));
//                System.out.println(sql);
                ps = conn.prepareStatement(executeSql);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("第" + idx + "前一万条数据插入出错...");
        } finally {
            try {
                if (ps != null) {
                    // 关闭连接
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
 
        return idx;
         
    }
     
    /**
     * 获取数据库连接
     *
     * @param sql
     *              SQL语句
     */
    private Connection getConnection() throws Exception {
         
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection(url, name, pwd);
        return conn;
         
    }


    void testSelect() throws Exception {
        // 获取数据库连接
        conn = getConnection();
        String sql = "select count(*),Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\")) from car_trace\n" +
                "where Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\"))<=(select max(Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\"))) from car_trace) and Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\"))>=(select min(Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\"))) from car_trace)\n" +
                "and car_flag=194715 and trigger_event=1\n" +
                "group by Date(STR_TO_DATE(time,\"%Y%m%d%H%i%s\"));";
        ps = conn.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+"---"+resultSet.getDate(2));
        }
    }
}