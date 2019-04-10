package RW�ɻ���ս;

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
		// ����������
	    String driver = "com.mysql.jdbc.Driver";
	    java.sql.Connection conn = null;
	    // URLָ��Ҫ���ʵ����ݿ���scutcs
	    String url = "jdbc:mysql://127.0.0.1:3306/plane";

	    // MySQL����ʱ���û���
	    String user = "root"; 
	    java.sql.PreparedStatement stmt = null;
	    // MySQL����ʱ������
	    String password = "123";

	    try { 
	        // ������������
	        Class.forName(driver);
	    	 conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement����ִ��SQL���
//	            java.sql.Statement statement = conn.createStatement();

	            // Ҫִ�е�SQL���
	            long currentTimeMillis = System.currentTimeMillis();
	            Time time = new Time(currentTimeMillis);
	            String sql = "select * from score order by score desc limit 15";
	           stmt = conn.prepareStatement(sql);
	           //�ڴ� PreparedStatement ������ִ�� SQL ��䣬����������һ�� SQL ���ݲ������ԣ�Data Manipulation Language��DML����䣬
	           //���� INSERT��UPDATE �� DELETE ��䣻�������޷������ݵ� SQL ��䣬���� DDL ��䡣
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
		// ����������
	    String driver = "com.mysql.jdbc.Driver";

	    // URLָ��Ҫ���ʵ����ݿ���scutcs
	    String url = "jdbc:mysql://127.0.0.1:3306/plane";

	    // MySQL����ʱ���û���
	    String user = "root"; 

	    // MySQL����ʱ������
	    String password = "123";

	    try { 
	        // ������������
	        Class.forName(driver);
	    	 java.sql.Connection conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement����ִ��SQL���
//	            java.sql.Statement statement = conn.createStatement();

	            // Ҫִ�е�SQL���
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
