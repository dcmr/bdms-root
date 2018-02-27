package com.bdms.hive.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * @author Administrator
 * 
 *${HIVE_HOME}/bin/hiveserver2  HiveServer2
 *   $ hive --service hiveserver2
 *
 */
public class HiveJDBC {
	
	//private static final Logger LOG = Logger.getLogger(HiveJDBCTest.class);
	private static Connection conn ;
	
	public void testSearch() throws SQLException{
		
		try {
			//Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			//conn = DriverManager.getConnection("jdbc:hive://Hadoop-9:10000/default","hive","");
			conn = DriverManager.getConnection("jdbc:hive2://Hadoop-1:10000/user_action","hive","");
			
		} catch (ClassNotFoundException e) {
			System.err.println("hive加载jdbc驱动HiveDriver失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String hql = "select * from log limit 10";
		
		PreparedStatement prepareStatement = conn.prepareStatement(hql);
		prepareStatement.execute();
		
		ResultSet resultSet = prepareStatement.getResultSet();
		
		while(resultSet.next()){
			System.out.println(resultSet.getString(1) + " -- > " + resultSet.getString(2));
		}
		
	}
	public static void main(String[] args) throws SQLException {
		
		HiveJDBC ht = new HiveJDBC();
		ht.testSearch();
	}

}
