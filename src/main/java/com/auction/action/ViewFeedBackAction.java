package com.auction.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.pojo.Feedback;
import com.auction.pojo.Transaction;
import com.auction.util.UtilConstants;

public class ViewFeedBackAction extends HttpServlet {

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
		String target = "/viewFeedback.jsp";
		
		try {
			String userid = (String) session.getAttribute(UtilConstants._LOGINID);
			String role =   (String) session.getAttribute(UtilConstants._ROLE);
			request.setAttribute("role",role);
			
			ArrayList<Feedback> feedback = new ItemsDaoManager().viewFeedBack(userid,role);
			if (!feedback.isEmpty()) {
				request.setAttribute("feedback",feedback);
			} else {
				request.setAttribute("status", "No Messages ");
			}
		} catch (Exception e) {
			request.setAttribute("status", "No Messages ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}