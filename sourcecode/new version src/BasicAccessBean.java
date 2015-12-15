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
 * to a database.It contains methods for retrieving and updating basic information.
 */


public class BasicAccessBean implements Serializable{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	} 
	
	
	
	
	
	
	
	
	
	
	/** 
	 * Inserts the information about the specified teacher,or 
	 * updates the information if it's already defined. 
	 */
	public void saveAdminPhone(String adminPhone) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValuePropsForAdminPhone(adminPhone,conn);
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
	 * Inserts the information about the specified teacher,
	 * or updates the information if it's already defined.
	 */
	private void saveSingleValuePropsForAdminPhone(String adminPhone,Connection conn)
		throws SQLException{			
		
		if(adminPhone == null) return;		
		
		StringBuffer sql = new StringBuffer();		
		// Use UPDATE statement
		sql.append("UPDATE basicinfo SET adminPhone = ?")
			.append("WHERE id = 000001");		
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());		
		Vector values = new Vector();
		values.addElement(new StringValue(adminPhone));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 
	 * Returns an TeacherBean initialized with the information found
	 * in the database for the specified teacher,or null if not found.
	 */
	public String getAdminPhone() throws SQLException{
		
		//Get the teacher info from the database
		Connection conn = dataSource.getConnection();
		Row basicRow = null;
		try{
			basicRow=getSingleValueProps(conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a TeacherBean if the teacher was found
		if(basicRow == null)
			//Not found
			return null;
			
			
		String adminPhone = null;
		try{
			adminPhone = basicRow.getString("adminPhone");			
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		return adminPhone;
	}
	 
	/**
	 * Returns a Row with all information about the specified
	 * teacher,or null if not found.
	 */
	private Row getSingleValueProps(Connection conn)
		throws SQLException{
		
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM basicinfo ")
			.append("WHERE id = 000001");
		sqlCommandBean.setSqlValue(sql.toString());
		//Vector values = new Vector();
		//values.addElement(new StringValue(teacherId));
		//sqlCommandBean.setValues(values);
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Teacher not found
			return null;
		}
		return (Row)rows.firstElement();
	}
	 
	 
}