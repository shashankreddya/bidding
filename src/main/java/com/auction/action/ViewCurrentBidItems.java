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

import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.pojo.Category;
import com.auction.pojo.Items;
import com.auction.util.UtilConstants;

public class ViewCurrentBidItems extends HttpServlet {

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
		String target = "currentBidItems.jsp";
		@SuppressWarnings("unused")
		String path = null;
		ArrayList<Category> category = null;
		try {
			String user = (String) session.getAttribute(UtilConstants._LOGINID);
			String role = (String) session.getAttribute(UtilConstants._ROLE);
			String task = request.getParameter("task");
			String check = request.getParameter("check");
			if (check.equalsIgnoreCase("current")) {
				target = "currentBidItems.jsp";
			} else if (check.equalsIgnoreCase("endtoday")) {
				target = "endItemsToday.jsp";
			} else if (check.equalsIgnoreCase("starttoday")) {
				target = "todayItems.jsp";
			}
			request.setAttribute("role", role);
			if (StringUtils.isBlank(task)) {
				String categoryName = request.getParameter("categoryName");
				path = request.getSession().getServletContext().getRealPath("/tmpImage");
				ArrayList<Items> bidItems = new ItemsDaoManager().getBidItems(categoryName, path, check);
				if (!bidItems.isEmpty()) {
					request.setAttribute("bidItems", bidItems);
				} else {
					request.setAttribute("status", "Auction Items are not available.");
				}
			}
			category = new ItemsDaoManager().getCategories();
			if (!category.isEmpty()) {
				request.setAttribute("category", category);
			}
		} catch (Exception e) {
			request.setAttribute("status", "No items are in this category");
			category = new ItemsDaoManager().getCategories();
			if (!category.isEmpty()) {
				request.setAttribute("category", category);
			}
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
