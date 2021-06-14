package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.service.ContentDAO;



@WebServlet("/board/user/Ugrade")
public class ModifyUserGrade extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String userID = (req.getSession().getAttribute("userID")).toString();
		HttpSession session = req.getSession();
		
		String [] check = req.getParameterValues("mrank");
		String grade_ = check[0];
		
		/*
		 * for(int i=0; i<check.length; i++){ System.out.println("체크값은?!!!!"+check[i]);
		 * }
		 * 
		 * switch(check[0]) {
		 * 
		 * case "A": req.setAttribute("userR",A); break; case "G":
		 * req.setAttribute("userR",G); break; case "U": req.setAttribute("userR",U);
		 * break; }
		 */
		ContentDAO cd = new ContentDAO(); 
		cd.usergrade(userID,grade_);
		
		
		   //req.getRequestDispatcher("../content/list").forward(req, resp);
		   resp.sendRedirect("../content/list");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * String userRank = req.getSession().getAttribute("userRank").toString();
		 * String rankval = null;
		 * 
		 * switch (userRank) { case "G": req.setAttribute("guest", guest);
		 * 
		 * break; case "A": req.setAttribute("admin", rankval); break; case "U":
		 * req.setAttribute("user", rankval); break;
		 * 
		 * }
		 */

		req.getRequestDispatcher("/WEB-INF/board/user/Usergrade.jsp").forward(req, resp);
	}
}
