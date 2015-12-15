package custom.servlets;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.text.*;
import custom.beans.*;
import custom.util.*;
import custom.resource.*;
import com.ora.jsp.sql.*;
import com.ora.jsp.util.*;
import com.ora.jsp.sql.value.*;


public class PControllerServlet extends HttpServlet{

	/**
	 * Creates an UserRegistryBean and saves it as servlet 
	 * context attributes.
	 */
    public void init() throws ServletException {
		DataSource ds = null;
		try{
			ds = new DataSourceWrapper("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/foat","root","123456");
		}	
		catch(Exception e){} // Ignore
		CourseAccessBean classAcc = new CourseAccessBean();
		classAcc.setDataSource(ds);
		getServletContext().setAttribute("classAcc",classAcc);
		
		StudentAccessBean studentAcc = new StudentAccessBean();
		studentAcc.setDataSource(ds);
		getServletContext().setAttribute("studentAcc",studentAcc);
		
		AdminAccessBean adminAcc = new AdminAccessBean();
		adminAcc.setDataSource(ds);
		getServletContext().setAttribute("adminAcc",adminAcc);
		
		TeacherAccessBean teacherAcc = new TeacherAccessBean();
		teacherAcc.setDataSource(ds);
		getServletContext().setAttribute("teacherAcc",teacherAcc);
		
		BasicAccessBean basicAcc = new BasicAccessBean();
		basicAcc.setDataSource(ds);
		getServletContext().setAttribute("basicAcc",basicAcc);
		
		ClassInfoAccessBean classInfoAcc = new ClassInfoAccessBean();
		classInfoAcc.setDataSource(ds);
		getServletContext().setAttribute("classInfoAcc",classInfoAcc);
		
	}
	
	
	/**
	 * Removes the UserRegistryBean servlet context attribute.
	 */
	public void destroy(){
		getServletContext().removeAttribute("classAcc");
	}
	 
	 
	 
	
	/**
	 * Changes GET requests to POST requests.
	 */
	public void doGet(HttpServletRequest request,
	    HttpServletResponse response)
		throws IOException,ServletException {
		doPost(request,response);			
	}
	 
	 
	/**
     * Performs authentication, if needed, and access control.
     * If the user is authorized, performs the action specified by
     * the "action" request parameter.
     */
	public void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws IOException,ServletException{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//test
		System.out.println("NowClassIndexNumber:"+Utility.getNowClassIndexNumber());
		
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");	
		try{
			courseAcc.generateAllCourseInstance();
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}		
		
		
		 if ("checkIn".equals(action)) {
            doCheckIn(request, response);
			return;
            }		 
		if ("editStudentState".equals(action)) {
            doEditStudentState(request, response);
			return;
            }		
		if ("editCourse".equals(action)) {
            doEditCourse(request, response);
			return;
            }
		if ("editCourseInfoDetail".equals(action)) {
            doEditCourseInfoDetail(request, response);
			return;
            }
		if ("saveBasicCourseInfo".equals(action)) {
            doSaveBasicCourseInfo(request, response);
			return;
            }			
		if ("addCourseTime".equals(action)) {
            doAddCourseTime(request, response);
			return;
            }	
		if ("addNewCourse".equals(action)) {
            doAddNewCourse(request, response);
			return;
            }	
		if ("addCourseStudent".equals(action)) {
            doAddCourseStudent(request, response);
			return;
            }					
		if ("deleteCourseStudent".equals(action)) {
            doDeleteCourseStudent(request, response);
			return;
            }		
		if ("deleteCourseTime".equals(action)) {
            doDeleteCourseTime(request, response);
			return;
            }
		if ("classInfoTest".equals(action)) {
            doClassInfoTest(request, response);
			return;
            }		
		if ("showCourseJSON".equals(action)) {
            doShowCourseJSON(request, response);
			return;
            }		
		if ("showScheduleJSON".equals(action)) {
            doShowScheduleJSON(request, response);
			return;
            }
			
			
		//管理员	
		if ("editBasic".equals(action)) {
            doEditBasic(request, response);
			return;
            }
		if ("saveBasicInfo".equals(action)) {
            doSaveBasicInfo(request, response);
			return;
            }
		if ("changeAdminPassword".equals(action)) {
            doChangeAdminPassword(request, response);
			return;
            }	
			
	//管理端-教师	
		//select
		if ("editTeacher".equals(action)) {
            doEditTeacher(request, response);
			return;
            }
		if ("deleteTeacher".equals(action)) {
            doDeleteTeacher(request, response);
			return;
            }		
		if ("addNewTeacher".equals(action)) {
            doAddNewTeacher(request, response);
			return;
            }
		//detail
		if ("editTeacherInfoDetail".equals(action)) {
            doEditTeacherInfoDetail(request, response);
			return;
            }		
		if ("deleteTeacherClass".equals(action)) {
            doDeleteTeacherClass(request, response);
			return;
            }
		if ("addTeacherClass".equals(action)) {
            doAddTeacherClass(request, response);
			return;
            }	
		if ("changeTeacherPassword".equals(action)) {
            doChangeTeacherPassword(request, response);
			return;
            }
		if ("saveBasicTeacherInfo".equals(action)) {
            doSaveBasicTeacherInfo(request, response);
			return;
            }	
			
	//管理端-班级	
		//select
		if ("editClass".equals(action)) {
            doEditClass(request, response);
			return;
            }
		if ("deleteClass".equals(action)) {
            doDeleteClass(request, response);
			return;
            }
		if ("addNewClass".equals(action)) {
            doAddNewClass(request, response);
			return;
            }
		//detail
		if ("editClassInfoDetail".equals(action)) {
            doEditClassInfoDetail(request, response);
			return;
            }		
		if ("deleteClassCourse".equals(action)) {
            doDeleteClassCourse(request, response);
			return;
            }
		if ("deleteClassStudent".equals(action)) {
            doDeleteClassStudent(request, response);
			return;
            }
		if ("addClassCourse".equals(action)) {
            doAddClassCourse(request, response);
			return;
            }
		if ("addClassStudent".equals(action)) {
            doAddClassStudent(request, response);
			return;
            }	
			
	//管理端-学生	
		//select
		if ("editStudent".equals(action)) {
            doEditStudent(request, response);
			return;
            }
		if ("deleteStudent".equals(action)) {
            doDeleteStudent(request, response);
			return;
            }
		if ("addNewStudent".equals(action)) {
            doAddNewStudent(request, response);
			return;
            }
		//detail		
		if ("editStudentInfoDetail".equals(action)) {
            doEditStudentInfoDetail(request, response);
			return;
            }
		if ("saveStudentInfo".equals(action)) {
            doSaveStudentInfo(request, response);
			return;
            }

		
		
		// Check if the user is authenticated
		//test
		if( !isAuthenticatedAdmin(request) && !("adminAuthenticate".equals(action) ||
			"logout".equals(action))){			
			if(!isAuthenticatedTeacher(request) && !("teacherAuthenticateJSON".equals(action) || "teacherAuthenticate".equals(action) ||
			"logout".equals(action))){
				doForwardToLogin(request,response);
				return;
			}
		}
		else{
			 if ("editStudent".equals(action)) {
                doEditStudent(request, response);return;
            }
			else if ("editTeacher".equals(action)) {
                doEditTeacher(request, response);return;
            }  
			else if ("editCourse".equals(action)) {
                doEditCourse(request, response);return;
            }
            else if ("adminLogout".equals(action)) {
                doAdminLogout(request, response);return;
            }       
			else if ("adminUpdateProfile".equals(action)) {
                doAdminUpdateProfile(request, response);return;
            }
            else if ("showPage".equals(action)) {
                doShowPage(request, response);return;
            }	
			else if ("adminAuthenticate".equals(action)) {
                doAdminAuthenticate(request, response);return;
            }	
			else if ("logout".equals(action)) {
                doAdminLogout(request, response);return;
            }	
			else {
				response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);return;
			}
		}

