package custom.beans;

import custom.beans.*;

/**
 * This is a value bean which contains information about a student.
 */

public class StudentBean extends java.lang.Object implements java.io.Serializable{
	//Properties
	private  java.lang.String studentId;
	private  java.lang.String sex;
	private  java.lang.String phone;
	private  java.lang.String name;
	private  java.lang.String email;
	private  java.lang.String studentClass;
	private  int state;


	//Constructor method
	public StudentBean() {state = -1;email="";sex="";phone="";name="";studentId="";}

	//Getter methods
	public java.lang.String getStudentId(){return studentId;}
	public java.lang.String getSex(){return sex;}
	public java.lang.String getPhone(){return phone;}
	public java.lang.String getName(){return name;}
	public java.lang.String getEmail(){return email;}
	public java.lang.String getStudentClass(){return studentClass;}
	public int getState(){return state;}


	//Setter methods
	public void setStudentId(java.lang.String studentId){this.studentId=studentId;}
	public void setSex(java.lang.String sex){this.sex=sex;}
	public void setPhone(java.lang.String phone){this.phone=phone;}
	public void setName(java.lang.String name){this.name=name;}
	public void setEmail(java.lang.String email){this.email=email;}
	public void setStudentClass(java.lang.String studentClass){this.studentClass=studentClass;}
	public void setState(int state){this.state=state;}


}