package custom.beans;


import custom.beans.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import javax.sql.*;
import com.ora.jsp.sql.*;
import com.ora.jsp.util.*;
import com.ora.jsp.sql.value.*;

/**
 * This is a utility bean which do the role as a special interface 
 * to a database.It contains methods for retrieving and updating admin information.
 */


public class AdminAccessBean implements Serializable{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	} 
	 
	 	
	 
	 /**
	 * See if the specified adminId and password match an admin in the database.
	 */
	public boolean authenticate(String adminId,String password)
		throws SQLException{
		
		AdminBean adminInfo=getAdmin(adminId);
		if(adminInfo != null && adminInfo.getPassword().equals(password)){
			return true;
		}
		return false;
	}

	 
	 
	 /** 
	 * Returns an AdminBean initialized with the information found
	 * in the database for the specified admin,or null if not found.
	 */
	public AdminBean getAdmin(String adminId) throws SQLException{
		
		//Get the admin info from the database
		Connection conn = dataSource.getConnection();
		Row adminRow = null;
		try{
			adminRow=getSingleValueProps(adminId,conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a AdminBean if the admin was found
		if(adminRow == null)
			//Not found
			return null;
			
			
		AdminBean adminInfo = new AdminBean();
		try{
			adminInfo.setAdminId(adminRow.getString("id"));
			adminInfo.setPassword(adminRow.getString("password"));					
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		return adminInfo;
	}
	 
	 
	 /** 
	 * Inserts the information about the specified admin,or 
	 * updates the information if it's already defined. 
	 */
	public void saveAdmin(AdminBean adminInfo) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValueProps(adminInfo,conn);
			conn.commit();
		}
		finally{
			try{
				conn.setAutoCommit(true);
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	}
	 
	 /**
	 * Inserts the information about the specified admin,
	 * or updates the information if it's already defined.
	 */
	private void saveSingleValueProps(AdminBean adminInfo,Connection conn)
		throws SQLException{
			
		if(adminInfo == null) return;
		
		StringBuffer sql = new StringBuffer();
		AdminBean dbInfo = getAdmin(adminInfo.getAdminId());
		if(dbInfo == null){
			// Use INSERT statement
			sql.append("INSERT INTO admins ")
				.append("(password,id) ")			
				.append("VALUES(?,?)");
				
		}
		else{
			// Use UPDATE statement
			sql.append("UPDATE admins SET password = ? WHERE id = ?");				
		}
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(adminInfo.getPassword()));
		values.addElement(new StringValue(adminInfo.getAdminId()));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}
	 
	 
	 
	 
	 
	 
	/**
	 * Returns a Row with all information about the specified
	 * admin,or null if not found.
	 */
	private Row getSingleValueProps(String adminId,Connection conn)
		throws SQLException{
		
		if(adminId == null) return null;
		
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM admins ")
			.append("WHERE id = ?");
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(adminId));
		sqlCommandBean.setValues(values);
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Admin not found
			return null;
		}
		return (Row)rows.firstElement();
	}

}