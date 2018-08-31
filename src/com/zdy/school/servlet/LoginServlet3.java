package com.zdy.school.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdy.school.factory.DaoFactory;
import com.zdy.school.vo.StudentInfo;
import com.zdy.school.vo.TeacherInfo;

@WebServlet("/LoginServlet3")
public class LoginServlet3 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Cookie[] cookieArry= request.getCookies();
		for(Cookie d:cookieArry) {
			System.out.println("缓存个数"+d);
		}
		StudentInfo studentinfo = new StudentInfo();
		TeacherInfo teacherinfo = new TeacherInfo();

		if(cookieArry!=null) {//判断当前是否存在cookie 如果存在 则进行判断，如果不存在 则跳过cookie判断
			String cookieusername = null;
			String cookiepassword = null;
			for(Cookie c:cookieArry) {
				if("cookieusername".equals(c.getName() )) {
					cookieusername=c.getValue();  //拿到cookie的 name叫做cookieusername  的value值
				}
				if("cookiepassword".equals(c.getName() )) {
					cookiepassword=c.getValue();//拿到cookie的 name叫做cookiepassword  的value值
				}
			}
			studentinfo.setStudentTel(cookieusername);
			System.out.println("Cookie信息"+cookieusername);
			studentinfo.setStudentPassword(cookiepassword);
			System.out.println("Cookie信息"+cookiepassword);
		}else {
			studentinfo.setStudentTel(request.getParameter("useraccount") );
			System.out.println("表单信息"+request.getParameter("useraccount") );
			studentinfo.setStudentPassword(request.getParameter("userpassword") );
			System.out.println("表单信息"+request.getParameter("userpassword") );
		}
		//获取权限
		String[] limits = request.getParameterValues("limits");
		int x;
		for(x=0;x<limits.length;x++) {
			System.out.println("登陆者身份是："+limits[x]);
			//学生登陆
			if(limits[x].equals("学生")) {
//				studentinfo.setStudentTel(request.getParameter("useraccount") );
//				System.out.println("学生表单信息"+request.getParameter("useraccount") );
//				studentinfo.setStudentPassword(request.getParameter("userpassword") );
//				System.out.println("学生表单信息"+request.getParameter("userpassword") );
				System.out.println(limits[x].equals("学生"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().studentlogin(studentinfo)) {
						request.getSession().setAttribute("StudentTel", studentinfo.getStudentTel());
						request.getSession().setAttribute("StudentPassword", studentinfo.getStudentPassword());
						Cookie c3=new Cookie("cookieusername",studentinfo.getStudentTel());
						//生命周期60s
						Cookie c4=new Cookie("cookiepassword",studentinfo.getStudentPassword());
						//生命周期60s
						c3.setMaxAge(60*60*24);
						c4.setMaxAge(60*60*24);
						response.addCookie(c3);
						response.addCookie(c4);
						request.getRequestDispatcher("/NewFile.html").forward(request,response);
					} else {
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//教师登陆
			else if(limits[x].equals("教师")) {
//				teacherinfo.setTeacherTel(request.getParameter("useraccount"));
//				System.out.println("教师表单信息"+request.getParameter("useraccount") );
//				
//				teacherinfo.setTeacherPassword(request.getParameter("userpassword") );
//				System.out.println("教师表单信息"+request.getParameter("userpassword") );
				System.out.println(limits[x].equals("教师"));
				System.out.println("************************************************");
				try {
					if (DaoFactory.getUsersInstances().teacherlogin(teacherinfo)) {
						request.getSession().setAttribute("TeacherTel", teacherinfo.getTeacherTel());
						request.getSession().setAttribute("TeacherPassword", teacherinfo.getTeacherPassword());
						Cookie c3=new Cookie("cookieusername",teacherinfo.getTeacherTel());
						//生命周期60s
						Cookie c4=new Cookie("cookiepassword",teacherinfo.getTeacherPassword());
						//生命周期60s
						c3.setMaxAge(60*60*24);
						c4.setMaxAge(60*60*24);
						response.addCookie(c3);
						response.addCookie(c4);
						request.getRequestDispatcher("/NewFile.html").forward(request,response);
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
		
		
	}

}
