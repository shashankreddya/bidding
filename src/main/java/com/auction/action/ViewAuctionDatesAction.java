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
import com.auction.util.UtilConstants;

public class ViewAuctionDatesAction extends HttpServlet {

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
		String target = "/seeAuctionDatesBySeller.jsp";
		try {
			String user = (String) session.getAttribute(UtilConstants._LOGINID);
			ArrayList<Bid> bid = new ItemsDaoManager().getBid(user);
			if (!bid.isEmpty()) {
				request.setAttribute("bid", bid);
			}else{
				request.setAttribute("status", "Auction dates not set yet for Items");
			}
		} catch (Exception e) {
			request.setAttribute("status", "First set auction dates to items");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}

