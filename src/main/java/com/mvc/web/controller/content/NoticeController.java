package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Etclist;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;

@WebServlet("/board/content/notice")
public class NoticeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userNM=req.getSession().getAttribute("userNM").toString();
		String userID=req.getSession().getAttribute("userID").toString();
		String userRank=req.getSession().getAttribute("userRank").toString();
		
		System.out.println("CONTENT-userID :"+userID);
		System.out.println("CONTENT-userNM :"+userNM);
		System.out.println("CONTENT-userRank :"+userRank);
		
		ContentDAO nd = new ContentDAO();
		String page_ = req.getParameter("p");	
		String field_=req.getParameter("f");
		String query_=req.getParameter("q");	
		String field="title";
		String query = "";						
		int page=1;
		
		if(page_!=null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		if(field_!=null && !field_.equals(""))
			field = field_;
		
		if(query_!=null && !query_.equals(""))
			query = query_;
				
		Etclist el = ContentDAO.getInstance().getList(page,field,query,userRank);
		
		int count = el.getCount();
		
		List<Notice>list = el.getList();	
		req.setAttribute("name", userNM);
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/board/content/ContentList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
