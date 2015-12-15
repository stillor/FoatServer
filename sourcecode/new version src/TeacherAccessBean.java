package custom.beans;


import custom.beans.*;
import custom.util.*;
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
 * to a database.It contains methods for retrieving and updating teacher information.
 */


public class TeacherAccessBean implements Serializable{
	private DataSource dataSource;
	private DatabaseAccessUtility dataAcc;
	final static String TABLENAME = "teachers";
	final static boolean PRINTFLAG = false;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		dataAcc.setDataSource(dataSource);
	} 
	 
	 //Constructor method
	public TeacherAccessBean(){
		dataAcc = new DatabaseAccessUtility();
	} 
	 
	
	 /**
	  * See if the specified teacherId and password match an teacher in the database.
	  */
	 public boolean authenticate(String teacherId,String password)
		throws SQLException{
		
		TeacherBean teacherInfo = getTeacher(teacherId);
		if(teacherInfo != null && teacherInfo.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	 
	 
	 
	 
	 /**
	** The method to update one ValueBean specified by the corresponding Prime Key(also other column value).
	*/
	public void deleteTeacherBean(String value1)throws SQLException{
		String sql = "DELETE FROM "+TABLENAME+" WHERE id = ?";
		Vector values = new Vector();
		values.addElement(value1);
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			System.out.printf("The TeacherBean that deleted:%n");
			System.out.printf("id=%s%n",value1);
			//...
		}
	}
	 
	 public Vector getAllTeacherBean()throws SQLException{
		return getAllTeachers();
	 }
	 
	 
	 /** 
	 * Returns an Vector of TeacherBean initialized with the information found
	 * in the database for all teachers,or null if not found.
	 */
	public Vector getAllTeachers( ) throws SQLException{
		
		//Get all class info from the database
		Connection conn = dataSource.getConnection();
		Vector teacherRows = null;
		try{
			teacherRows=getSingleValuePropsForAllTeachers(conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a TeacherBean if any teacher was found
		if(teacherRows == null){
			//Not found
			System.out.println("Not found any teacher");
			return null;
		}
		
		//deal with the Vector ROWs
		Vector teachers = new Vector();
		for(int i = 0;i < teacherRows.size();i++){
			Row tempRow = (Row)teacherRows.elementAt(i);
			TeacherBean tempTea = new TeacherBean();
			try{
				tempTea.setTeacherId(tempRow.getString("id"));
				tempTea.setPassword(tempRow.getString("password"));
				tempTea.setPhone(tempRow.getString("phone"));
				tempTea.setName(tempRow.getString("name"));										
				String detailStr = tempRow.getString("classes");
				tempTea.setClassesOfTeacherStr(detailStr);
				System.out.println("detailStr:"+detailStr);
				Vector detail = Utility.arrayToVector(detailStr.split(","));
				tempTea.setClassesOfTeacher(detail);				
				}
			catch(NoSuchColumnException nsce){}//Can not happen here
			teachers.addElement(tempTea);			
		}	
		return teachers;
	}
	 
	 
	 
	 
	 
	 
	 
	 public TeacherBean getTeacherBean(String value1)throws SQLException{
		return getTeacher(value1);
	 }
	 
	 
	 
	 
	 
	 /** 
	 * Returns an TeacherBean initialized with the information found
	 * in the database for the specified teacher,or null if not found.
	 */
	public TeacherBean getTeacher(String teacherId) throws SQLException{
		
		//Get the teacher info from the database
		Connection conn = dataSource.getConnection();
		Row teacherRow = null;
		try{
			teacherRow=getSingleValueProps(teacherId,conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a TeacherBean if the teacher was found
		if(teacherRow == null)
			//Not found
			return null;
			
			
		TeacherBean teacherInfo = new TeacherBean();
		try{
			teacherInfo.setTeacherId(teacherRow.getString("id"));
			teacherInfo.setPassword(teacherRow.getString("password"));
			teacherInfo.setPhone(teacherRow.getString("phone"));
			teacherInfo.setName(teacherRow.getString("name"));	
			String detailStr = teacherRow.getString("classes");
			teacherInfo.setClassesOfTeacherStr(detailStr);						
			System.out.println("detailStr:"+detailStr);
			Vector detail = Utility.arrayToVector(detailStr.split(","));
			teacherInfo.setClassesOfTeacher(detail);			
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		return teacherInfo;
	}
	 
	 public void updateTeacherBean(TeacherBean teacherInfo)throws SQLException{
		saveTeacher(teacherInfo);
	 }
	 public void insertTeacherBean(TeacherBean teacherInfo)throws SQLException{
		saveTeacher(teacherInfo);
	 }
	 /** 
	 * Inserts the information about the specified teacher,or 
	 * updates the information if it's already defined. 
	 */
	public void saveTeacher(TeacherBean teacherInfo) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValueProps(teacherInfo,conn);
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
	private void saveSingleValueProps(TeacherBean teacherInfo,Connection conn)
		throws SQLException{
			
		if(teacherInfo == null) return;
		
		StringBuffer sql = new StringBuffer();
		TeacherBean dbInfo = getTeacher(teacherInfo.getTeacherId());
		if(dbInfo == null){
			// Use INSERT statement
			sql.append("INSERT INTO teachers ")
				.append("(password,phone,name,classes,id) ")			
				.append("VALUES(?,?,?,?,?)");
				
		}
		else{
			// Use UPDATE statement
			sql.append("UPDATE teachers SET password = ?,phone = ?,name = ?,")
				.append("classes = ? WHERE id = ?");
		}
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());
		
		
		
		
		StringBuffer detailStrBuf = new StringBuffer();
		for(int i = 0 ; i < teacherInfo.getClassesOfTeacher().size() ; i++){
			String temp = (String)teacherInfo.getClassesOfTeacher().elementAt(i);
			detailStrBuf.append(temp);
			if(i!=teacherInfo.getClassesOfTeacher().size()-1)
			detailStrBuf.append(",");
		}

		
		System.out.println(detailStrBuf.toString());
		
		Vector values = new Vector();
		values.addElement(new StringValue(teacherInfo.getPassword()));
		values.addElement(new StringValue(teacherInfo.getPhone()));
		values.addElement(new StringValue(teacherInfo.getName()));
		values.addElement(new StringValue(teacherInfo.getClassesOfTeacherStr()));
		values.addElement(new StringValue(teacherInfo.getTeacherId()));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}
	 
	 
	 
	 
	 /**
	 * Returns a Vector of Rows with all information about all
	 * teachers,or null if not found.
	 */
	private Vector getSingleValuePropsForAllTeachers(Connection conn)
		throws SQLException{		
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM teachers ");
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);	
		sqlCommandBean.setSqlValue(sql.toString());
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){throw new SQLException("UnsupportedTypeException",e);}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Class not found
			return null;
		}
		return rows;
	} 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	/**
	 * Returns a Row with all information about the specified
	 * teacher,or null if not found.
	 */
	private Row getSingleValueProps(String teacherId,Connection conn)
		throws SQLException{
		
		if(teacherId == null) return null;
		
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM teachers ")
			.append("WHERE id = ?");
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(teacherId));
		sqlCommandBean.setValues(values);
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