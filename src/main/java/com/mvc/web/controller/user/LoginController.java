package com.mvc.web.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.User;
import com.mvc.web.service.UserDAO;

@WebServlet("/user/login")
public class LoginController extends HttpServlet{
	private static final Integer cookieExpire = 60*60*24*30;//1 month 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String pid = req.getParameter("id");
		String ppass = req.getParameter("pass");
		String premember = req.getParameter("remember");
		
		Login lg = new Login(pid,ppass);
		
		User ur = (UserDAO.getInstance().LoginCheck(lg));
		

		PrintWriter out=resp.getWriter();
		
		int result = ur.getNumber();
		System.out.println("result값:"+result);
		switch(result) {
		case -1: 
			//login창으로 다시 보낸다.
			
			/*
			 * req.setAttribute("ment", "pw가 틀렸습니다."); doGet(req,resp);
			 */
			out.println("<script>alert('비밀번호가 틀렸습니다.');");
			 
			out.flush();

			out.println("history.back(); </script>");
			break;
			
		case 0:
			//login창으로 다시 보낸다.
			
			/*
			 * req.setAttribute("ment", "존재하지 않는 id입니다."); doGet(req,resp); break;
			 */
			out.println("<script>alert('존재하지 않는 id입니다.');");
			 
			out.flush();

			out.println("history.back(); </script>");
			//doGet(req,resp);
			break;
			
		
		case 1://로그인 성공
			//login창으로 다시 보낸다.
			System.out.println("넘어옴");
			//user 객체 내 정보를 변수로 이동
			String userID = ur.getId();
			String userNM= ur.getName();
			String userRank = ur.getRank();
			String userEmail = ur.getEmail();
			
			
			HttpSession session = req.getSession();
			session.setAttribute("userID",userID );
			session.setAttribute("userNM",userNM);
			session.setAttribute("userRank",userRank);
			session.setAttribute("userEmail",userEmail);
			
			
			//틓하리렁츠 ip추출
			String userIP = req.getHeader("X-Forwarded-For");
			if(userIP==null) 
				userIP = req.getRemoteAddr();
			
			//접속한 인원 테이블에 접속자 정보 입력
			UserDAO.getInstance().setLoginInfo(userID,userIP);
			
			
			
			
			
			if(premember!=null) {
				setCookie("sid",pid,resp);
			}else{
				setCookie("sid","",resp);
			}
			
			//String userNM = (String)req.getSession().getAttribute("userNM");
			
			
			
			System.out.println("세션이름 : "+(req.getSession().getAttribute("userID")).toString());
			System.out.println("세션이름 : "+(req.getSession().getAttribute("userNM")).toString());
			System.out.println("세션이름 : "+(req.getSession().getAttribute("userRank")).toString());
			System.out.println("세션이름 : "+(req.getSession().getAttribute("userEmail")).toString());
			
			resp.sendRedirect("/board/content/list");
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userID = get_Cookie("sid",req); //
		req.setAttribute("id", userID);
		
		req.getRequestDispatcher("/WEB-INF/board/user/login.jsp").forward(req, resp);
	}
	
	//쿠키 불러오기
	private String get_Cookie(String cid, HttpServletRequest req) {
		String ret= "";
		
		if(req==null) {
			return ret;
		}
		Cookie[] cookies = req.getCookies();
		if(cookies == null) {
			return ret;
		}
		
		for(Cookie ck : cookies) {
			if(ck.getName().equals(cid)) {
				
				ret=ck.getValue();
				
				System.out.println("ck의 이름 :"+ck.getName());
				System.out.println("ck의 값 :"+ck.getValue());
				System.out.println("ret :"+ret);
				ck.setMaxAge(cookieExpire);
				break;
			}
		}
		
		return ret;
	}

	private void setCookie(String cid, String pid, HttpServletResponse resp) {
		Cookie ck = new Cookie(cid,pid); //sid, pid(접속한 사람 id)
		ck.setPath("/");
		ck.setMaxAge(cookieExpire);
		resp.addCookie(ck);
	}
}
