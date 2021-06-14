package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;

import com.mvc.web.service.UserDAO;

@WebServlet("/board/content/detail")
public class ContentDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션값 불러오기
		String userNM=req.getSession().getAttribute("userNM").toString();
		String userID=req.getSession().getAttribute("userID").toString();
		String userRank=req.getSession().getAttribute("userRank").toString();
		
		//세션 값 테스트
		System.out.println("CONTENT-userID :"+userID);
		System.out.println("CONTENT-userNM :"+userNM);
		System.out.println("CONTENT-userRank :"+userRank);
		
		String par = req.getParameter("id");
		String field_ = req.getParameter("f");
		String query_ = req.getParameter("q");		
		String field= "title";
		String query="";		
		
		int id =0;
		
		if (par != null && !par.equals("")) {
			id = Integer.parseInt(par);
		}
		
		if (field_ != null && !field_.equals("")) {
			field=field_;
		}
		if (query_ != null && !query_.equals("")) {
			query=query_;
		}
		
		ContentDAO.getInstance().uphit(id);
		
		Notice nt = ContentDAO.getInstance().getDetail(id);
		
		req.setAttribute("sid", userID);
		req.setAttribute("srank", userRank);
		
		req.setAttribute("nt", nt);//nt는 노티스를 받고 , "nt"는 이름이다.
		req.setAttribute("f",field);
		req.setAttribute("q",query);
		
		req.getRequestDispatcher("/WEB-INF/board/content/Detail1.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//int result = ContentDAO.getInstance().updateContent(title,content);
		
		resp.sendRedirect("list");
	}
}
