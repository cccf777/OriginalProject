package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.service.UserDAO;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;


@WebServlet("/board/content/regedit")
public class ContentRegeditController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String userNM = (req.getSession().getAttribute("userNM")).toString();
		String userID = (req.getSession().getAttribute("userID")).toString();
		HttpSession session = req.getSession();
		req.setAttribute("name", userNM);
		req.setAttribute("userID", userID);
		
		String title= req.getParameter("title");
		String content=req.getParameter("content");
		int boardid=1;
		
		
		//Notice nt = new Notice(userID,title,content);
		
		ContentDAO nd = new ContentDAO();//DAO에 insert 진행
		int result = nd.regeditNotice(userID,title,content,boardid);
		
		resp.sendRedirect("list");
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/board/content/regedit.jsp").forward(req, resp);
	}
}
