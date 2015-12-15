package custom.beans;

import custom.beans.*;
import custom.util.*;

//May not all be used
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import javax.sql.*;
import com.ora.jsp.sql.*;
import com.ora.jsp.util.*;
import com.ora.jsp.sql.value.*;


/**
** This is a utility bean,which is used to access a
** database.Including insert,update,select and delete.
** The corresponding value bean is ValueBeanTemplate,and assume value1
** is the Prime Key of the table in database.
** 
** No verify part about parameter in every method now.
** Only support string value now.All method parameter values is a Vector of Strings. 
** @version 0.1
** @author Diausc
*/

public class ClassInfoAccessBean implements Serializable{
	private DatabaseAccessUtility dataAcc;
	final static String TABLENAME = "classtable";
	final static boolean PRINTFLAG = true;
	
	//Constructor method
	public ClassInfoAccessBean(){
		dataAcc = new DatabaseAccessUtility();
	} 
	
	public void setDataSource(DataSource dataSource){dataAcc.setDataSource(dataSource);}
	
	
	/**
	** The method to get one ValueBean which is selected
	** by the corresponding Prime Key(also other column value).
	** The parameter value1 means prime key.
	** Return null if not found. 
	*/
	public ClassInfoBean getClassInfoBean(String value1)throws SQLException{
		String sql = "SELECT * FROM "+TABLENAME+" WHERE classId = ? ORDER BY classId";
		Vector values = new Vector();
		values.addElement(value1);//add the prime key
		Vector rows = dataAcc.selectRows(values,sql);
		Row row = null;
		if(rows != null && rows.size() != 0) row = (Row)rows.firstElement();
		else {
			if(PRINTFLAG == true){	
				System.out.printf("No ValueBeans found:%n");
			}
			return null;
		}
		ClassInfoBean valueBean = new ClassInfoBean();
		try{
			valueBean.setClassId(row.getString("classId"));
			valueBean.setClassName(row.getString("name"));
			valueBean.setStudents(row.getString("students"));
			valueBean.setCourses(row.getString("courses"));
		}
		catch(NoSuchColumnException nsce){}//Can not happen here
		//Print info that got.
		if(PRINTFLAG == true){
			ClassInfoBean tempBean = valueBean;
			System.out.printf("The ClassInfoBean that got:%n");
			System.out.printf("classId=%s%n",tempBean.getClassId());
			System.out.printf("className=%s%n",tempBean.getClassName());
			System.out.printf("students=%s%n",tempBean.getStudents());
			System.out.printf("courses=%s%n",tempBean.getCourses());
		}
		return valueBean;			
	}
	
	/**
	** The method to get all rows in the table.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getAllClassInfoBean()throws SQLException{
		Vector valueBeans = new Vector();
		Vector values = new Vector();
		String sql = "SELECT * FROM "+TABLENAME+" ORDER BY classId";
		Vector rows = dataAcc.selectRows(values,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No ValueBeans found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			ClassInfoBean tempValueBean = new ClassInfoBean();
			try{
				tempValueBean.setClassId(tempRow.getString("classId"));
				tempValueBean.setClassName(tempRow.getString("name"));
				tempValueBean.setStudents(tempRow.getString("students"));
				tempValueBean.setCourses(tempRow.getString("courses"));
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}				
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d ValueBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				ClassInfoBean tempBean = (ClassInfoBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("classId=%s%n",tempBean.getClassId());
				System.out.printf("className=%s%n",tempBean.getClassName());
				System.out.printf("students=%s%n",tempBean.getStudents());
				System.out.printf("courses=%s%n",tempBean.getCourses());
			}
		}
		return valueBeans;
	}
	
	/**
	** The method to get specified ValueBeans by the list of Prime Key(also other column value) of each ValueBean.
	** Return a Vector of all ValueBeans.
	** Return null if the table is empty.
	*/
	public Vector getClassInfoBeanByList(String list)throws SQLException{
		Vector valueBeans = new Vector();
		String sql = "SELECT * FROM "+TABLENAME+" WHERE classId = ? ORDER BY classId";
		Vector rows = dataAcc.selectRowsBySet(list,sql);
		if(rows == null || rows.size() == 0) {
			if(PRINTFLAG == true){	
				System.out.printf("No ValueBeans found:%n");
			}
			return null;
			}

		for(int i = 0;i<rows.size();i++){
			Row tempRow = (Row)rows.elementAt(i);
			ClassInfoBean tempValueBean = new ClassInfoBean();
			try{
				tempValueBean.setClassId(tempRow.getString("classId"));
				tempValueBean.setClassName(tempRow.getString("name"));
				tempValueBean.setStudents(tempRow.getString("students"));
				tempValueBean.setCourses(tempRow.getString("courses"));
			}
			catch(NoSuchColumnException nsce){}//Can not happen here
			valueBeans.addElement(tempValueBean);
		}	
		//Print info that got.
		if(PRINTFLAG == true){		
			System.out.printf("Got %d ValueBeans and they are:%n",valueBeans.size());
			for(int i = 0;i<valueBeans.size();i++){
				ClassInfoBean tempBean = (ClassInfoBean)valueBeans.elementAt(i);
				System.out.printf("The No.%d that got:%n",i+1);
				System.out.printf("classId=%s%n",tempBean.getClassId());
				System.out.printf("className=%s%n",tempBean.getClassName());
				System.out.printf("students=%s%n",tempBean.getStudents());
				System.out.printf("courses=%s%n",tempBean.getCourses());
			}
		}
		return valueBeans;	
	}
	
	/**
	** The method to update one ValueBean specified by the parameter valueBean.
	*/
	public void updateClassInfoBean(ClassInfoBean valueBean)throws SQLException{
		String sql = "UPDATE "+TABLENAME+" SET students = ?,courses = ?,name=? WHERE classId = ?";
		Vector values = new Vector();		
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getCourses());
		values.addElement(valueBean.getClassName());
		values.addElement(valueBean.getClassId());
		dataAcc.updateRow(values,sql);
		//Print info.
		if(PRINTFLAG == true){
			ClassInfoBean tempBean = valueBean;
			System.out.printf("The ClassInfoBean that updated:%n");
			System.out.printf("classId=%s%n",tempBean.getClassId());
		}
	}
	
	/**
	** The method to insert one ValueBean specified by the parameter valueBean.
	*/
	public void insertClassInfoBean(ClassInfoBean valueBean)throws SQLException{
		String sql = "INSERT INTO "+TABLENAME+" (students,courses,name,classId) VALUES(?,?,?,?)";
		Vector values = new Vector();		
		values.addElement(valueBean.getStudents());
		values.addElement(valueBean.getCourses());
		values.addElement(valueBean.getClassName());
		values.addElement(valueBean.getClassId());
		dataAcc.updateRow(values,sql);
		if(PRINTFLAG == true){
			ClassInfoBean tempBean = valueBean;
			System.out.printf("The ClassInfoBean that inserted:%n");
			System.out.printf("classId=%s%n",tempBean.getClassId());
		}
	}
	
	/**
	** The method to update one ValueBean specified by the corresponding Prime Key(also other column value).
	*/
	public void deleteClassInfoBean(String value1)throws SQLException{
		String sql = "DELETE FROM "+TABLENAME+" WHERE classId = ?";
		Vector values = new Vector();
		values.addElement(value1);
		dataAcc.updateRow(values,sql);
		if(PRINTFLAG == true){
			System.out.printf("The ClassInfoBean that deleted:%n");
			System.out.printf("classId=%s%n",value1);
		}
	}
}