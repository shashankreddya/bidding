package com.auction.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.UserDetails;
import com.auction.util.UtilConstants;

public class ViewProfileAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = "/editUserDetails.jsp";
		HttpSession  session = request.getSession();
		String userid = (String)session.getAttribute(UtilConstants._LOGINID);
		try {
			ArrayList<UserDetails> userDetails = new RegistrationDaoManager().getUsers(userid);
				if (!userDetails.isEmpty()) {
					request.setAttribute("user", userDetails);
				} else {
					request.setAttribute("status", "Please try again.");
				}
		} catch (Exception e) {
			request.setAttribute("status", "Please try later.");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
