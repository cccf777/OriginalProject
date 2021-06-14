package com.calc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal/calc")
public class calc extends HttpServlet{
	//int result;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String x_= req.getParameter("x");
//		String y_= req.getParameter("y");
//		
//		int x =0;
//		int y = 0;
//		
//		if(!x_.equals(""))
//			x=Integer.parseInt(x_);
//		
//		if(!y_.equals(""))
//			y=Integer.parseInt(y_);
		
		//getParameterValues는 배열로 들어감 체크박스에서 하나둘 체크한값 담을때 쓴다
		//post가 그런식이니까
		String x_[] = req.getParameterValues("num");
		
		int result=0;
		for(int i =0; i<x_.length; i++) {
			int num = Integer.parseInt(x_[i]);
			result +=num;
		}
		
		
		String op = req.getParameter("operator");
		
		/*
		 * int result = 0;
		 * 
		 * switch(op) { case "Plus" : result = x+y; break; case "Minus" : result = x-y;
		 * break; case "Multiple" : result = x*y; break;
		 * 
		 * default : System.out.println("똑디 입력해라 "); break; }
		 */
		
		req.setAttribute("result", result);
		//히스토리백 리 다이렉트 즉 값을 가지고 돌아가는 것
		doGet(req, resp);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * String x_= req.getParameter("x"); String y_= req.getParameter("y");
		 * 
		 * System.out.println("x  :"+x_); System.out.println("y  :"+y_);
		 */
		
		  
		  req.getRequestDispatcher("/WEB-INF/cal/calc.jsp").forward(req, resp);
		 
	}
	
}
