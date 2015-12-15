package custom.beans;

import java.lang.String;

/**
** This is a value bean template used to generate a value bean
**
** @version 0.1
** @author Diausc
 */
 
 public class ClassInfoBean extends java.lang.Object implements java.io.Serializable{
	//Properties
	private String classId;
	private String className;
	private String students;
	private String courses;
	
	//Constructor method
	public ClassInfoBean(){
		//Put your initialization code here
		classId="";
		className="";
		students="";
		courses="";
	}
	
	//Getter methods
	public String getClassId(){return classId;}
	public String getClassName(){return className;}
	public String getStudents(){return students;}
	public String getCourses(){return courses;}

	
	//Setter method
	public void setClassId(String classId){this.classId=classId;}
	public void setClassName(String className){this.className=className;}
	public void setStudents(String students){this.students=students;}
	public void setCourses(String courses){this.courses=courses;}

	
 }