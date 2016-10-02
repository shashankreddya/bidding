package com.auction.action;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.auction.pojo.Items;
import com.auction.util.UtilConstants;

public class AuctionDatesAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute(UtilConstants._LOGINID);
		String target = "/sellerhomepage.jsp";
		String path;
		try {
			String itemName = request.getParameter("itemName");
			String itemId = request.getParameter("itemId");
			Bid bid = new Bid();
			bid.setItemId(Integer.parseInt(itemId));
			bid.setBidStartDate(request.getParameter("startdate"));
			bid.setBidEndDate(request.getParameter("enddate"));
			bid.setLoginId(userid);
			
			boolean  flag = new ItemsDaoManager().addAuctionDates(bid);
			if (flag) {
				request.setAttribute("status", "Auctions dates are set for item : "+itemName);
			} else {
				request.setAttribute("status", "Auction dates alredy set. This auction Started.");
			}

		} catch (Exception e) {
			request.setAttribute("status", "Please try with proper dates ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
