package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.UserDetails;
import com.auction.util.UtilConstants;

public class FeedBackAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		String target = "/feedback.jsp";
		String userid = (String) session.getAttribute(UtilConstants._LOGINID);
		String role =   (String) session.getAttribute(UtilConstants._ROLE);
		boolean flag;
		try {
			flag = new ItemsDaoManager().sendMessage(userid,message,subject);
			if(flag){
				request.setAttribute("status", "Feedback send successfully.");
			}else{
				request.setAttribute("status", "Feedback sending failed.");
			}
		} catch (Exception e) {
			request.setAttribute("status", "Please try again");
		} finally {

			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}
