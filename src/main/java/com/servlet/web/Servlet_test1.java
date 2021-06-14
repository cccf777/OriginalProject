package com.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/antss")
public class Servlet_test1 extends HttpServlet  {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//서비스가 메인이다 얘부터 시작한다. req는 입력 res는 출력을 담당한다. 입력은 전부 스트링임
		//System.out.println("hello Servlet");
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();

		int cnt=0;
		
		if(req.getParameter("cnt")!=null && req.getParameter("cnt")=="") {
			cnt=Integer.parseInt(req.getParameter("cnt"));
		}
		
		
		
		out.println("cnt : "+cnt);
		for(int i=0;i<=cnt ; i++) {
			out.println("<h1>한글!</h1>");
		}
	}
}
