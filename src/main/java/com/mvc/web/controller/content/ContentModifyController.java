package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.ContentDAO;


@WebServlet("/board/content/modify")
public class ContentModifyController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String userNM = (req.getSession().getAttribute("userNM")).toString();
		String userID = (req.getSession().getAttribute("userID")).toString();
		String pid_ = req.getParameter("id");//modify.jsp에서 hidden으로 보낸값을 받음
		
		HttpSession session = req.getSession();
		req.setAttribute("name", userNM);
		req.setAttribute("userID", userID);

		
		int pid = Integer.parseInt(pid_);
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		// Notice nt = new Notice(userID,title,content);

		
		int result = ContentDAO.getInstance().updateContent(pid,userID, title, content);

		if(result>0)
			resp.sendRedirect("/board/content/detail?id="+pid);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String par = req.getParameter("id");
		String field_ = req.getParameter("f");
		String query_ = req.getParameter("q");
		String field = "title";
		String query = "";

		int id = 0;

		if (par != null && !par.equals("")) {
			id = Integer.parseInt(par);
		}

		if (field_ != null && !field_.equals("")) {
			field = field_;
		}
		if (query_ != null && !query_.equals("")) {
			query = query_;
		}

		Notice nt = ContentDAO.getInstance().getDetail(id);
		req.setAttribute("nt", nt);// nt는 노티스를 받고 , "nt"는 이름이다.
		req.setAttribute("f", field);
		req.setAttribute("q", query);

		req.getRequestDispatcher("/WEB-INF/board/content/Modify.jsp").forward(req, resp);
	}
}
