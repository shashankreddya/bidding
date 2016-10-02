package com.auction.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.pojo.Items;
import com.auction.pojo.Transaction;
import com.auction.util.UtilConstants;

public class ViewTransactions extends HttpServlet {

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
		String target = "/viewAllTransactions.jsp";
		
		try {
			String userid = (String) session.getAttribute(UtilConstants._LOGINID);
			String role =   (String) session.getAttribute(UtilConstants._ROLE);
			request.setAttribute("role",role);
			
			ArrayList<Transaction> transation = new ItemsDaoManager().viewTransaction(userid,role);
			if (!transation.isEmpty()) {
				request.setAttribute("transation",transation);
			} else {
				request.setAttribute("status", "No transaction ");
			}
		} catch (Exception e) {
			request.setAttribute("status", "No transaction ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}