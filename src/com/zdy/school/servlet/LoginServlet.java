package com.zdy.school.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.zdy.school.factory.DaoFactory;
import com.zdy.school.vo.AdminInfo;
import com.zdy.school.vo.EnterpriseInfo;
import com.zdy.school.vo.StudentInfo;
import com.zdy.school.vo.TeacherInfo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		StudentInfo studentinfo = new StudentInfo();
		TeacherInfo teacherinfo = new TeacherInfo();
		EnterpriseInfo enterpriseinfo = new EnterpriseInfo();
		AdminInfo admininfo = new AdminInfo();
			
		//获取权限
		String[] limits = request.getParameterValues("limits");
		int x;
		for(x=0;x<limits.length;x++) {
			System.out.println("登陆者身份是："+limits[x]);
			//学生登陆
			if(limits[x].equals("学生")) {
				studentinfo.setStudentTel(request.getParameter("useraccount") );
				System.out.println("学生表单信息"+request.getParameter("useraccount") );
				studentinfo.setStudentPassword(request.getParameter("userpassword") );
				System.out.println("学生表单信息"+request.getParameter("userpassword") );
				
				System.out.println(limits[x].equals("学生"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().studentlogin(studentinfo)) {
//						request.getSession().setAttribute("StudentTel", studentinfo.getStudentTel());
//						request.getSession().setAttribute("StudentPassword", studentinfo.getStudentPassword());
//						request.getSession().setAttribute("StudentName", studentinfo.getStudentName());
						request.getSession().setAttribute("StudentInfo", studentinfo);
						
						request.getRequestDispatcher("/index.jsp").forward(request,response);
					} else {
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//教师登陆
			else if(limits[x].equals("教师")) {
				teacherinfo.setTeacherTel(request.getParameter("useraccount"));
				System.out.println("教师表单信息"+request.getParameter("useraccount") );
				
				teacherinfo.setTeacherPassword(request.getParameter("userpassword") );
				System.out.println("教师表单信息"+request.getParameter("userpassword") );
				
				System.out.println(limits[x].equals("教师"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().teacherlogin(teacherinfo)) {
//						request.getSession().setAttribute("TeacherTel", teacherinfo.getTeacherTel());
//						request.getSession().setAttribute("TeacherPassword", teacherinfo.getTeacherPassword());
						request.getSession().setAttribute("TeacherInfo", teacherinfo);
						
						request.getRequestDispatcher("/index.jsp").forward(request,response);
					} else {
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//企业登陆
			else if(limits[x].equals("企业")) {
				enterpriseinfo.setEnterpriseId(Integer.parseInt(request.getParameter("useraccount")));
				System.out.println("企业表单信息"+request.getParameter("useraccount") );
				
				enterpriseinfo.setEnterprisePassword(request.getParameter("userpassword") );
				System.out.println("企业表单信息"+request.getParameter("userpassword") );
				
				System.out.println(limits[x].equals("企业"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().enterpriselogin(enterpriseinfo)) {
//						request.getSession().setAttribute("TeacherTel", teacherinfo.getTeacherTel());
//						request.getSession().setAttribute("TeacherPassword", teacherinfo.getTeacherPassword());
						request.getSession().setAttribute("EnterpriseInfo", enterpriseinfo);
						
						request.getRequestDispatcher("/index.jsp").forward(request,response);
					} else {
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//管理员
			else if(limits[x].equals("管理员")) {
				admininfo.setAdminId(Integer.parseInt(request.getParameter("useraccount")));
				System.out.println("管理员表单信息"+request.getParameter("useraccount") );
				
				admininfo.setAdminPassword(request.getParameter("userpassword"));
				System.out.println("管理员表单信息"+request.getParameter("userpassword") );
				
				System.out.println(limits[x].equals("管理员"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().adminlogin(admininfo)) {
						request.getSession().setAttribute("AdminInfo", admininfo);
						
						request.getRequestDispatcher("/index.jsp").forward(request,response);
					} else {
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("出错啦！");
			}	
		}
		//for循环停止
		
	}

}
