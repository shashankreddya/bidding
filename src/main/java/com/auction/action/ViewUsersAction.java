package com.auction.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.UserDetails;

public class ViewUsersAction extends HttpServlet{

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
		String target = "/viewUserDetails.jsp";
		try {
			ArrayList<UserDetails> userDetails = new RegistrationDaoManager().getUsers();
				if (!userDetails.isEmpty()) {
					request.setAttribute("user", userDetails);
				} else {
					request.setAttribute("status", "No User's Details available ");
				}
		} catch (Exception e) {
			request.setAttribute("status", "No User's Details available ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
