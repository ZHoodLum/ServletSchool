package com.zdy.school.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdy.school.factory.DaoFactory;
import com.zdy.school.vo.StudentInfo;

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		StudentInfo studentinfo = new StudentInfo();
		studentinfo.setStudentTel(request.getParameter("useraccount"));
		studentinfo.setStudentPassword(request.getParameter("userpassword"));
		
		String[] limits = request.getParameterValues("limits");
		
		for(int x=0;x<limits.length;x++) {
			System.out.println("登陆者身份是："+limits[x]);
			if(limits[x].equals("学生")) {
				try {
					if (DaoFactory.getUsersInstances().studentlogin(studentinfo)) {
						request.getSession().setAttribute("StudentTel", studentinfo.getStudentTel());
						request.getSession().setAttribute("StudentPassword", studentinfo.getStudentPassword());
//					response.sendRedirect("/NewFile.html");
						request.getRequestDispatcher("/NewFile.html").forward(request,response);
					} else {
//					request.getRequestDispatcher("/login/login.jsp").forward(request,response);
						response.sendRedirect("/School/login/login.jsp");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("出错啦！");
			}
			
		}
		
	}

}
