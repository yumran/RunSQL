package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class JdbcUtil {
	//private Logger logger = Logger.getLogger(JdbcUtil.class);
    private static String driverClass;     // 定义 数据库驱动
    private static String host;           // 定义 主机
    private static String port;           // 定义 端口
    private static String user;      	 // 定义 数据库用户 
    private static String password;     // 定义 数据库用户的密码
    private static String url;          // 定义 url
    
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    
    public JdbcUtil(String dataBase,String host,String port,String user,String password) {
    	init(dataBase,host,port,user,password);
    	host = JdbcUtil.host;
    	port = JdbcUtil.port;
    	user = JdbcUtil.user;
    	password = JdbcUtil.password;
    }
    
    public static void init(String dataBase,String host,String port,String user,String password) {
    	if(dataBase.equals("MYSQL")) {
    		driverClass = "com.mysql.jdbc.Driver";
    		url = "jdbc:mysql://"+host+":"+port;
    		System.out.println(url);
    	}else if(dataBase.equals("SQL SERVER")) {
    		driverClass = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    		url = "jdbc:sqlserver://"+host+":"+port;
    	}
    	user = JdbcUtil.user;
    	password = JdbcUtil.password;
    }
    
    private static void doLoadDriverClass(String driverClass) {
		// TODO 自动生成的方法存根
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.out.println("数据库驱动类加载异常");
			e.printStackTrace();
		} 
	}
    
    public static Connection getConnection() {
    	doLoadDriverClass(driverClass);
    	try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("获取数据库连接异常");
			e.printStackTrace();
		}
    	return conn;
    }
    
    public static Connection getConnection(String dataBase,String host,String port,String user,String password) {
    	init(dataBase,host,port,user,password);
    	doLoadDriverClass(driverClass);
    	try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("获取数据库连接异常");
			e.printStackTrace();
		}
    	return conn;
    }
    
    
    public static PreparedStatement getPStatement(String sql,Object...objects) {
    	try {
			pstmt = getConnection().prepareStatement(sql);
//			pstmt.setObject(1, sql);
			for(int i=0;i<objects.length;i++) {
				pstmt.setObject(i+1, objects[i]);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("获取数据库处理命令异常");
			e.printStackTrace();
		}
    	return pstmt;
    }
 
    
	public static ResultSet executeQuery(String sql,Object...objects) {
		pstmt = getPStatement(sql,objects);
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("SQL查询失败！");
			e.printStackTrace();
		} finally {
			close();
		}
		return rs;
	}
    
	public static int executeUpdate(String sql) {
		int rows = 0;
		try {
			rows = pstmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("SQL执行失败！");
			e.printStackTrace();
		} finally {
			close();
		}
		return rows;
	}
	
	public static String resultSetToJson(ResultSet rs) throws SQLException {  
	   // json数组  
	   JSONArray array = new JSONArray();  
	   // 获取列数  
	   ResultSetMetaData metaData = rs.getMetaData();
	   int columnCount = metaData.getColumnCount();  
	   // 遍历ResultSet中的每条数据  
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
	        }   
	        array.put(jsonObj);   
	    }  
	   return array.toString();  
	}  
	
	
    public static void close() {
    	try {
    		if(rs!=null) {
        		rs.close();
        	}
    		
    		if(pstmt!=null) {
    			pstmt.close();
    		}
    		if(conn!=null) {
    			conn.close();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("关闭数据库连接异常");
			e.printStackTrace();
		}
    }
}
