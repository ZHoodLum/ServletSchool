package com.zdy.school.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * 
 * @author Psyduck
 *
 *DBC连接数据库    
 *创建一个以JDBC连接数据库的程序，包含7个步骤：    
 * 1、加载JDBC驱动程序：  
 * 2、提供JDBC连接的URL   
 * 3、创建数据库的连接  
 * 6、处理结果        两种情况：         1、执行更新返回的是本次操作影响到的记录数。         2、执行查询返回的结果是一个ResultSet对象。
 * 7、关闭JDBC对象          操作完成以后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声         明顺序相反：         1、关闭记录集         2、关闭声明         3、关闭连接对象   
 */
public class DBConnection {

	//连接数据库
	private static final String dbDriver = "com.mysql.jdbc.Driver";
	
	private static final String dbUrl = "jdbc:mysql://localhost:3306/school";


	private static final String dbName = "root";

	private static final String dbPwd = "root";

	private Connection conn = null;

	public Connection getConnection() {

		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbName, dbPwd);

		} catch (Exception e) {
            e.printStackTrace();
			System.out.println("连接成功");

		}
		return conn;
	}

	public void connClose() {

		if (conn != null)

			try {

				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("conn连接异常！");

			}

	}

}