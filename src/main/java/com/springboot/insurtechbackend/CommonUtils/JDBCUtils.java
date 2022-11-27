//package com.springboot.insurtechbackend.CommonUtils;
//
////import com.alibaba.druid.pool.DruidDataSourceFactory;
//
//import javax.sql.DataSource;
//import java.io.InputStream;
//import java.sql.*;
//import java.util.Properties;
//
///*
//    1. Declare static data source member variables
//    2. Create a connection pool object
//    3. Define a public method of getting the data source
//    4. Define the method to get the connection object
//    5. Define the method to close the resource
// */
//
//public class JDBCUtils {
//	// 1.	Declare static data source member variables
//    static DataSource ds;
//
//
//
//    static Connection conn = null;
//    static String message;
//
//
//
//    public static  String getConnection() {
//        System.out.println("第一次进入getConnection()++");
//
//        try {
//
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            System.out.println("jdbc");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurancedbz?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false",
//                    "root", "123456");
//            message = "Successfull";
//            return message;
//        } catch (SQLException e) {
//            message = "unsuccessful";
//            return message;
//        } catch (Exception e) {
//            message = e.getMessage();
//            System.out.println("进入报错" + message);
//
//            return message;
//        }
//    }
//    public static Connection getConn() {
//        return conn;
//    }
//
//    public static void setConn(Connection conn) {
//        JDBCUtils.conn = conn;
//    }
//
//	// 5.Define a method to close a resource
//	public static void close(Connection conn, Statement stmt, ResultSet rs) {
//		if (rs != null) {
//			try {
//				rs.close();
//			} catch (SQLException e) {}
//		}
//
//		if (stmt != null) {
//			try {
//				stmt.close();
//			} catch (SQLException e) {}
//		}
//
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {}
//		}
//	}
//
//	// 6.Overload the close method
//	public static void close(Connection conn, Statement stmt) {
//		close(conn, stmt, null);
//	}
//}
