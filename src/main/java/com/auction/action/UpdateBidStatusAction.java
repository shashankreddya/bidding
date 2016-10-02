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

public class UpdateBidStatusAction extends HttpServlet {

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
		String target = "/adminhomepage.jsp";
		String message = null;
		try {
			String bidId = request.getParameter("bidid");
			Bid bid = new Bid();
			bid.setBidId(Integer.parseInt(bidId));
			bid.setBidStartDate(request.getParameter("startdate"));
			bid.setBidEndDate(request.getParameter("enddate"));
			bid.setStatus(request.getParameter("status"));
			//String user = (String) session.getAttribute(UtilConstants._LOGINID);
			boolean flag = new ItemsDaoManager().changeStatus(bid);
			if("init".equalsIgnoreCase(request.getParameter("status"))){
				message = "Dates only updated by Admin. Please change status if you want to start auction";
			}else{
				message = "Status updated by Admin. Now Auction start on "+request.getParameter("startdate");
			}
			if (flag) {
				request.setAttribute("status", message);
			}else{
				request.setAttribute("status", "Status updation failed");
			}
		} catch (Exception e) {
			request.setAttribute("status", "Try again. Status updation failed");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}

