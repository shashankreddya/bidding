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
import com.auction.pojo.Bid;
import com.auction.pojo.Category;
import com.auction.util.UtilConstants;

public class SeeAuctionDatesAction extends HttpServlet {

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
		String target = "/seeAuctionDates.jsp";
		try {
			String itemId = request.getParameter("itemId");
			String itemName = request.getParameter("itemName");
			String user = (String) session.getAttribute(UtilConstants._LOGINID);
			ArrayList<Bid> bid = new ItemsDaoManager().getBid(user,Integer.parseInt(itemId));
			if (!bid.isEmpty()) {
				request.setAttribute("itemName",itemName);
				request.setAttribute("bid", bid);
			}else{
				request.setAttribute("status", "Dates not set by Seller");
			}
		} catch (Exception e) {
			request.setAttribute("status", "No Categories ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
