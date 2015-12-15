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
 * to a database.It contains methods for retrieving and updating class information.
 */


public class CourseAccessBean implements Serializable{
	private DatabaseAccessUtility dataAcc;
	final static String INSTANCETABLENAME = "courses";
	final static String INFOTABLENAME = "courseinfo";
	final static boolean PRINTFLAG = false;
	
	private DataSource dataSource;

	//Constructor method
	public CourseAccessBean(){
		dataAcc = new DatabaseAccessUtility();
	} 
	
	

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		dataAcc.setDataSource(dataSource);
	} 
	 
	 
	 
	 
	 
	 
	 public void generateCourseInstance(String CID)throws SQLException{
		CourseBean validCourse = null; 
		//get valid course
		validCourse = getCourseInfoBean(CID);
		
		Vector times = Utility.arrayToVector(validCourse.getTime().split(","));
		if(validCourse.getTime().equals("")){
			times = new Vector();
		}
		Vector students = Utility.arrayToVector(validCourse.getStudents().split(","));
		if(validCourse.getStudents().equals("")){
			students = new Vector();
		}
		for(int i = 0;i<students.size();i++){
			String tempStu = (String)students.elementAt(i);
			tempStu = tempStu+"|1";
			students.setElementAt(tempStu,i);
		}
		String newStudentsList = Utility.vectorToString(students);
		System.out.println("New Generate Studentlist:"+newStudentsList);
		
		for(int i =0 ;i<times.size();i++){
			String tempTime = (String)times.elementAt(i);
			String index = tempTime.substring(1,2);
			String instanceCID = Utility.generateCID(CID,tempTime.substring(0,1),"0"+index);	
			System.out.println("New Generate instanceCID:"+instanceCID);
			//exam instanceCourse existence
			CourseBean validInstanceCourse = null; 
			validInstanceCourse = getCourseInstanceBean(instanceCID);		
			if(validInstanceCourse == null) {	
				CourseBean newInstanceCourse = new CourseBean(); 	
				newInstanceCourse.setClassId(instanceCID);		
				newInstanceCourse.setName(validCourse.getName());		
				newInstanceCourse.setTime(tempTime.substring(1,3));	
				newInstanceCourse.setTeacherName(validCourse.getTeacherName());	
				newInstanceCourse.setStudents(newStudentsList);	
				newInstanceCourse.setTotal(Integer.toString(students.size()));	
				newInstanceCourse.setAttend("0");	
				newInstanceCourse.setAbsent(Integer.toString(students.size()));	
				newInstanceCourse.setLeave("0");	
				insertCourseInstanceBean(newInstanceCourse);
			}
				
		}	
	}
	 
	public void generateAllCourseInstance()throws SQLException{
		Vector courses = getAllCourseInfoBean();
		
		for(int i =0 ;i<courses.size();i++){
			CourseBean tempCour = (CourseBean)courses.elementAt(i);
			generateCourseInstance(tempCour.getClassId());
		}
			
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	** The method to get one ValueBean which is selected
	** by the corresponding Prime Key(also other column value).
	** The parameter value1 means prime key.
	** Return null if not found. 
	*/
	public CourseBean getCourseInstanceBean(String value1)throws SQLException{
		String sql = "SELECT * FROM "+INSTANCETABLENAME+" WHERE id = ? ORDER BY time";
		Vector values = new Vector();
		values.addElement(value1);//add the prime key
		Vector rows = dataAcc.selectRows(values,sql);
		Row row = null;
		if(rows != null && rows.size() != 0) row = (Row)rows.firstElement();
		else {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
		}
		CourseBean valueBean = new CourseBean();
		try{
			valueBean.setClassId(row.getString("id"));
			valueBean.setName(row.getString("name"));
			valueBean.setTime(row.getString("time"));
			valueBean.setTeacherName(row.getString("teacher_name"));
			valueBean.setStudents(row.getString("detail"));
			valueBean.setTotal(row.getString("totalNum"));
			valueBean.setAttend(row.getString("attendNum"));
			valueBean.setAbsent(row.getString("absentNum"));
			valueBean.setLeave(row.getString("leaveNum"));
			//...
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		//Print info that got.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that got:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			System.out.printf("name=%s%n",tempBean.getName());
			System.out.printf("time=%s%n",tempBean.getTime());
			System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
			System.out.printf("detail=%s%n",tempBean.getStudents());
			System.out.printf("totalNum=%s%n",tempBean.getTotal());
			System.out.printf("attendNum=%s%n",tempBean.getAttend());
			System.out.printf("absentNum=%s%n",tempBean.getAbsent());
			System.out.printf("leaveNum=%s%n",tempBean.getLeave());
			//...
		}
		return valueBean;			
	}
	 
	 /**
	** The method to get one ValueBean which is selected
	** by the corresponding Prime Key(also other column value).
	** The parameter value1 means prime key.
	** Return null if not found. 
	*/
	public CourseBean getCourseInfoBean(String value1)throws SQLException{
		String sql = "SELECT * FROM "+INFOTABLENAME+" WHERE id = ? ORDER BY id";
		Vector values = new Vector();
		values.addElement(value1);//add the prime key
		Vector rows = dataAcc.selectRows(values,sql);
		Row row = null;
		if(rows != null && rows.size() != 0) row = (Row)rows.firstElement();
		else {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
		}
		CourseBean valueBean = new CourseBean();
		try{
			valueBean.setClassId(row.getString("id"));
			valueBean.setName(row.getString("name"));
			valueBean.setTime(row.getString("time"));
			valueBean.setTeacherName(row.getString("teacher_name"));
			valueBean.setStudents(row.getString("detail"));
			//...
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		//Print info that got.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that got:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			System.out.printf("name=%s%n",tempBean.getName());
			System.out.printf("time=%s%n",tempBean.getTime());
			System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
			System.out.printf("detail=%s%n",tempBean.getStudents());
			//...
		}
		return valueBean;			
	}
	 
	/**
	** The method to get all rows in the table.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getAllCourseInstanceBean()throws SQLException{
		Vector valueBeans = new Vector();
		Vector values = new Vector();
		String sql = "SELECT * FROM "+INSTANCETABLENAME+" ORDER BY time";
		Vector rows = dataAcc.selectRows(values,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			CourseBean tempValueBean = new CourseBean();
			try{
				tempValueBean.setClassId(tempRow.getString("id"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setTime(tempRow.getString("time"));
				tempValueBean.setTeacherName(tempRow.getString("teacher_name"));
				tempValueBean.setStudents(tempRow.getString("detail"));
				tempValueBean.setTotal(tempRow.getString("totalNum"));
				tempValueBean.setAttend(tempRow.getString("attendNum"));
				tempValueBean.setAbsent(tempRow.getString("absentNum"));
				tempValueBean.setLeave(tempRow.getString("leaveNum"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}				
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d CourseBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				CourseBean tempBean = (CourseBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getClassId());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("time=%s%n",tempBean.getTime());
				System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
				System.out.printf("detail=%s%n",tempBean.getStudents());
				System.out.printf("totalNum=%s%n",tempBean.getTotal());
				System.out.printf("attendNum=%s%n",tempBean.getAttend());
				System.out.printf("absentNum=%s%n",tempBean.getAbsent());
				System.out.printf("leaveNum=%s%n",tempBean.getLeave());
				//...
			}
		}
		return valueBeans;
	}
	
	/**
	** The method to get all rows in the table.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getAllCourseInfoBean()throws SQLException{
		Vector valueBeans = new Vector();
		Vector values = new Vector();
		String sql = "SELECT * FROM "+INFOTABLENAME+" ORDER BY id";
		Vector rows = dataAcc.selectRows(values,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			CourseBean tempValueBean = new CourseBean();
			try{
				tempValueBean.setClassId(tempRow.getString("id"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setTime(tempRow.getString("time"));
				tempValueBean.setTeacherName(tempRow.getString("teacher_name"));
				tempValueBean.setStudents(tempRow.getString("detail"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}				
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d CourseBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				CourseBean tempBean = (CourseBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getClassId());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("time=%s%n",tempBean.getTime());
				System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
				System.out.printf("detail=%s%n",tempBean.getStudents());
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
	public Vector getCourseInstanceBeanByList(String list)throws SQLException{
		Vector valueBeans = new Vector();
		String sql = "SELECT * FROM "+INSTANCETABLENAME+" WHERE id = ? ORDER BY time";
		Vector rows = dataAcc.selectRowsBySet(list,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			CourseBean tempValueBean = new CourseBean();
			try{
				tempValueBean.setClassId(tempRow.getString("id"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setTime(tempRow.getString("time"));
				tempValueBean.setTeacherName(tempRow.getString("teacher_name"));
				tempValueBean.setStudents(tempRow.getString("detail"));
				tempValueBean.setTotal(tempRow.getString("totalNum"));
				tempValueBean.setAttend(tempRow.getString("attendNum"));
				tempValueBean.setAbsent(tempRow.getString("absentNum"));
				tempValueBean.setLeave(tempRow.getString("leaveNum"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}	
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d CourseBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				CourseBean tempBean = (CourseBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getClassId());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("time=%s%n",tempBean.getTime());
				System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
				System.out.printf("detail=%s%n",tempBean.getStudents());
				System.out.printf("totalNum=%s%n",tempBean.getTotal());
				System.out.printf("attendNum=%s%n",tempBean.getAttend());
				System.out.printf("absentNum=%s%n",tempBean.getAbsent());
				System.out.printf("leaveNum=%s%n",tempBean.getLeave());
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
	public Vector getCourseInfoBeanByList(String list)throws SQLException{
		Vector valueBeans = new Vector();
		String sql = "SELECT * FROM "+INFOTABLENAME+" WHERE id = ? ORDER BY id";
		Vector rows = dataAcc.selectRowsBySet(list,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No CourseBean found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			CourseBean tempValueBean = new CourseBean();
			try{
				tempValueBean.setClassId(tempRow.getString("id"));
				tempValueBean.setName(tempRow.getString("name"));
				tempValueBean.setTime(tempRow.getString("time"));
				tempValueBean.setTeacherName(tempRow.getString("teacher_name"));
				tempValueBean.setStudents(tempRow.getString("detail"));
				//...
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}	
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d CourseBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				CourseBean tempBean = (CourseBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("id=%s%n",tempBean.getClassId());
				System.out.printf("name=%s%n",tempBean.getName());
				System.out.printf("time=%s%n",tempBean.getTime());
				System.out.printf("teacher_name=%s%n",tempBean.getTeacherName());
				System.out.printf("detail=%s%n",tempBean.getStudents());
				//...
			}
		}
		return valueBeans;	
	}
	
	
	/**
	** The method to update one ValueBean specified by the parameter valueBean.
	*/
	public void updateCourseInstanceBean(CourseBean valueBean)throws SQLException{
		String sql = "UPDATE "+INSTANCETABLENAME+" SET name = ?,time = ?,teacher_name = ?,totalNum = ?,attendNum = ?,absentNum = ?,leaveNum = ?,detail = ? WHERE id = ?";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getTime());
		values.addElement(valueBean.getTeacherName());
		Vector students = Utility.arrayToVector(valueBean.getStudents().split(","));
		int attendNum = 0;
		int absentNum = 0;
		int leaveNum = 0;
		for(int i = 0;i<students.size();i++){
			String tempStr = (String)students.elementAt(i);
			tempStr = tempStr.substring(11,12);
			switch(tempStr){
				case "0": attendNum++;break;
				case "1": absentNum++;break;
				case "2": leaveNum++;break;
			}
		}
		values.addElement(Integer.toString(students.size()));
		values.addElement(Integer.toString(attendNum));
		values.addElement(Integer.toString(absentNum));
		values.addElement(Integer.toString(leaveNum));
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getClassId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that updated:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			//...
		}
	}
	
	/**
	** The method to update one ValueBean specified by the parameter valueBean.
	*/
	public void updateCourseInfoBean(CourseBean valueBean)throws SQLException{
		String sql = "UPDATE "+INFOTABLENAME+" SET name = ?,time = ?,teacher_name = ?,detail = ? WHERE id = ?";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getTime());
		values.addElement(valueBean.getTeacherName());
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getClassId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that updated:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			//...
		}
	}
	
	/**
	** The method to insert one ValueBean specified by the parameter valueBean.
	*/
	public void insertCourseInstanceBean(CourseBean valueBean)throws SQLException{
		String sql = "INSERT INTO "+INSTANCETABLENAME+" (name,time,teacher_name,totalNum,attendNum,absentNum,leaveNum,detail,id) VALUES(?,?,?,?,?,?,?,?,?)";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getTime());
		values.addElement(valueBean.getTeacherName());
		Vector students = Utility.arrayToVector(valueBean.getStudents().split(","));
		if(valueBean.getStudents().equals("")){
			students = new Vector();
		}
		int attendNum = 0;
		int absentNum = 0;
		int leaveNum = 0;
		for(int i = 0;i<students.size();i++){
			String tempStr = (String)students.elementAt(i);
			tempStr = tempStr.substring(11,12);
			switch(tempStr){
				case "0": attendNum++;break;
				case "1": absentNum++;break;
				case "2": leaveNum++;break;
			}
		}
		values.addElement(Integer.toString(students.size()));
		values.addElement(Integer.toString(attendNum));
		values.addElement(Integer.toString(absentNum));
		values.addElement(Integer.toString(leaveNum));
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getClassId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that inserted:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			//...
		}
	}
	
	/**
	** The method to insert one ValueBean specified by the parameter valueBean.
	*/
	public void insertCourseInfoBean(CourseBean valueBean)throws SQLException{
		String sql = "INSERT INTO "+INFOTABLENAME+" (name,time,teacher_name,detail,id) VALUES(?,?,?,?,?)";
		Vector values = new Vector();		
		values.addElement(valueBean.getName());
		values.addElement(valueBean.getTime());
		values.addElement(valueBean.getTeacherName());
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getClassId());
		//...
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			CourseBean tempBean = valueBean;
			System.out.printf("The CourseBean that inserted:%n");
			System.out.printf("id=%s%n",tempBean.getClassId());
			//...
		}
	}
	
	
	/**
	** The method to update one ValueBean specified by the corresponding Prime Key(also other column value).
	*/
	public void deleteCourseInstanceBean(String value1)throws SQLException{
		String sql = "DELETE FROM "+INSTANCETABLENAME+" WHERE id = ?";
		Vector values = new Vector();
		values.addElement(value1);
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			System.out.printf("The CourseBean that deleted:%n");
			System.out.printf("id=%s%n",value1);
			//...
		}
	}
	
	/**
	** The method to update one ValueBean specified by the corresponding Prime Key(also other column value).
	*/
	public void deleteCourseInfoBean(String value1)throws SQLException{
		String sql = "DELETE FROM "+INFOTABLENAME+" WHERE id = ?";
		Vector values = new Vector();
		values.addElement(value1);
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			System.out.printf("The CourseBean that deleted:%n");
			System.out.printf("id=%s%n",value1);
			//...
		}
	}
	 
	 /** 
	 * Returns an CourseBean initialized with the information found
	 * in the database for the specified class,or null if not found.
	 */
	public CourseBean getClassInfo(String classId) throws SQLException{
		
		//Get the class info from the database
		Connection conn = dataSource.getConnection();
		Row classRow = null;
		try{
			classRow=getSingleValuePropsForClassInfo(classId,conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a CourseBean if the class was found
		if(classRow == null){
			//Not found
			System.out.println("Not found classInfo"+"classInfoId:"+classId);
			return null;
		}
		CourseBean classInfo = new CourseBean();
		try{
			classInfo.setClassId(classRow.getString("id"));			
			classInfo.setName(classRow.getString("name"));
			classInfo.setTime(classRow.getString("time"));
			classInfo.setTeacherName(classRow.getString("teacher_name"));
			classInfo.setTeacherId(classRow.getString("teacher_id"));		
			classInfo.setStudents(classRow.getString("detail"));		
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		return classInfo;
	}
	 
	 /** 
	 * Returns an Vector of CourseBean initialized with the information found
	 * in the database for all class,or null if not found.
	 */
	public Vector getAllClassInfo( ) throws SQLException{
		
		//Get all class info from the database
		Connection conn = dataSource.getConnection();
		Vector classRows = null;
		try{
			classRows=getSingleValuePropsForAllClassInfo(conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a CourseBean if the class was found
		if(classRows == null){
			//Not found
			System.out.println("Not found any classInfo");
			return null;
		}
		
		//deal with the Vector ROWs
		Vector classes = new Vector();
		for(int i = 0;i < classRows.size();i++){
			Row tempRow = (Row)classRows.elementAt(i);
			CourseBean tempCla = new CourseBean();
			try{
				tempCla.setClassId(tempRow.getString("id"));			
				tempCla.setName(tempRow.getString("name"));
				tempCla.setTime(tempRow.getString("time"));
				tempCla.setTeacherName(tempRow.getString("teacher_name"));
				tempCla.setTeacherId(tempRow.getString("teacher_id"));		
				tempCla.setStudents(tempRow.getString("detail"));		
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			classes.addElement(tempCla);			
		}	
		return classes;
	}
	 

	 
	 
	 
	 /** 
	 * Returns an CourseBean initialized with the information found
	 * in the database for the specified class,or null if not found.
	 */
	public CourseBean getClass(String classId) throws SQLException{
		
		//Get the class info from the database
		Connection conn = dataSource.getConnection();
		Row classRow = null;
		try{
			classRow=getSingleValuePropsForClass(classId,conn);
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException e){}//Ignore
		}
	
		//Create a CourseBean if the class was found
		if(classRow == null){
			//Not found
			System.out.println("Not found class"+"classId:"+classId);
			return null;
		}
			
		
		
		
			
		
		
		CourseBean classInfo = new CourseBean();
		try{
			classInfo.setClassId(classRow.getString("id"));			
			classInfo.setName(classRow.getString("name"));
			classInfo.setTime(classRow.getString("time"));
			classInfo.setTeacherName(classRow.getString("teacher_name"));
			classInfo.setTeacherId(classRow.getString("teacher_id"));
			classInfo.setTotal(classRow.getInt("totalNum"));	
			classInfo.setAttend(classRow.getInt("attendNum"));	
			classInfo.setAbsent(classRow.getInt("absentNum"));	
			classInfo.setLeave(classRow.getInt("leaveNum"));

			String detailStr = classRow.getString("detail");
			System.out.println("detailStr:"+detailStr);
			Vector detail = new Vector();
			for(int i = 0;i*13 < detailStr.length();i++){
				detail.addElement(detailStr.substring(0+13*i,12+13*i));
				System.out.println(detailStr.substring(0+13*i,12+13*i));//test
			}
			classInfo.setClassDetailInfo(detail);			
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		catch(UnsupportedConversionException usce){throw new SQLException("UnsupportedConversionException");}
		return classInfo;
	}
	 
	 
	 /** 
	 * Inserts the information about the specified class,or 
	 * updates the information if it's already defined. 
	 */
	public void saveClass(CourseBean classInfo) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValueProps(classInfo,conn);
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
	 * Inserts the information about the specified class,or 
	 * updates the information if it's already defined. 
	 */
	public void saveClassInfo(CourseBean classInfo) throws SQLException{
	
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try{
			saveSingleValuePropsForClassInfo(classInfo,conn);
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
	 * Inserts the information about the specified class,
	 * or updates the information if it's already defined.
	 */
	private void saveSingleValueProps(CourseBean classInfo,Connection conn)
		throws SQLException{
			
		if(classInfo == null) return;
		
		StringBuffer sql = new StringBuffer();
		CourseBean dbInfo = getClass(classInfo.getClassId());
		if(dbInfo == null){
			// Use INSERT statement
			sql.append("INSERT INTO courses ")
				.append("(name,time,teacher_name,teacher_id,totalNum,attendNum,absentNum,leaveNum,detail,id) ")			
				.append("VALUES(?,?,?,?,?,?,?,?,?,?)");
				
		}
		else{
			// Use UPDATE statement
			sql.append("UPDATE courses SET name = ?,time = ?,teacher_name = ?,teacher_id = ?,totalNum = ?,")
				.append("attendNum = ?,absentNum = ?,leaveNum = ?,detail = ? WHERE id = ?");
		}
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());
		
		
		StringBuffer detailStrBuf = new StringBuffer();
		for(int i = 0 ; i < classInfo.getClassDetailInfo().size() ; i++){
			String temp = (String)classInfo.getClassDetailInfo().elementAt(i);		
			if(i != classInfo.getClassDetailInfo().size()-1)
				detailStrBuf.append(temp).append(",");
		}

		
		System.out.println(detailStrBuf.toString());
		
		
		
		
		Vector values = new Vector();
		values.addElement(new StringValue(classInfo.getName()));
		values.addElement(new StringValue(classInfo.getTime()));
		values.addElement(new StringValue(classInfo.getTeacherName()));
		values.addElement(new StringValue(classInfo.getTeacherId()));
		
		Vector students = classInfo.getClassDetailInfo();
		int attendNum = 0;
		int absentNum = 0;
		int leaveNum = 0;
		for(int i = 0;i<students.size();i++){
			String tempStr = (String)students.elementAt(i);
			tempStr = tempStr.substring(11,12);
			switch(tempStr){
				case "0": attendNum++;break;
				case "1": absentNum++;break;
				case "2": leaveNum++;break;
			}
		}
		
		
		
		values.addElement(new StringValue(Integer.toString(students.size())));
		values.addElement(new StringValue(Integer.toString(attendNum)));
		values.addElement(new StringValue(Integer.toString(absentNum)));
		values.addElement(new StringValue(Integer.toString(leaveNum)));
		values.addElement(new StringValue(detailStrBuf.toString()));
		values.addElement(new StringValue(classInfo.getClassId()));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}	
	
	
	
	
	 
	 /**
	 * Inserts the information about the specified class,
	 * or updates the information if it's already defined.
	 */
	private void saveSingleValuePropsForClassInfo(CourseBean classInfo,Connection conn)
		throws SQLException{
			
		if(classInfo == null) return;
		
		StringBuffer sql = new StringBuffer();
		CourseBean dbInfo = getClassInfo(classInfo.getClassId());
		if(dbInfo == null){
			// Use INSERT statement
			sql.append("INSERT INTO courseinfo ")
				.append("(name,time,teacher_name,teacher_id,detail,id) ")			
				.append("VALUES(?,?,?,?,?,?)");				
		}
		else{
			// Use UPDATE statement
			sql.append("UPDATE courseinfo SET name = ?,time = ?,teacher_name = ?,teacher_id = ?,")
				.append("detail = ? WHERE id = ?");
		}
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);
		sqlCommandBean.setSqlValue(sql.toString());
		System.out.println(classInfo.getClassId()+classInfo.getName()+classInfo.getTeacherId());
		Vector values = new Vector();
		values.addElement(new StringValue(classInfo.getName()));
		values.addElement(new StringValue(classInfo.getTime()));
		values.addElement(new StringValue(classInfo.getTeacherName()));
		values.addElement(new StringValue(classInfo.getTeacherId()));
		values.addElement(new StringValue(classInfo.getStudents()));
		values.addElement(new StringValue(classInfo.getClassId()));
		sqlCommandBean.setValues(values);
		sqlCommandBean.executeUpdate();
	}
	
	/**
	 * Returns a Vector of Rows with all information about all
	 * classes,or null if not found.
	 */
	private Vector getSingleValuePropsForAllClassInfo(Connection conn)
		throws SQLException{		
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM courseinfo ");
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
	 * class,or null if not found.
	 */
	private Row getSingleValuePropsForClassInfo(String classId,Connection conn)
		throws SQLException{
		
		if(classId == null) return null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM courseinfo ")
			.append("WHERE id = ?");
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);	
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(classId));
		sqlCommandBean.setValues(values);
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){throw new SQLException("UnsupportedTypeException",e);}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Class not found
			return null;
		}
		return (Row)rows.firstElement();
	}
	 
	 
	 
	/**
	 * Returns a Row with all information about the specified
	 * class,or null if not found.
	 */
	private Row getSingleValuePropsForClass(String classId,Connection conn)
		throws SQLException{
		
		if(classId == null) return null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM courses ")
			.append("WHERE id = ?");
		SQLCommandBean sqlCommandBean = new SQLCommandBean();
		sqlCommandBean.setConnection(conn);	
		sqlCommandBean.setSqlValue(sql.toString());
		Vector values = new Vector();
		values.addElement(new StringValue(classId));
		sqlCommandBean.setValues(values);
		Vector rows = null;
		try{
			rows = sqlCommandBean.executeQuery();
		}
		catch(UnsupportedTypeException e){throw new SQLException("UnsupportedTypeException",e);}// Can not happen here
		
		if(rows == null || rows.size() == 0) {
			// Class not found
			return null;
		}
		return (Row)rows.firstElement();
	}

}