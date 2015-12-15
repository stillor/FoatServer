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
 * to a database.It contains methods for retrieving and updating student information.
 */


public class StudentAccessBean implements Serializable{
	private DatabaseAccessUtility dataAcc;
	final static String TABLENAME = "students";
	final static boolean PRINTFLAG = false;
	
	private DataSource dataSource;

	
	//Constructor method
	public StudentAccessBean(){
		dataAcc = new DatabaseAccessUtility();
	} 
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		dataAcc.setDataSource(dataSource);
	} 
	 
	 
	 
	 	/**
	** The method to get one ValueBean which is selected
	** by the corresponding Prime Key(also other column value).
	** The parameter value1 means prime key.
	** Return null if not found. 
	*/
	public StudentBean getStudentBean(String value1)throws SQLException{
		String sql = "SELECT * FROM "+TABLENAME+" WHERE id = ? ORDER BY id";
		Vector values = new Vector();
		values.addElement(value1);//add the prime key
		Vector rows = dataAcc.selectRows(values,sql);
		Row row = null;
		if(rows != null && rows.size() != 0) row = (Row)rows.firstElement();
		else {
			if(PRINTFLAG == true){	
				System.out.printf("No StudentBean found:%n");
			}
			return null;
		}
		StudentBean valueBean = new StudentBean();
		try{
			valueBean.setStudentId(row.getString("id"));
			valueBean.setSex(row.getString("sex"));
			valueBean.setPhone(row.getString("phone"));
			valueBean.setName(row.getString("name"));			
			valueBean.setEmail(row.getString("email"));			
			//...
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		//Print info that got.
		if(PRINTFLAG == true){
			StudentBean tempBean = valueBean;
			System.out.printf("The StudentBean that got:%n");
			System.out.printf("id=%s%n",tempBean.getStudentId());
			System.out.printf("sex=%s%n",tempBean.getSex());
			System.out.printf("phone=%s%n",tempBean.getPhone());
			System.out.printf("name=%s%n",tempBean.getName());
			System.out.printf("email=%s%n",tempBean.getEmail());
			//...
		}
		return valueBean;			
	}
	
	/**
	** The method to get all rows in the table.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getAllStudentBean()throws SQLException{
		Vector valueBeans = new Vector();
		Vector values = new Vector();
		String sql = "SELECT * FROM "+TABLENAME+" ORDER BY id";
		Vector rows = dataAcc.selectRows(values,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No StudentBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			StudentBean tempValueBean = new StudentBean();
			try{
				tempValueBean.setStudentId(tempRow.getString("id"));
				tempValueBean.setSex(tempRow.getString("sex"));
				tempValueBean.setPhone(tempRow.getString("phone"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setEmail(tempRow.getString("email"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}				
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d ValueBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				StudentBean tempBean = (StudentBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getStudentId());
				System.out.printf("sex=%s%n",tempBean.getSex());
				System.out.printf("phone=%s%n",tempBean.getPhone());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("email=%s%n",tempBean.getEmail());
				//...
			}
		}
		return valueBeans;
	}
	
	/**
	** The method to get specified ValueBeans by the list of Prime Key(also other column value) of each ValueBean.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getStudentBeanByList(String list)throws SQLException{
		Vector valueBeans = new Vector();
		String sql = "SELECT * FROM "+TABLENAME+" WHERE id = ? ORDER BY id";
		Vector rows = dataAcc.selectRowsBySet(list,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No StudentBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			StudentBean tempValueBean = new StudentBean();
			try{
				tempValueBean.setStudentId(tempRow.getString("id"));
				tempValueBean.setSex(tempRow.getString("sex"));
				tempValueBean.setPhone(tempRow.getString("phone"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setEmail(tempRow.getString("email"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}	
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d StudentBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				StudentBean tempBean = (StudentBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getStudentId());
				System.out.printf("sex=%s%n",tempBean.getSex());
				System.out.printf("phone=%s%n",tempBean.getPhone());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("email=%s%n",tempBean.getEmail());
				//...
			}
		}
		return valueBeans;	
	}
	
	/**
	** The method to update one ValueBean specified by the parameter valueBean.
	*/
	public void updateStudentBean(StudentBean valueBean)throws SQLException{
		String sql = "UPDATE "+TABLENAME+" SET name = ?,sex = ?,phone = ?,email = ? WHERE id = ?";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getSex());
		values.addElement(valueBean.getPhone());
		values.addElement(valueBean.getEmail());
		values.addElement(valueBean.getStudentId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			StudentBean tempBean = valueBean;
			System.out.printf("The StudentBean that updated:%n");
			System.out.printf("id=%s%n",tempBean.getStudentId());
			//...
		}
	}
	
	/**
	** The method to insert one ValueBean specified by the parameter valueBean.
	*/
	public void insertStudentBean(StudentBean valueBean)throws SQLException{
		String sql = "INSERT INTO "+TABLENAME+" (name,sex,phone,email,id) VALUES(?,?,?,?,?)";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getSex());
		values.addElement(valueBean.getPhone());
		values.addElement(valueBean.getEmail());
		values.addElement(valueBean.getStudentId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			StudentBean tempBean = valueBean;
			System.out.printf("The StudentBean that inserted:%n");
			System.out.printf("id=%s%n",tempBean.getStudentId());
			//...
		}
	}
	
	/**
	** The method to update one ValueBean specified by the corresponding Prime Key(also other column value).
	*/
	public void deleteStudentBean(String value1)throws SQLException{
		String sql = "DELETE FROM "+TABLENAME+" WHERE id = ?";
		Vector values = new Vector();
		values.addElement(value1);
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			System.out.printf("The StudentBean that deleted:%n");
			System.out.printf("id=%s%n",value1);
			//...
		}
	}
	 
	 
	 
	 
	 
	 
	 /** 
	 * Returns an Vector of StudentBean initialized with the information found
	 * in the database for all students,or null if not found.
	 */
	public Vector getAllStudents( ) throws SQLException{
		
		//Get all student info from the database
		Connection conn = dataSource.getConnection();
		Vector studentRows = null;
		try{
			studentRows=getSingleValuePropsForAllStudents(conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a ClassBean if the class was found
		if(studentRows == null){
			//Not found
			System.out.println("Not found any student");
			return null;
		}
		
		//deal with the Vector ROWs
		Vector students = new Vector();
		for(int i = 0;i < studentRows.size();i++){
			Row tempRow = (Row)studentRows.elementAt(i);
			StudentBean tempStu = new StudentBean();
			try{
				tempStu.setStudentId(tempRow.getString("id"));			
				tempStu.setSex(tempRow.getString("sex"));
				tempStu.setPhone(tempRow.getString("phone"));
				tempStu.setEmail(tempRow.getString("email"));
				tempStu.setName(tempRow.getString("name"));		
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			students.addElement(tempStu);			
		}	
		return students;
	}
	 
	 
	 
	 
	 
	 
	 
	 /** 
	 * Returns an StudentBean initialized with the information found
	 * in the database for the specified student,or null if not found.
	 */
	public StudentBean getStudent(String studentId) throws SQLException{
		
		//Get the student info from the database
		Connection conn = dataSource.getConnection();
		Row studentRow = null;
		try{
			studentRow=getSingleValueProps(studentId,conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a StudentBean if the student was found
		if(studentRow == null)
			//Not found
			return null;
			
			
		StudentBean studentInfo = new StudentBean();
		try{
			studentInfo.setStudentId(studentRow.getString("id"));
			studentInfo.setSex(studentRow.getString("sex"));
			studentInfo.setPhone(studentRow.getString("phone"));
			studentInfo.setEmail(studentRow.getString("email"));
			studentInfo.setName(studentRow.getString("name"));		
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		return studentInfo;
	}
	 
	 
	 /** 
	 * Inserts the information about the specified student,or 
	 * updates the information if it's already defined. 
	 */
	public void saveStudent(StudentBean studentInfo) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValueProps(studentInfo,conn);
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
	 * Inserts the information about the specified student,
	 * or updates the information if it's already defined.
	 */
	private void saveSingleValueProps(StudentBean studentInfo,Connection conn)
		throws SQLException{
			
		if(studentInfo == null) return;
		
		StringBuffer sql = new StringBuffer();
		StudentBean dbInfo = getStudent(studentInfo.getStudentId());
		if(dbInfo == null){
			// Use INSERT statement
			sql.append("INSERT INTO students ")
				.append("(name,sex,phone,email,id) ")			
				.append("VALUES(?,?,?,?,?)");
				
		}
		else{
			// Use UPDATE statement
			sql.append("UPDATE students SET name = ?,sex = ?,phone = ?,email= ? WHERE id = ?");				
		}
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(studentInfo.getName()));
		values.addElement(new StringValue(studentInfo.getSex()));
		values.addElement(new StringValue(studentInfo.getPhone()));
		values.addElement(new StringValue(studentInfo.getEmail()));		
		values.addElement(new StringValue(studentInfo.getStudentId()));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}
	 
	 
	 /**
	 * Returns a Vector of Rows with all information about all
	 * students,or null if not found.
	 */
	private Vector getSingleValuePropsForAllStudents(Connection conn)
		throws SQLException{		
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM students ");
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);	
		sqlCommandBean.setSqlValue(sql.toString());
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){throw new SQLException("UnsupportedTypeException",e);}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// student not found
			return null;
		}
		return rows;
	} 
	 
	 
	 
	/**
	 * Returns a Row with all information about the specified
	 * student,or null if not found.
	 */
	private Row getSingleValueProps(String studentId,Connection conn)
		throws SQLException{
		
		if(studentId == null) return null;
		
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM students ")
			.append("WHERE id = ?");
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(studentId));
		sqlCommandBean.setValues(values);
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Student not found
			return null;
		}
		return (Row)rows.firstElement();
	}

}