package custom.beans;

import custom.beans.*;

/**
 * This is a value bean which contains information about a admin.
 */

public class AdminBean extends java.lang.Object implements java.io.Serializable{
	//Properties
	private  java.lang.String adminId;
	private  java.lang.String password;

	//Constructor method
	public AdminBean() {}

	//Getter methods
	public java.lang.String getAdminId(){return adminId;}
	public java.lang.String getPassword(){return password;}


	//Setter methods
	public void setAdminId(java.lang.String adminId){this.adminId=adminId;}
	public void setPassword(java.lang.String password){this.password=password;}


}