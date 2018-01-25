package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gb") //GuestbookServlet -> gb로 이름 바꿔줌
public class GuestbookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("servlet 진입");
		
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		if("list".equals(actionName)) {
			System.out.println("list 진입");
			
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			
			request.setAttribute("list", list); //부를이름, 실제데이터
			/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);*/
			WebUtil.forword(request, response, "/WEB-INF/list.jsp");
		} else if("add".equals(actionName)) {
			System.out.println("add 진입");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo = new GuestbookVo(name, password, content);	
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			/*response.sendRedirect("/guestbook2/gb?a=list");*/
			WebUtil.redirect(request, response, "/guestbook2/gb?a=list");
		} else if("deleteform".equals(actionName)) {
			System.out.println("deleteform 진입");
			
			String no = request.getParameter("no");
			request.setAttribute("no", no);
	
			/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteform.jsp");
			rd.forward(request, response);*/
			WebUtil.forword(request, response, "/WEB-INF/deleteform.jsp");
		} else if("delete".equals(actionName)) {
			System.out.println("delete 진입");
			
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			int no1 = Integer.parseInt(no);
			
			GuestbookDao dao = new GuestbookDao();
			
			List<GuestbookVo> list = dao.getList();
			for(GuestbookVo gvo : list){
				if(gvo.getNo() == no1){
					if(gvo.getPassword().equals(password)){
						dao.delete(no1);
					}
				}
			}
			/*response.sendRedirect("/guestbook2/gb?a=list");*/
			WebUtil.redirect(request, response, "/guestbook2/gb?a=list");
		} else {
			System.out.println("잘못입력했습니다.");
		}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
