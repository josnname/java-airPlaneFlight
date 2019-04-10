package RW飞机大战;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class Connection {
	Connection(){}
	public ResultSet Connection() {
		ResultSet sc = null;
		// 驱动程序名
	    String driver = "com.mysql.jdbc.Driver";
	    java.sql.Connection conn = null;
	    // URL指向要访问的数据库名scutcs
	    String url = "jdbc:mysql://127.0.0.1:3306/plane";

	    // MySQL配置时的用户名
	    String user = "root"; 
	    java.sql.PreparedStatement stmt = null;
	    // MySQL配置时的密码
	    String password = "123";

	    try { 
	        // 加载驱动程序
	        Class.forName(driver);
	    	 conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement用来执行SQL语句
//	            java.sql.Statement statement = conn.createStatement();

	            // 要执行的SQL语句
	            long currentTimeMillis = System.currentTimeMillis();
	            Time time = new Time(currentTimeMillis);
	            String sql = "select * from score order by score desc limit 15";
	           stmt = conn.prepareStatement(sql);
	           //在此 PreparedStatement 对象中执行 SQL 语句，该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，
	           //比如 INSERT、UPDATE 或 DELETE 语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。
	           sc=stmt.executeQuery(); 
	           
	           return sc;
	            
	    } catch(ClassNotFoundException e) {

            System.out.println("Sorry,can`t find the Driver!"); 
            e.printStackTrace();


           } catch(SQLException e) {


            e.printStackTrace();


           } catch(Exception e) {
            e.printStackTrace();
           }
	    try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return sc;
	}
	public Connection(int score){
		// 驱动程序名
	    String driver = "com.mysql.jdbc.Driver";

	    // URL指向要访问的数据库名scutcs
	    String url = "jdbc:mysql://127.0.0.1:3306/plane";

	    // MySQL配置时的用户名
	    String user = "root"; 

	    // MySQL配置时的密码
	    String password = "123";

	    try { 
	        // 加载驱动程序
	        Class.forName(driver);
	    	 java.sql.Connection conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement用来执行SQL语句
//	            java.sql.Statement statement = conn.createStatement();

	            // 要执行的SQL语句
	            long currentTimeMillis = System.currentTimeMillis();
	            Time time = new Time(currentTimeMillis);
	            String sql = "insert into score values(?,?,?);";
	           java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
	           stmt.setInt(1, score);
//	           stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
	           
	           stmt.setDate(2, new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));
	           stmt.setTime(3, time);
	            stmt.executeUpdate(); 
	            stmt.close();
	            conn.close();
	    } catch(ClassNotFoundException e) {

            System.out.println("Sorry,can`t find the Driver!"); 
            e.printStackTrace();


           } catch(SQLException e) {


            e.printStackTrace();


           } catch(Exception e) {
            e.printStackTrace();
           } 
	    
	}
}
