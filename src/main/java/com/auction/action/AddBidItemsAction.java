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
import com.auction.pojo.Category;
import com.auction.util.UtilConstants;

public class AddBidItemsAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String target = "/buyerhomepage.jsp";
		try {
			String userid = (String) session.getAttribute(UtilConstants._LOGINID);
			boolean flag = false;
			String bidPrice = request.getParameter("bidprice");
			String itemId = request.getParameter("itemid");
			
			flag = new ItemsDaoManager().addBiddingPrice(userid,bidPrice,itemId);
			if (flag) {
				request.setAttribute("status", "Bidding Price added successfully");
			} else {
				request.setAttribute("status", "Bidding Price not added. Please try again. ");
			}
		} catch (Exception e) {
			target = "/buyerhomepage.jsp";
			request.setAttribute("status", "Please Try again with proper data");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}
