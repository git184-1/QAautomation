package	com.lti.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lti.util.ConfigureLibrary;


public class SQLDriver {

	public static Connection conn;
	public static PreparedStatement preparedStatement;
	static Properties prop = ConfigureLibrary.getProp();
	public static ArrayList<DBrowTO> data;
	final static Logger logger = Logger.getLogger(com.lti.database.SQLDriver.class);
	
	public static void openConnection() {		
		try{
			conn =  DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("db_url")+":"+prop.getProperty("db_port")+"/"+prop.getProperty("db_name"), prop.getProperty("db_username"), prop.getProperty("db_password"));
			logger.info("Creating connection to database");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void createPreparedStatement(String sql){
		try {
			preparedStatement = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<DBrowTO> getData(String tableName) {
		logger.info("Retriving data from table "+tableName);
		data = new ArrayList<DBrowTO>();
		String query = "SELECT "+tableName+".key, "+tableName+".value, value_types.name as value_type FROM "+tableName+" INNER JOIN value_types ON "+tableName+".value_type=value_types.id";
		
		SQLDriver.openConnection();
		
		SQLDriver.createPreparedStatement(query);
		ResultSet result;
		try {
			
			result = preparedStatement.executeQuery();
			while(result.next()){
				DBrowTO temp = new DBrowTO(); 
				temp.setKey(result.getString("key"));
				temp.setValue(result.getString("value"));
				temp.setValueType(result.getString("VALUE_TYPE"));
				data.add(temp);
			}
		}catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
		    
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		    logger.info("Closing connection after fetching table's data");
		    
		}
			
		return data;
	}

	public static boolean saveToDB(String key, String value) {
		boolean flag = true;
		String query = "insert into outward_params (name, value) values (?, ?) ON DUPLICATE KEY UPDATE value = ?;";
		try {
			SQLDriver.openConnection();
			SQLDriver.createPreparedStatement(query);
			preparedStatement.setString (1, key);
			preparedStatement.setString (2, value);
			preparedStatement.setString (3, value);
			preparedStatement.execute();
			logger.info("Inserting "+key+" to 'outward_params' with value "+value);
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}finally {
		    
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		    logger.info("Closing connection after storing value to 'outward_params'");
		    
		}
		
		return flag;
		
	}

	public static String getFromOutparam(String key) {
		String value = new String();
		String query = "SELECT value from outward_params where name = ?";
		try {
			SQLDriver.openConnection();
			SQLDriver.createPreparedStatement(query);
			preparedStatement.setString (1, key);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()){
				value = result.getString("value");
			}
			logger.info("Fetching "+key+" value 'outward_params'");
		} catch (SQLException e) {
			logger.error("Error fetching value from 'outward_params'");
			e.printStackTrace();
		}finally {
		    
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		    logger.info("Closing connection after fetch value from 'outward_params'");
		    
		}
		
		return value;
	}
	
}
