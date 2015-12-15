package custom.beans;

import custom.beans.*;

/**
 * This is a value bean which contains information about a teacher.
 */

public class CourseBean extends java.lang.Object implements java.io.Serializable{
	//Properties
	private  java.lang.String classId;
	private  java.lang.String name;
	private  java.lang.String time;
	private  java.lang.String teacherName;
	private  java.lang.String teacherId;
	private  java.lang.String students;
	private  int total;
	private  int attend;
	private  int absent;
	private  int leave;
	private  java.util.Vector classDetailInfo;
	//flags
	private int iniFlag;//when initialized it should be 2047

	//Constructor method
	public CourseBean() {iniFlag=0;total=-1;attend=-1;absent=-1;leave=-1;classId="";name="";time="";teacherName="";teacherId="";students="";}

	//Getter methods
	public java.lang.String getClassId(){return classId;}
	public java.lang.String getName(){return name;}
	public java.lang.String getTime(){return time;}
	public java.lang.String getTeacherName(){return teacherName;}
	public java.lang.String getTeacherId(){return teacherId;}
	public java.lang.String getStudents(){return students;}
	public int getTotal(){return total;}
	public int getAttend(){return attend;}
	public int getAbsent(){return absent;}
	public int getLeave(){return leave;}
	public java.util.Vector getClassDetailInfo(){return classDetailInfo;}

	//Setter methods
	public void setClassId(java.lang.String classId){this.classId=classId; iniFlag = iniFlag|1;}
	public void setName(java.lang.String name){this.name=name; iniFlag = iniFlag|512;}
	public void setTime(java.lang.String time){this.time=time; iniFlag = iniFlag|2;}
	public void setTeacherName(java.lang.String teacherName){this.teacherName=teacherName; iniFlag = iniFlag|4;}
	public void setTeacherId(java.lang.String teacherId){this.teacherId=teacherId; iniFlag = iniFlag|8;}
	public void setStudents(java.lang.String students){this.students=students; iniFlag = iniFlag|1024;}
	public void setTotal(int total){this.total=total; iniFlag = iniFlag|16;}
	public void setAttend(int attend){this.attend=attend; iniFlag = iniFlag|32;}
	public void setAbsent(int absent){this.absent=absent; iniFlag = iniFlag|64;}
	public void setLeave(int leave){this.leave=leave; iniFlag = iniFlag|128;}	
	public void setTotal(java.lang.String total){this.total=Integer.parseInt(total); iniFlag = iniFlag|16;}
	public void setAttend(java.lang.String attend){this.attend=Integer.parseInt(attend); iniFlag = iniFlag|32;}
	public void setAbsent(java.lang.String absent){this.absent=Integer.parseInt(absent); iniFlag = iniFlag|64;}
	public void setLeave(java.lang.String leave){this.leave=Integer.parseInt(leave); iniFlag = iniFlag|128;}
	public void setClassDetailInfo(java.util.Vector classDetailInfo){this.classDetailInfo=classDetailInfo; iniFlag = iniFlag|256;}




}