		if(!isAuthenticatedTeacher(request) && !("teacherAuthenticateJSON".equals(action) || "teacherAuthenticate".equals(action) ||
			"logout".equals(action))){
			doForwardToLogin(request,response);return;
		}
		else{
			 if ("showSchedule".equals(action)) {
                doShowSchedule(request, response);return;
            }
            else if ("showScheduleJSON".equals(action)) {
                doShowScheduleJSON(request, response);return;
            }  
			else if ("showCourse".equals(action)) {
                doShowCourse(request, response);return;
            }   
			else if ("showCourseJSON".equals(action)) {
                doShowCourseJSON(request, response);return;
            }        
			else if ("classUpdateTest".equals(action)) {
                doCourseUpdateTest(request, response);return;
            }
			else if ("studentUpdateTest".equals(action)) {
                doStudentUpdateTest(request, response);return;
            }	
			else if ("teacherAuthenticate".equals(action)) {
                doTeacherAuthenticate(request, response);return;
            }	
			else if ("teacherChangePassword".equals(action)) {
                doTeacherChangePassword(request, response);return;
            }	
			else if ("logout".equals(action)) {
                doTeacherLogout(request, response);return;
            }	
			else if ("teacherAuthenticateJSON".equals(action)) {
                doTeacherAuthenticateJSON(request, response);return;
            }				
			else {
				response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);return;
			}
		}

	}
	
	
	/**
     * Returns true if an authentication-token object of the admin is found
     * in the session.
     */
	private boolean isAuthenticatedAdmin(HttpServletRequest request){
		boolean isAuthenticated = false;
		HttpSession session = request.getSession();
		AdminBean validAdmin = (AdminBean)session.getAttribute("validAdmin");
		if(validAdmin != null && validAdmin.getAdminId() != null){ 
			isAuthenticated = true;
		}
		return isAuthenticated;
	}
	
	/**
     * Returns true if an authentication-token object of the teacher is found
     * in the session.
     */
	private boolean isAuthenticatedTeacher(HttpServletRequest request){
		boolean isAuthenticated = false;//Always true in test period 
		HttpSession session = request.getSession();
		TeacherBean validTeacher = (TeacherBean)session.getAttribute("validTeacher");
		if(validTeacher != null && validTeacher.getTeacherId() != null){ 
			System.out.printf("valid teacher:%s%n",validTeacher.getTeacherId());
			isAuthenticated = true;
		}
		return isAuthenticated;
	}
	
	
	//Edit part
	private void doEditCourse(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			Vector classes = null;
			try{
				classes = classAcc.getAllClassInfo();
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			request.setAttribute("classes",classes);
			forward("editCourse.jsp",request,response);
		}	
	private void doEditBasic(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {	
		BasicAccessBean basicAcc = (BasicAccessBean) 
             getServletContext().getAttribute("basicAcc");
		try{
			request.setAttribute("adminPhone",basicAcc.getAdminPhone());
		}catch(SQLException e){throw new ServletException("database error!");}
		forward(R.JSP.editAdminProfileAndBasicInfoJSP,request,response);
		}
	
	
	private void doEditTeacher(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			Vector teachers = null;
			try{
				teachers = teacherAcc.getAllTeachers();
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			request.setAttribute("teachers",teachers);
			forward(R.JSP.editTeacherJSP,request,response);
		}		
	private void doEditClass(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			Vector classes = null;
			try{
				classes = classInfoAcc.getAllClassInfoBean();
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			request.setAttribute("classes",classes);
			forward(R.JSP.editClassJSP,request,response);
		}		
	private void doEditStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			Vector students = null;
			try{
				students = studentAcc.getAllStudentBean();
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			request.setAttribute("students",students);
			forward(R.JSP.editStudentJSP,request,response);
		}	
		
		
	//Delete part
	private void doDeleteTeacher(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			String TID = request.getParameter("TID");
			try{
				teacherAcc.deleteTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}						
			String forwardURL = "process?action=editTeacher"+ 
                    "&msg=" + URLEncoder.encode("Delete Teacher Succeeded");					                    
            response.sendRedirect(forwardURL);
		}
	private void doDeleteClass(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			String CID = request.getParameter("CID");
			try{ 
				classInfoAcc.deleteClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}						
			String forwardURL = "process?action=editClass"+ 
                    "&msg=" + URLEncoder.encode("Delete Class Succeeded");					                    
            response.sendRedirect(forwardURL);
		}		
	private void doDeleteStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			String SID = request.getParameter("SID");
			try{
				studentAcc.deleteStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}						
			String forwardURL ="process?action=editStudent"+ 
                    "&msg=" + URLEncoder.encode("Delete Student Succeeded");					                    
            response.sendRedirect(forwardURL);
		}		
		
		

		
		
		
	//EditDetail part
	private void doEditTeacherInfoDetail(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			String TID = request.getParameter("TID");
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher == null) {
				String forwardURL = "process?action=editTeacher"+ 
                    "&msg=" + URLEncoder.encode("Teacher Not Found");					                    
				response.sendRedirect(forwardURL);
				return;
			}
					
			//Get classes
			Vector classes = null;
			try{
				classes = classInfoAcc.getClassInfoBeanByList(validTeacher.getClassesOfTeacherStr());
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}			
			if(classes == null )classes= new Vector();
			request.setAttribute("validTeacher",validTeacher);
			request.setAttribute("classes",classes);
			forward(R.JSP.editTeacherInfoDetailJSP,request,response);
		}	
	private void doSaveBasicTeacherInfo(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			String TID = request.getParameter("TID");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher == null) {				
				return;
			}							
			validTeacher.setPhone(phone);		
			validTeacher.setName(name);		
					
			try{
				teacherAcc.updateTeacherBean(validTeacher);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editTeacherInfoDetail"+                 
					"&TID=" + URLEncoder.encode(TID)+			
					"&msg=" + URLEncoder.encode("Teacher Basic Saved");		                    
				response.sendRedirect(forwardURL);
		}
		
	private void doSaveStudentInfo(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			String SID = request.getParameter("SID");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			//Get valid student
			StudentBean validStudent = null; 
			try{
				validStudent = studentAcc.getStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validStudent == null) {				
				return;
			}							
			validStudent.setPhone(phone);		
			validStudent.setName(name);		
			validStudent.setSex(sex);		
			validStudent.setEmail(email);		
			
					
			try{
				studentAcc.updateStudentBean(validStudent);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editStudentInfoDetail"+                 
					"&SID=" + URLEncoder.encode(SID)+			
					"&msg=" + URLEncoder.encode("Student Basic Saved");		                    
				response.sendRedirect(forwardURL);
		}
	private void doAddNewTeacher(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			String TID = request.getParameter("TID");
			String password = request.getParameter("password");
			String password_again = request.getParameter("password_again");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			
			if(TID == null || TID.equals("")||password == null ||
				password.equals("")||password_again == null || password_again.equals("")){
					String forwardURL = "process?action=showPage"+ 
					"&page="+URLEncoder.encode("addNewTeacher.jsp")+	               		
					"&msg=" + URLEncoder.encode("Input ID Or Password");		                    
				response.sendRedirect(forwardURL);
				return;	
			}
			if(!password.equals(password_again)){
					String forwardURL = "process?action=showPage"+ 
					"&page="+URLEncoder.encode("addNewTeacher.jsp")+	              		
					"&msg=" + URLEncoder.encode("Password_again Not Right");		                    
				response.sendRedirect(forwardURL);
				return;	
			}
				
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher != null) {	
					String forwardURL = "process?action=showPage"+ 
					"&page="+URLEncoder.encode("addNewTeacher.jsp")+	               		
					"&msg=" + URLEncoder.encode("Teacher ID Invalid");		                    
				response.sendRedirect(forwardURL);
				return;
			}
			TeacherBean newTeacher = new TeacherBean(); 	
			newTeacher.setTeacherId(TID);		
			newTeacher.setPassword(password);		
			newTeacher.setName(name);		
			newTeacher.setPhone(phone);		
					
			try{
				teacherAcc.insertTeacherBean(newTeacher);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editTeacherInfoDetail"+ 
					"&TID="+URLEncoder.encode(newTeacher.getTeacherId())+	
					"&msg=" + URLEncoder.encode("New Teacher Added");		                    
				response.sendRedirect(forwardURL);
		}
		
	private void doAddNewClass(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			String CID = request.getParameter("CID");
			String className = request.getParameter("className");
			
			if(CID == null || CID.equals("")||className == null ||
				className.equals("")){
				String forwardURL = "process?action=showPage"+  
					"&page="+URLEncoder.encode("addNewClass.jsp")+					
					"&msg=" + URLEncoder.encode("Input ID Or Name");		                    
				response.sendRedirect(forwardURL);
				return;	
			}
				
			//Get valid teacher
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass != null) {	
				String forwardURL = "process?action=showPage"+  
					"&page="+URLEncoder.encode("addNewClass.jsp")+	             		
					"&msg=" + URLEncoder.encode("Class ID Invalid");		                    
				response.sendRedirect(forwardURL);
				return;
			}
			ClassInfoBean newClass = new ClassInfoBean(); 	
			newClass.setClassId(CID);		
			newClass.setClassName(className);		
					
			try{
				classInfoAcc.insertClassInfoBean(newClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editClassInfoDetail"+  
					"&CID="+URLEncoder.encode(newClass.getClassId())+	
					"&msg=" + URLEncoder.encode("New Class Added");		                    
				response.sendRedirect(forwardURL);
		}
		
	private void doAddNewStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			String SID = request.getParameter("SID");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			if(SID == null || SID.equals("")||name == null ||
				name.equals("")){
				String forwardURL = "process?action=showPage"+  
					"&page="+URLEncoder.encode("addNewStudent.jsp")+					
					"&msg=" + URLEncoder.encode("Input ID Or Name");		                    
				response.sendRedirect(forwardURL);
				return;	
			}
				
			//Get valid student
			StudentBean validStudent = null; 
			try{
				validStudent = studentAcc.getStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validStudent != null) {	
				String forwardURL = "process?action=showPage"+  
					"&page="+URLEncoder.encode("addNewStudent.jsp")+	             		
					"&msg=" + URLEncoder.encode("Student ID Invalid");		                    
				response.sendRedirect(forwardURL);
				return;
			}
			StudentBean newStudent = new StudentBean(); 	
			newStudent.setStudentId(SID);		
			newStudent.setSex(sex);		
			newStudent.setPhone(phone);		
			newStudent.setName(name);		
			newStudent.setEmail(email);		
		
					
			try{
				studentAcc.insertStudentBean(newStudent);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editStudentInfoDetail"+  
					"&SID="+URLEncoder.encode(newStudent.getStudentId())+	
					"&msg=" + URLEncoder.encode("New Student Added");		                    
				response.sendRedirect(forwardURL);
		}
	private void doChangeTeacherPassword(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
			String TID = request.getParameter("TID");
			String newPassword = request.getParameter("newPassword");
			String newPassword_again = request.getParameter("newPassword_again");
			
			if(!newPassword_again.equals(newPassword)){
				String forwardURL = "process?action=editTeacherInfoDetail"+                 
					"&TID=" + URLEncoder.encode(TID)+			
					"&msg=" + URLEncoder.encode("NewPassword_again Not Right");		                    
				response.sendRedirect(forwardURL);
			}
			
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher == null) {				
				return;
			}							
			validTeacher.setPassword(newPassword);							
			try{
				teacherAcc.updateTeacherBean(validTeacher);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}							
				String forwardURL = "process?action=editTeacherInfoDetail"+                 
					"&TID=" + URLEncoder.encode(TID)+			
					"&msg=" + URLEncoder.encode("Teacher Password Saved");		                    
				response.sendRedirect(forwardURL);
		}		
		
	
	private void doEditClassInfoDetail(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {			
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");	
			CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");	
			String CID = request.getParameter("CID");			
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) {	
				String forwardURL = "process?action=editClass"+                 		
					"&msg=" + URLEncoder.encode("Class Not Found");		                    
				response.sendRedirect(forwardURL);
				return;
			}
					
			//Get courses
			Vector courses = null;
			try{
				courses = courseAcc.getCourseInfoBeanByList(validClass.getCourses());
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}	
			if(courses == null) courses = new Vector();
			
			//Get students
			Vector students = null;
			try{
				students = studentAcc.getStudentBeanByList(validClass.getStudents());
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}		
			if(students == null) students = new Vector();
			
			request.setAttribute("validClass",validClass);
			request.setAttribute("courses",courses);
			request.setAttribute("students",students);
			forward(R.JSP.editClassInfoDetailJSP,request,response);
		}	
	private void doEditStudentInfoDetail(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {			
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");	
			String SID = request.getParameter("SID");			
			//Get valid student
			StudentBean validStudent = null; 
			try{
				validStudent = studentAcc.getStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validStudent == null) return;
								
			request.setAttribute("validStudent",validStudent);
			forward(R.JSP.editStudentInfoDetailJSP,request,response);
		}
		
	//Delete a part of list
	private void doDeleteTeacherClass(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String TID = request.getParameter("TID");
			
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");	
								
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher == null) return;
			
			//Delete list						
			Vector classes = Utility.arrayToVector(validTeacher.getClassesOfTeacherStr().split(","));
			String newClasses = null;
			for(int i = 0;i < classes.size();i++){
				String tempStr = (String)classes.elementAt(i);			
				if(tempStr.equals(CID)){//find the co-responding one
					classes.removeElementAt(i);			
					break;
				}
			}
			newClasses = Utility.vectorToString(classes);
			validTeacher.setClassesOfTeacherStr(newClasses);			
			System.out.println(validTeacher.getClassesOfTeacher());
			
			try{
				teacherAcc.updateTeacherBean(validTeacher);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editTeacherInfoDetail"+
                    "&msg=" + URLEncoder.encode("Class Deleted")
					+"&TID=" + URLEncoder.encode(request.getParameter("TID"));                   
            response.sendRedirect(forwardURL);
		}	
	private void doDeleteClassCourse(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String course = request.getParameter("course");
			
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
								
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) return;
			
			//Delete list						
			Vector courses = Utility.arrayToVector(validClass.getCourses().split(","));
			String newCourses = null;
			for(int i = 0;i < courses.size();i++){
				String tempStr = (String)courses.elementAt(i);			
				if(tempStr.equals(course)){//find the co-responding one
					courses.removeElementAt(i);			
					break;
				}
			}
			newCourses = Utility.vectorToString(courses);
			validClass.setCourses(newCourses);			
			System.out.println(validClass.getCourses());
			
			try{
				classInfoAcc.updateClassInfoBean(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Course Deleted")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
		}	
		
	private void doDeleteClassStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String SID = request.getParameter("SID");
			
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
								
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) return;
			
			//Delete list						
			Vector students = Utility.arrayToVector(validClass.getStudents().split(","));
			String newStudents = null;
			for(int i = 0;i < students.size();i++){
				String tempStr = (String)students.elementAt(i);			
				if(tempStr.equals(SID)){//find the co-responding one
					students.removeElementAt(i);			
					break;
				}
			}
			newStudents = Utility.vectorToString(students);
			validClass.setStudents(newStudents);			
			System.out.println(validClass.getStudents());
			
			try{
				classInfoAcc.updateClassInfoBean(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Deleted")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
		}	
		
	private void doAddClassCourse(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String CourseId = request.getParameter("CourseId");
			
			CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");	
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");					
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) return;
			
			
			//Get valid course
			CourseBean validCourse = null; 
			try{
				validCourse = courseAcc.getCourseInfoBean(CourseId);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validCourse == null) {
				String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Course Not Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);	
				return;
			}
			
			
			//add list						
			Vector courses = Utility.arrayToVector(validClass.getCourses().split(","));
			String newCourses = null;
			if(validClass.getCourses().equals(""))courses=new Vector();
			for(int i = 0;i < courses.size();i++){
				String tempStr = (String)courses.elementAt(i);			
				if(tempStr.equals(CourseId)){//find the co-responding one
					String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Course Already Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);					
					return;
				}
			}
			courses.addElement(CourseId);
			newCourses = Utility.vectorToString(courses);
			validClass.setCourses(newCourses);			
			System.out.println(validClass.getCourses());
			
			try{
				classInfoAcc.updateClassInfoBean(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Course Added")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
			
		}
		
		
	private void doAddClassStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String SID = request.getParameter("SID");
			
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");	
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");					
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) return;
			
			
			//Get valid Student
			StudentBean validStudent = null; 
			try{
				validStudent = studentAcc.getStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validStudent == null) {
				String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Not Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);	
				return;
			}
			
			
			//add list						
			Vector students = Utility.arrayToVector(validClass.getStudents().split(","));
			if(validClass.getStudents().equals(""))students=new Vector();
			String newStudents = null;
			for(int i = 0;i < students.size();i++){
				String tempStr = (String)students.elementAt(i);			
				if(tempStr.equals(SID)){//find the co-responding one
					String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Already Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);					
					return;
				}
			}
			students.addElement(SID);
			newStudents = Utility.vectorToString(students);
			validClass.setStudents(newStudents);			
			System.out.println(validClass.getStudents());
			
			try{
				classInfoAcc.updateClassInfoBean(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editClassInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Added")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
			
		}
		
	private void doAddTeacherClass(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String TID = request.getParameter("TID");
			
			TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");	
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");					
			//Get valid teacher
			TeacherBean validTeacher = null; 
			try{
				validTeacher = teacherAcc.getTeacherBean(TID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validTeacher == null) return;
			
			
			//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) {
				String forwardURL = "process?action=editTeacherInfoDetail"+
                    "&msg=" + URLEncoder.encode("Class Not Exist")
					+"&TID=" + URLEncoder.encode(request.getParameter("TID"));                   
					response.sendRedirect(forwardURL);	
				return;
			}
			
			
			
			//add list						
			Vector classes = Utility.arrayToVector(validTeacher.getClassesOfTeacherStr().split(","));
			if(validTeacher.getClassesOfTeacherStr().equals(""))classes=new Vector();
			String newClasses = null;
			for(int i = 0;i < classes.size();i++){
				String tempStr = (String)classes.elementAt(i);			
				if(tempStr.equals(CID)){//find the co-responding one,add error
					String forwardURL = "process?action=editTeacherInfoDetail"+
                    "&msg=" + URLEncoder.encode("Class Already Exist")
					+"&TID=" + URLEncoder.encode(request.getParameter("TID"));                   
					response.sendRedirect(forwardURL);					
					return;
				}
			}
			classes.addElement(CID);
			newClasses = Utility.vectorToString(classes);
			validTeacher.setClassesOfTeacherStr(newClasses);			
			System.out.println(validTeacher.getClassesOfTeacher());
			
			try{
				teacherAcc.updateTeacherBean(validTeacher);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editTeacherInfoDetail"+
                    "&msg=" + URLEncoder.encode("Class Added")
					+"&TID=" + URLEncoder.encode(request.getParameter("TID"));                   
            response.sendRedirect(forwardURL);
		}	
	
		
		
		
		
		
	//Test part	
	private void doClassInfoTest(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			ClassInfoBean classInfo1 = new ClassInfoBean();
			classInfo1.setClassId("软件31");
			classInfo1.setStudents("2131601056,2131601057,2131601046,2131601076,2131601026,2131601022,2131601052,2131601051,2131601054,2131601053");
			classInfo1.setCourses("code0001,code0002,code0003");
			ClassInfoBean classInfo2 = new ClassInfoBean();
			classInfo2.setClassId("软件32");
			classInfo2.setStudents("2131601056,2131601057,2131601046,2131601076,2131601026,2131601022,2131601052,2131601051,2131601054,2131601053");
			classInfo2.setCourses("code0001,code0002,code0003");
			
			try{
			classInfoAcc.insertClassInfoBean(classInfo1);
			classInfoAcc.insertClassInfoBean(classInfo2);
			classInfoAcc.getAllClassInfoBean();
			classInfo2.setStudents("2131601058,2131601057,2131601046,2131601076,2131601026,2131601022,2131601052,2131601051,2131601054,2131601053");
			classInfoAcc.updateClassInfoBean(classInfo2);
						
			classInfoAcc.getClassInfoBeanByList("软件31,软件32");
			classInfoAcc.getClassInfoBean("软件32");
			classInfoAcc.deleteClassInfoBean("软件32");		
			}catch(SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
		}	
		
	private void doEditCourseInfoDetail(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
		
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");	
		//Get valid class
			CourseBean validCourse = null; 
			try{
				validCourse = courseAcc.getCourseInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validCourse == null) {	
				String forwardURL = "process?action=editCourse"+                 		
					"&msg=" + URLEncoder.encode("Course Not Found");		                    
				response.sendRedirect(forwardURL);
				return;
			}
		
		
		//Get students
			Vector students = null;
			String studentStr = validCourse.getStudents(); 
			try{
				students = studentAcc.getStudentBeanByList(studentStr);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}		
			if(students == null) students = new Vector();
		
			request.setAttribute("students",students);
			request.setAttribute("validCourse",validCourse);
			forward(R.JSP.editCourseInfoDetailJSP,request,response);
		}	
		
	private void doSaveBasicInfo(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {	
		BasicAccessBean basicAcc = (BasicAccessBean) 
             getServletContext().getAttribute("basicAcc");
		String adminPhone = request.getParameter("adminPhone");						 
		try{
			basicAcc.saveAdminPhone(adminPhone);
		}catch(SQLException e){throw new ServletException("database error!");}
		
		String forwardURL = "process?action=editBasic" +
                    "&msg=" + URLEncoder.encode("Basic Info Changed");					                   
        response.sendRedirect(forwardURL);
		}	
		
	private void doChangeAdminPassword(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {	
		 AdminAccessBean adminAcc = (AdminAccessBean) 
                getServletContext().getAttribute("adminAcc");
		AdminBean validAdmin = (AdminBean)request.getSession().getAttribute("validAdmin");
		String nowPassword = request.getParameter("nowPassword");						 
		String newPassword = request.getParameter("newPassword");						 
		String newPassword_again = request.getParameter("newPassword_again");
		if(!nowPassword.equals(validAdmin.getPassword())){
			String forwardURL = "process?action=editBasic" + 
                  "&msg=" + URLEncoder.encode("Password Wrong");					                   
			response.sendRedirect(forwardURL);
			return;
		}
		if(!newPassword.equals(newPassword_again)){
			String forwardURL = "process?action=editBasic" + 
                  "&msg=" + URLEncoder.encode("Password_Again Wrong");					                   
			response.sendRedirect(forwardURL);
			return;
		}	
		validAdmin.setPassword(newPassword);
		try{
			adminAcc.saveAdmin(validAdmin);
		}catch(SQLException e){throw new ServletException("database error!");}
		
		String forwardURL = "process?action=editBasic" + 
                    "&msg=" + URLEncoder.encode("Password Changed");					                   
        response.sendRedirect(forwardURL);
		}	
	private void doSaveBasicCourseInfo(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			//String id = request.getParameter("id");
			//String name = request.getParameter("name");
			String teacherName = request.getParameter("teacher_name");
		
			CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			CourseBean validClass = null;
			try{
				validClass = classAcc.getCourseInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			//validClass.setClassId(id);
			//validClass.setName(name);
			validClass.setTeacherName(teacherName);
			System.out.println(teacherName);
			
			try{
				classAcc.saveClassInfo(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editCourseInfoDetail" + 
                    "&msg=" + URLEncoder.encode("Basic Changed")
					+"&CID=" + URLEncoder.encode(CID);
                    
            response.sendRedirect(forwardURL);
		}	
		
	private void doAddCourseTime(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");	
			String CID = request.getParameter("CID");
			String time1 = request.getParameter("time1");
			String time2 = null;
			if(request.getParameter("time2").equals("10"))
				time2 = "A";
			else if(request.getParameter("time2").equals("11"))
				time2 = "B";
			else time2 = request.getParameter("time2");
			String time3 = null;
			if(request.getParameter("time3").equals("10"))
				time3 = "A";
			else if(request.getParameter("time3").equals("11"))
				time3 = "B";
			else time3 = request.getParameter("time3");
	
			if(time3 == null || time3.equals("")||time2 == null ||
				time2.equals("")){
				String forwardURL = "process?action=editCourseInfoDetail" + 
                    "&msg=" + URLEncoder.encode("Input Time Completedly")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));		                    
				response.sendRedirect(forwardURL);
				return;	
			}
				
			String newTime = time1 + time2 + time3;
			
	
	
	//Get valid class
			CourseBean validCourse = null; 
			try{
				validCourse = courseAcc.getCourseInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validCourse == null) 	return;
		
	
	//add list						
			Vector times = Utility.arrayToVector(validCourse.getTime().split(","));
			if(validCourse.getTime().equals(""))times=new Vector();
			String newTimes = null;
			for(int i = 0;i < times.size();i++){
				String tempStr = (String)times.elementAt(i);			
				if(tempStr.equals(newTime)){//find the co-responding one,add error
					String forwardURL = "process?action=editCourseInfoDetail"+
                    "&msg=" + URLEncoder.encode("Time Already Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);					
					return;
				}
			}
			times.addElement(newTime);
			newTimes = Utility.vectorToString(times);
			validCourse.setTime(newTimes);			
			System.out.println(validCourse.getTime());
	
			
			try{
				courseAcc.updateCourseInfoBean(validCourse);
				courseAcc.generateCourseInstance(validCourse.getClassId());
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editCourseInfoDetail" + 
                    "&msg=" + URLEncoder.encode("New Time Added")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));
                    
            response.sendRedirect(forwardURL);
		}		
		private void doAddNewCourse(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			
			CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			String courseId = request.getParameter("courseId");
			String courseName = request.getParameter("courseName");
			String teacher_name = request.getParameter("teacher_name");
			
			if(courseId == null || courseId.equals("")||courseName == null ||
				courseName.equals("")||teacher_name == null || teacher_name.equals("")){
					String forwardURL = "process?action=showPage"+ 
					"&page="+URLEncoder.encode("addNewCourse.jsp")+	               		
					"&msg=" + URLEncoder.encode("Input ID Or Other");		                    
				response.sendRedirect(forwardURL);
				return;	
			}

				
			//Get valid Course
			CourseBean validCourse = null; 
			try{
				validCourse = classAcc.getCourseInfoBean(courseId);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validCourse != null) {	
					String forwardURL = "process?action=showPage"+ 
					"&page="+URLEncoder.encode("addNewCourse.jsp")+	               		
					"&msg=" + URLEncoder.encode("Course ID Invalid");		                    
				response.sendRedirect(forwardURL);
				return;
			}
			CourseBean newCourse = new CourseBean(); 	
			newCourse.setClassId(courseId);		
			newCourse.setName(courseName);		
			newCourse.setTeacherName(teacher_name);		
		
					
			try{
				classAcc.insertCourseInfoBean(newCourse);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}					
				
				String forwardURL = "process?action=editCourseInfoDetail"+ 
					"&CID="+URLEncoder.encode(newCourse.getClassId())+	
					"&msg=" + URLEncoder.encode("New Course Added");		                    
				response.sendRedirect(forwardURL);
		}
		
		
		
	private void doAddCourseStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String SID = request.getParameter("SID");
			CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");	
					
			//Get valid class
			CourseBean validCourse = null; 
			try{
				validCourse = courseAcc.getCourseInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validCourse == null) 	return;
			
			
			//Get valid Student
			StudentBean validStudent = null; 
			try{
				validStudent = studentAcc.getStudentBean(SID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validStudent == null) {
				String forwardURL = "process?action=editCourseInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Not Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);	
				return;
			}
			
			
			
			//add list						
			Vector students = Utility.arrayToVector(validCourse.getStudents().split(","));
			if(validCourse.getStudents().equals(""))students=new Vector();
			String newStudents = null;
			for(int i = 0;i < students.size();i++){
				String tempStr = (String)students.elementAt(i);			
				if(tempStr.equals(SID)){//find the co-responding one
					String forwardURL = "process?action=editCourseInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Already Exist")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
					response.sendRedirect(forwardURL);					
					return;
				}
			}
			students.addElement(SID);
			newStudents = Utility.vectorToString(students);
			validCourse.setStudents(newStudents);			
			System.out.println(validCourse.getStudents());
			
			
			
			try{
				courseAcc.updateCourseInfoBean(validCourse);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editCourseInfoDetail"+
                    "&msg=" + URLEncoder.encode("Student Added")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
			
			

		}	
		
	private void doDeleteCourseStudent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String SID = request.getParameter("SID");
			
			CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			CourseBean validClass = null;
			try{
				validClass = classAcc.getClassInfo(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String students = validClass.getStudents();
			for(int i = 0;i*11 < students.length();i++){
				String tempStr = students.substring(0+i*11,10+i*11);
				if(tempStr.equals(SID)){
					if(10+i*11+1 < students.length())
						tempStr = students.substring(0,0+i*11)+students.substring(10+i*11+1,students.length());
					else//the last one in the string
						tempStr = (i == 0) ? students.substring(0,0+i*11):students.substring(0,0+i*11-1);
					students = tempStr;
					break;
				}
			}
			
			validClass.setStudents(students);			
			System.out.println(validClass.getStudents());
			
			try{
				classAcc.saveClassInfo(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editCourseInfoDetail" + 
                    "&msg=" + URLEncoder.encode("Student Deleted")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
		}	
		
	private void doDeleteCourseTime(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
			String CID = request.getParameter("CID");
			String time = request.getParameter("time");
			
			CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
			CourseBean validClass = null;
			try{
				validClass = classAcc.getCourseInfoBean(CID);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String times = validClass.getTime();
			for(int i = 0;i*4 < times.length();i++){
				String tempStr = times.substring(0+i*4,3+i*4);
				if(tempStr.equals(time)){
					if(3+i*4+1 < times.length())
						tempStr = times.substring(0,0+i*4)+times.substring(3+i*4+1,times.length());
					else//the last one in the string
						tempStr = (i == 0) ? times.substring(0,0+i*4):times.substring(0,0+i*4-1);
					times = tempStr;
					break;
				}
			}
			
			validClass.setTime(times);			
			System.out.println(validClass.getTime());
			
			try{
				classAcc.saveClassInfo(validClass);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}
			
			String forwardURL = "process?action=editCourseInfoDetail" + 
                    "&msg=" + URLEncoder.encode("Time Deleted")
					+"&CID=" + URLEncoder.encode(request.getParameter("CID"));                   
            response.sendRedirect(forwardURL);
		} 
		
	private void doAdminUpdateProfile(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
		
		}
		
	
	
	
	
	
	/**
     * Autheticates a admin with help from the AdminAccessBean,
     * using the "adminName" and "password" request parameters.
     * <p>
     * Cookies with the admin name and password are set or reset
     * as specified by the "remember" request parameter.
     */
    private void doAdminAuthenticate(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String adminName = request.getParameter("adminName");
        if (adminName == null) {
            throw new ServletException("Missing User Name");
        }
        String password = request.getParameter("password");
        if (password == null) {
            throw new ServletException("Missing Password");
        }

        try {
            AdminAccessBean adminAcc = (AdminAccessBean) 
                getServletContext().getAttribute("adminAcc");
            boolean isRegistered = adminAcc.authenticate(adminName, password);
            if (isRegistered) {
                AdminBean admin = adminAcc.getAdmin(adminName);
                HttpSession session = request.getSession();
                session.setAttribute("validAdmin", admin);
                
                // Set or "delete" cookies, as requested
                Cookie adminNameCookie = new Cookie("adminName", adminName);
                Cookie passwordCookie = new Cookie("password", password);
                int maxAge1 = 2592000,maxAge2 = 2592000;
                if (request.getParameter("rememberName") == null) maxAge1 = 0;
				if (request.getParameter("rememberPSW") == null) maxAge2 = 0;             
                adminNameCookie.setMaxAge(maxAge1);
                passwordCookie.setMaxAge(maxAge2);
                response.addCookie(adminNameCookie);
                response.addCookie(passwordCookie);
                
                // Redirect to the originally requested URL or main
                String next = request.getParameter("origURL");
                if (next == null || next.length() == 0) {
                    next = getShowPageURL(request) + R.JSP.mainJSP;
                }
                response.sendRedirect(next);
            }
            else {
                String loginURL =  R.JSP.adminLoginJSP + 
                    "?errorMsg=" + 
                    URLEncoder.encode("Invalid Admin Name or Password");
                response.sendRedirect(loginURL);
            }
        }
        catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }	
	
	/**
     * Autheticates a teacher with help from the TeacherAccessBean,
     * using the "teacherName" and "password" request parameters.
     */
    private void doTeacherAuthenticate(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String teacherName = request.getParameter("teacherName");
        if (teacherName == null) {
            request.setAttribute("error","Missing Teacher Id");
			forward(R.JSP.errorTemplateJSP, request, response);	
        }
        String password = request.getParameter("password");
        if (password == null) {
            request.setAttribute("error","Missing Teacher Password");
			forward(R.JSP.errorTemplateJSP, request, response);	
        }
			
        try {
            TeacherAccessBean teacherAcc = (TeacherAccessBean) 
                getServletContext().getAttribute("teacherAcc");
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
            boolean isRegistered = teacherAcc.authenticate(teacherName, password);
            if (isRegistered) {
                TeacherBean teacher = teacherAcc.getTeacher(teacherName);
                HttpSession session = request.getSession();
                session.setAttribute("validTeacher", teacher); 	

				Vector classes = null;
				classes = classInfoAcc.getClassInfoBeanByList(teacher.getClassesOfTeacherStr());
				if(classes == null )classes= new Vector();
				request.setAttribute("classes",classes);
				
				
				//get Students
				Vector absentStudents = new Vector();
				Vector leaveStudents = new Vector();
				Vector lateStudents = new Vector();
				for(int i =0;i<classes.size();i++){
					ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
					Vector tempCourses = courseAcc.getCourseInfoBeanByList(tempCla.getCourses());
					Vector tempClassStudents = Utility.arrayToVector(tempCla.getStudents().split(","));
					for(int k =0;k<tempCourses.size();k++){
						CourseBean tempCour = (CourseBean)tempCourses.elementAt(k);
						Vector times = Utility.arrayToVector(tempCour.getTime().split(","));
						for(int i2 =0 ;i2<times.size();i2++){												
							String tempTime = (String)times.elementAt(i2);
							if(Utility.isClassInTeaching(tempTime)){
							//when is teaching							
							String index = tempTime.substring(1,2);
							String instanceCID = Utility.generateCID(tempCour.getClassId(),tempTime.substring(0,1),"0"+index);
							CourseBean tempInstanceCourse = null; 
							tempInstanceCourse=courseAcc.getCourseInstanceBean(instanceCID);
							Vector tempStudents = Utility.arrayToVector(tempInstanceCourse.getStudents().split(","));
							for(int j =0;j<tempStudents.size();j++){
								String tempStuStr = (String)tempStudents.elementAt(j);
								for(int i3 =0;i3<tempClassStudents.size();i3++){
									String tempClassStuStr = (String)tempClassStudents.elementAt(i3);
									if(tempClassStuStr.equals(tempStuStr.substring(0,10))){
										if(tempStuStr.substring(11,12).equals("3")){//late
										StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
										tempStu.setStudentClass(tempCla.getClassName());						
										lateStudents.addElement(tempStu);
										}
										if(tempStuStr.substring(11,12).equals("1")){//absent
											StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
											tempStu.setStudentClass(tempCla.getClassName());
											absentStudents.addElement(tempStu);
										}
										if(tempStuStr.substring(11,12).equals("2")){//leave
											StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
											tempStu.setStudentClass(tempCla.getClassName());
											leaveStudents.addElement(tempStu);
										}
									}
								}
								
							}
							}
						}
					}													
				}
				request.setAttribute("absentStudents",absentStudents);
				request.setAttribute("leaveStudents",leaveStudents);
				request.setAttribute("lateStudents",lateStudents);
				
				
				
				BasicAccessBean basicAcc = (BasicAccessBean) 
                getServletContext().getAttribute("basicAcc");
				request.setAttribute("adminPhone",basicAcc.getAdminPhone());
				request.setAttribute("teacherName",teacher.getName());
				forward("welcomTemplate.jsp", request, response);
            }
            else {
				request.setAttribute("error","Invalid Teacher Name or Password");
				forward(R.JSP.errorTemplateJSP, request, response);			              
            }
        }
        catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }	
	
	

	
	
	/**
     * Autheticates a teacher with help from the TeacherAccessBean,
     * using the "teacherName" and "password" request parameters.
     */
    private void doTeacherAuthenticateJSON(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String teacherName = request.getParameter("teacherName");
        if (teacherName == null) {
            request.setAttribute("error","Missing Teacher Id");
			forward(R.JSP.errorTemplateJSONJSP, request, response);	
        }
        String password = request.getParameter("password");
        if (password == null) {
            request.setAttribute("error","Missing Teacher Password");
			forward(R.JSP.errorTemplateJSONJSP, request, response);	
        }

        try {
            TeacherAccessBean teacherAcc = (TeacherAccessBean) 
                getServletContext().getAttribute("teacherAcc");
			ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
			StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
			CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
            boolean isRegistered = teacherAcc.authenticate(teacherName, password);
            if (isRegistered) {
                TeacherBean teacher = teacherAcc.getTeacher(teacherName);
                HttpSession session = request.getSession();
                session.setAttribute("validTeacher", teacher); 	
				Vector classes = null;
				classes = classInfoAcc.getClassInfoBeanByList(teacher.getClassesOfTeacherStr());
				if(classes == null )classes= new Vector();
				request.setAttribute("classes",classes);
				
				
				//get Students
				Vector absentStudents = new Vector();
				Vector leaveStudents = new Vector();
				Vector lateStudents = new Vector();
				for(int i =0;i<classes.size();i++){
					ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
					Vector tempCourses = courseAcc.getCourseInfoBeanByList(tempCla.getCourses());
					Vector tempClassStudents = Utility.arrayToVector(tempCla.getStudents().split(","));
					for(int k =0;k<tempCourses.size();k++){
						CourseBean tempCour = (CourseBean)tempCourses.elementAt(k);
						Vector times = Utility.arrayToVector(tempCour.getTime().split(","));
						for(int i2 =0 ;i2<times.size();i2++){												
							String tempTime = (String)times.elementAt(i2);
							if(Utility.isClassInTeaching(tempTime)){
							//when is teaching							
							String index = tempTime.substring(1,2);
							String instanceCID = Utility.generateCID(tempCour.getClassId(),tempTime.substring(0,1),"0"+index);
							CourseBean tempInstanceCourse = null; 
							tempInstanceCourse=courseAcc.getCourseInstanceBean(instanceCID);
							Vector tempStudents = Utility.arrayToVector(tempInstanceCourse.getStudents().split(","));
							for(int j =0;j<tempStudents.size();j++){
								String tempStuStr = (String)tempStudents.elementAt(j);
								for(int i3 =0;i3<tempClassStudents.size();i3++){
									String tempClassStuStr = (String)tempClassStudents.elementAt(i3);
									if(tempClassStuStr.equals(tempStuStr.substring(0,10))){
										if(tempStuStr.substring(11,12).equals("3")){//late
										StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
										tempStu.setStudentClass(tempCla.getClassName());						
										lateStudents.addElement(tempStu);
										}
										if(tempStuStr.substring(11,12).equals("1")){//absent
											StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
											tempStu.setStudentClass(tempCla.getClassName());
											absentStudents.addElement(tempStu);
										}
										if(tempStuStr.substring(11,12).equals("2")){//leave
											StudentBean tempStu = studentAcc.getStudentBean(tempStuStr.substring(0,10));
											tempStu.setStudentClass(tempCla.getClassName());
											leaveStudents.addElement(tempStu);
										}
									}
								}
								
							}
							}
						}
					}													
				}
				request.setAttribute("absentStudents",absentStudents);
				request.setAttribute("leaveStudents",leaveStudents);
				request.setAttribute("lateStudents",lateStudents);
				
				
				BasicAccessBean basicAcc = (BasicAccessBean) 
                getServletContext().getAttribute("basicAcc");
				request.setAttribute("adminPhone",basicAcc.getAdminPhone());
				request.setAttribute("teacherName",teacher.getName());
				forward("welcomTemplateJSON.jsp", request, response);
            }
            else {
				request.setAttribute("error","Invalid Teacher Name or Password");
				forward(R.JSP.errorTemplateJSONJSP, request, response);			              
            }
        }
        catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
	
	
	
	
	/**
     * Invalidates the session, thereby removing the authentication
     * token, and redirects to the login page.
     */
    private void doAdminLogout(HttpServletRequest request, 
        HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("adminLogin.jsp");
    }
	
		/**
     * Invalidates the session, thereby removing the authentication
     * token, and redirects to the login page.
     */
    private void doTeacherLogout(HttpServletRequest request, 
        HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
    }
	
	
	
	
	//Test method to insert data to database
	private void doStudentUpdateTest(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException{
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
		StudentBean studentInfo = new StudentBean();
		
		studentInfo.setStudentId("2131601056");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545231");
		studentInfo.setName("wangfeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601057");
		studentInfo.setSex("M");
		studentInfo.setPhone("16882545232");
		studentInfo.setName("liufeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601046");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545233");
		studentInfo.setName("ranfeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601076");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545234");
		studentInfo.setName("xiangfeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601026");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545235");
		studentInfo.setName("yufeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601057");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545236");
		studentInfo.setName("guofeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601052");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545237");
		studentInfo.setName("zaifeng");
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601051");
		studentInfo.setSex("M");
		studentInfo.setPhone("16882545238");
		studentInfo.setName("chengfeng");		
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601054");
		studentInfo.setSex("F");
		studentInfo.setPhone("16882545239");
		studentInfo.setName("baifeng");		
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		studentInfo.setStudentId("2131601053");
		studentInfo.setSex("M");
		studentInfo.setPhone("16882545240");
		studentInfo.setName("yufeng");		
		try{
			studentAcc.saveStudent(studentInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		
		}
	
	
	//Test method to insert data to database
	private void doCourseUpdateTest(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException{
		CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		CourseBean classInfo = new CourseBean();
		
		Vector classDetailInfo = new Vector();
		classDetailInfo.addElement("2131601056|0");
		classDetailInfo.addElement("2131601057|1");
		classDetailInfo.addElement("2131601046|1");
		classDetailInfo.addElement("2131601076|2");
		classDetailInfo.addElement("2131601026|0");
		classDetailInfo.addElement("2131601057|0");
		classDetailInfo.addElement("2131601052|0");
		classDetailInfo.addElement("2131601051|0");
		classDetailInfo.addElement("2131601054|0");
		classDetailInfo.addElement("2131601053|0");
		
		
		
		classInfo.setClassId("code10012015116002");
		classInfo.setTime("34");
		classInfo.setName("IT Management");
		classInfo.setTeacherName("John");
		classInfo.setTeacherId("John.xjtu");
		classInfo.setTotal(10);	
		classInfo.setAttend(7);	
		classInfo.setAbsent(2);	
		classInfo.setLeave(1);	
		classInfo.setClassDetailInfo(classDetailInfo);

		try{
			classAcc.saveClass(classInfo);			
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
		}
	
	
	
	
	
	
	
	
	
	
	
	
		
	private void doTeacherChangePassword(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException{
		TeacherBean validTeacher = (TeacherBean)request.getSession().getAttribute("validTeacher");	
		String newPassword = request.getParameter("newPassword");
		TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
		/*String TID = request.getParameter("TID");
		TeacherBean validTeacher = null;
		try{
			validTeacher = teacherAcc.getTeacher(TID);
		}catch(SQLException e){throw new ServletException("Database error",e);}
*/
		validTeacher.setPassword(newPassword);
		
		

		
		//save Bean
			ClassInfoBean validClass = null; 
			try{
				teacherAcc.updateTeacherBean(validTeacher);
				validTeacher = teacherAcc.getTeacherBean(validTeacher.getTeacherId());
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
				
		}
	
	
	
	
	
	
	
	private void doShowSchedule(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException{
		TeacherBean validTeacher = (TeacherBean)request.getSession().getAttribute("validTeacher");	
		String ClassId = request.getParameter("CID");
		/*TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
		String TID = request.getParameter("TID");
		TeacherBean validTeacher = null;
		try{
			validTeacher = teacherAcc.getTeacher(TID);
		}catch(SQLException e){throw new ServletException("Database error",e);}
*/
		
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
		
		//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(ClassId);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) return;
		
		Vector classes = new Vector();
		String[] courses = validClass.getCourses().split(",");
		
		for(int i = 0 ; i<courses.length;i++){
			String temp = courses[i];
			CourseBean tempCour = null;
			try{
				tempCour = courseAcc.getClassInfo(temp);
				String time = tempCour.getTime();
				for(int i2 = 0;i2*4<time.length();i2++){
					String subTime = time.substring(0+4*i2,3+4*i2);
					String index = subTime.substring(1,2);
					CourseBean classInfo = null;
					String CID = Utility.generateCID(temp,subTime.substring(0,1),"0"+index);
					System.out.println("generateCID:"+CID);
					classInfo = courseAcc.getClass(CID);
					if(classInfo == null){
							throw new Exception("Cannot find class");
						}
					classes.addElement(classInfo);
				}
			}catch (SQLException e) {
            throw new ServletException("Database error", e);		
			}catch (Exception e){
			throw new ServletException("Cannot find class", e);
			}	
		}		
		request.setAttribute("classes",classes);
		request.setAttribute("class",ClassId);
		forward("scheduleTemplate.jsp", request, response);
		}
		
	private void doShowScheduleJSON(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException{
		TeacherBean validTeacher = (TeacherBean)request.getSession().getAttribute("validTeacher");	
		String ClassId = request.getParameter("CID");
		/*TeacherAccessBean teacherAcc = (TeacherAccessBean) 
				getServletContext().getAttribute("teacherAcc");
		String TID = request.getParameter("TID");
		TeacherBean validTeacher = null;
		try{
			validTeacher = teacherAcc.getTeacher(TID);
		}catch(SQLException e){throw new ServletException("Database error",e);}
*/
		
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		ClassInfoAccessBean classInfoAcc = (ClassInfoAccessBean) 
				getServletContext().getAttribute("classInfoAcc");
		
		//Get valid class
			ClassInfoBean validClass = null; 
			try{
				validClass = classInfoAcc.getClassInfoBean(ClassId);
			}catch (SQLException e) {
				throw new ServletException("Database error", e);		
			}				
			if(validClass == null) {
				request.setAttribute("error","Invalid Class ID");
				forward(R.JSP.errorTemplateJSP, request, response);		
				return;
			}
		
		Vector classes = new Vector();
		String[] courses = validClass.getCourses().split(",");
		
		for(int i = 0 ; i<courses.length;i++){
			String temp = courses[i];
			CourseBean tempCour = null;
			try{
				tempCour = courseAcc.getClassInfo(temp);
				String time = tempCour.getTime();
				for(int i2 = 0;i2*4<time.length();i2++){
					String subTime = time.substring(0+4*i2,3+4*i2);
					String index = subTime.substring(1,2);
					CourseBean classInfo = null;
					String CID = Utility.generateCID(temp,subTime.substring(0,1),"0"+index);
					System.out.println("generateCID:"+CID);
					classInfo = courseAcc.getClass(CID);
					if(classInfo == null){
							throw new Exception("Cannot find class");
						}
					classes.addElement(classInfo);
				}
			}catch (SQLException e) {
            throw new ServletException("Database error", e);		
			}catch (Exception e){
			throw new ServletException("Cannot find class", e);
			}	
		}		
		request.setAttribute("classes",classes);
		request.setAttribute("class",ClassId);
		forward("scheduleTemplateJSON.jsp", request, response);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void doShowCourse(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException {
		String CID = request.getParameter("CID");
				
		CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
		CourseBean classInfo = null;
		try{
			classInfo = classAcc.getClass(CID);
		}catch(SQLException e){
			throw new ServletException("Database error",e);
		}
		System.out.println("gotCourseId:"+classInfo.getClassId());//test
		
		Vector students = new Vector();
		for(int i = 0;i < classInfo.getClassDetailInfo().size();i++){
			String temp = (String) classInfo.getClassDetailInfo().elementAt(i);
			StudentBean tempStu = null;
			try{
			tempStu = studentAcc.getStudent(temp.substring(0,10));
			}catch(SQLException e){
				throw new ServletException("Database error",e);
			}
			tempStu.setState(Integer.parseInt(temp.substring(11,12)));
			students.addElement(tempStu);
		}

		
		request.setAttribute("validCourse",classInfo);
		request.setAttribute("students",students);

		forward("courseTemplate.jsp", request, response);
		}	
	private void doShowCourseJSON(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException {
		String CID = request.getParameter("CID");
				
		CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
		CourseBean classInfo = null;
		try{
			classInfo = classAcc.getClass(CID);
		}catch(SQLException e){
			throw new ServletException("Database error",e);
		}
		System.out.println("gotCourseId:"+classInfo.getClassId());//test
		
		Vector students = new Vector();
		for(int i = 0;i < classInfo.getClassDetailInfo().size();i++){
			String temp = (String) classInfo.getClassDetailInfo().elementAt(i);
			StudentBean tempStu = null;
			try{
			tempStu = studentAcc.getStudent(temp.substring(0,10));
			}catch(SQLException e){
				throw new ServletException("Database error",e);
			}
			tempStu.setState(Integer.parseInt(temp.substring(11,12)));
			students.addElement(tempStu);
		}

		
		request.setAttribute("validCourse",classInfo);
		request.setAttribute("students",students);

		forward("courseTemplateJSON.jsp", request, response);
		}
	
	
	

	//change the student state in class
	private void doEditStudentState(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException {
		String SID = request.getParameter("SID");
		String CID = request.getParameter("CID");
		String stateType = request.getParameter("stateType");		
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
		CourseAccessBean courseAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");	
		if(stateType==null || stateType.equals("")||SID==null || SID.equals(""))
		doCheckIn(request,response);


				
		CourseBean courseInfo = null;
		try{
			courseInfo = courseAcc.getCourseInstanceBean(CID);
		}catch(SQLException e){
			throw new ServletException("Database error",e);
		}
		System.out.println("gotCourseId:"+courseInfo.getClassId());//test		
				
		Vector students = Utility.arrayToVector(courseInfo.getStudents().split(","));		
				
				
		for(int i = 0;i < students.size();i++){
			String tempStr = (String) students.elementAt(i);			
			if(tempStr.substring(0,10).equals(SID)){
				if(stateType.equals("attend")) tempStr = tempStr.substring(0,10)+"|0";
				if(stateType.equals("absent")) tempStr = tempStr.substring(0,10)+"|1";
				if(stateType.equals("leave")) tempStr = tempStr.substring(0,10)+"|2";
				if(stateType.equals("late")) tempStr = tempStr.substring(0,10)+"|3";
				students.setElementAt(tempStr,i);
				String newStudentsList = Utility.vectorToString(students);
				courseInfo.setStudents(newStudentsList);
				try{
					courseAcc.updateCourseInstanceBean(courseInfo);
				}catch(SQLException e){
					throw new ServletException("Database error",e);
				}	
				break;
			}			
		}			
		doCheckIn(request,response);	
	}
	
	
	
	
	
	
	
	
	private void doCheckIn(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, ServletException {
		String CID = request.getParameter("CID");
		
		
		
		CourseAccessBean classAcc = (CourseAccessBean) 
				getServletContext().getAttribute("classAcc");
		StudentAccessBean studentAcc = (StudentAccessBean) 
				getServletContext().getAttribute("studentAcc");
		CourseBean classInfo = null;
		try{
			classInfo = classAcc.getClass(CID);
		}catch(SQLException e){
			throw new ServletException("Database error",e);
		}
		System.out.println("gotCourseId:"+classInfo.getClassId());//test
		
		Vector students = new Vector();
		for(int i = 0;i < classInfo.getClassDetailInfo().size();i++){
			String temp = (String) classInfo.getClassDetailInfo().elementAt(i);
			StudentBean tempStu = null;
			try{
			tempStu = studentAcc.getStudent(temp.substring(0,10));
			}catch(SQLException e){
				throw new ServletException("Database error",e);
			}
			tempStu.setState(Integer.parseInt(temp.substring(11,12)));
			students.addElement(tempStu);
		}

		
		request.setAttribute("validCourse",classInfo);
		request.setAttribute("students",students);

		forward("checkIn.jsp", request, response);
		}
	
	
	
	
	
	
	
	
	 /**
     * Forwards the request to the login JSP page, with an
     * "errorMsg" parameter containing text to be displayed on
     * the login page.
     */
    private void doForwardToLogin(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String origURL = HttpUtils.getRequestURL(request).toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            origURL += "?" + queryString;
        }
        String loginURL = "adminLogin.jsp" + "?origURL=" + 
            URLEncoder.encode(origURL) +
            "&errorMsg=" + URLEncoder.encode("Please log in first");
        forward(loginURL, request, response);
    }
	
	
	
	

	
	/**
	 * Show the specified page with the parameter "page".
	 */	
    private void doShowPage(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String url = request.getParameter("page");
        if (url == null) {
            throw new ServletException("Missing page info");
        }
        forward(url, request, response);
    }
	 
	 /**
     * Returns an absolute URL, suitable for redirecting a request 
     * back to this servlet, with an "action" parameter set to
     * "showPage" and a "page" parameter set to the specified
     * (relative) page URL. 
     */
    private String getShowPageURL(HttpServletRequest request) {
        return request.getContextPath() + request.getServletPath() + 
            "?action=showPage&page=";
    }
	 
	/**
     * Forwards the request to the specified relative URL.
     */
    private void forward(String url, HttpServletRequest request,
        HttpServletResponse response) 
        throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);		
    }

}