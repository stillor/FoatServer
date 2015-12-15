package custom.beans;

import custom.beans.*;
import java.util.*;

/**
 * This is a value bean which contains information about a teacher.
 */

public class TeacherBean extends java.lang.Object implements java.io.Serializable{
	//Properties
	private  java.lang.String teacherId;
	private  java.lang.String password;
	private  java.lang.String phone;
	private  java.lang.String name;
	private  java.lang.String classesOfTeacherStr;
	private  java.util.Vector classesOfTeacher;

	//Constructor method
	public TeacherBean() {teacherId="";password="";phone="";name="";classesOfTeacherStr="";classesOfTeacher= new Vector();}

	//Getter methods
	public java.lang.String getTeacherId(){return teacherId;}
	public java.lang.String getPassword(){return password;}
	public java.lang.String getPhone(){return phone;}
	public java.lang.String getName(){return name;}
	public java.lang.String getClassesOfTeacherStr(){return classesOfTeacherStr;}
	public java.util.Vector getClassesOfTeacher(){return classesOfTeacher;}
	/*
	public java.lang.String getEventTime(){
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < events.length; i++) {
			
            str.append(events[i].getStartTime().split(" ")[0].split("-")[0]+",")
				.append(events[i].getStartTime().split(" ")[0].split("-")[1]+",")
				.append(events[i].getStartTime().split(" ")[0].split("-")[2]+",");
        }	
		return str.toString();
	}*/

	//Setter methods
	public void setTeacherId(java.lang.String teacherId){this.teacherId=teacherId;}
	public void setPassword(java.lang.String password){this.password=password;}
	public void setPhone(java.lang.String phone){this.phone=phone;}
	public void setName(java.lang.String name){this.name=name;}
	public void setClassesOfTeacherStr(java.lang.String classesOfTeacherStr){this.classesOfTeacherStr=classesOfTeacherStr;}
	public void setClassesOfTeacher(java.util.Vector classesOfTeacher){this.classesOfTeacher=classesOfTeacher;}




}