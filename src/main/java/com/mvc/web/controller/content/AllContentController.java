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

@WebServlet("/board/content/allcontent")
public class AllContentController extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userNM=req.getSession().getAttribute("userNM").toString();
		String userID=req.getSession().getAttribute("userID").toString();
		String userRank=req.getSession().getAttribute("userRank").toString();
		
		//세션 값 테스트
		System.out.println("CONTENT-userID :"+userID);
		System.out.println("CONTENT-userNM :"+userNM);
		System.out.println("CONTENT-userRank :"+userRank);
		
		ContentDAO nd = new ContentDAO();
		String page_ = req.getParameter("p");	//page
		
		String query_=req.getParameter("q");	//검색어
		
		String query = "";						//검색어 초기값
		int page=1;
		
		//System.out.println("query_: "+query_);
		//List<Notice>list = new ArrayList<>();
		
		
		if(page_!=null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		
		if(query_!=null && !query_.equals(""))
			query = query_;
		System.out.println("query_: "+query_);
		System.out.println("query: "+query);
		//int count = ContentDAO.getInstance().getCount(field, query,userRank);
		//System.out.println("controller :"+count);
		
		
		Etclist el = ContentDAO.getInstance().getAllContent(page,query,userRank);
		
		int count = el.getCount();
		
		List<Notice>list = el.getList();	//Etclist로 부터 list 꺼냄
		//System.out.println("bbbb : "+list.get(0).toString());
		req.setAttribute("name", userNM);
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		//String str = req.getParameter("cnt");
		
		req.getRequestDispatcher("/WEB-INF/board/content/AllContentList.jsp").forward(req, resp);
	}
}
