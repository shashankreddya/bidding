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

public class ViewWinners extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String target = "viewWinners.jsp";
		@SuppressWarnings("unused")
		String path = null;
		ArrayList<UserDetails> winners = null;
		try {
			String user = (String) session.getAttribute(UtilConstants._LOGINID);
			String role = (String) session.getAttribute(UtilConstants._ROLE);
			winners = new RegistrationDaoManager().getWinners();
			if (!winners.isEmpty()) {
				request.setAttribute("winners", winners);
				request.setAttribute("role", role);
			}
		} catch (Exception e) {
			request.setAttribute("status", "There are no bids and no winnes right now.");
			
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}

