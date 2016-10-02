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

public class ViewItemsByCategoryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String target = "/addItemsBySeller.jsp";
		String path;
		try {
			String user = (String) session.getAttribute(UtilConstants._LOGINID);
			if(user.equalsIgnoreCase(UtilConstants._ADMIN)){
				target = "/viewItemsByAdmin.jsp";
			}
			String categoryName = request.getQueryString().split("=")[1];
			categoryName =  URLDecoder.decode(categoryName, "UTF-8").trim();
			path = request.getSession().getServletContext().getRealPath("/tmpImage");
			ArrayList<Items> itemsList = new ItemsDaoManager().getItems(path,categoryName);
				if (!itemsList.isEmpty()) {
					request.setAttribute("itemsList", itemsList);
					request.setAttribute("categoryName",categoryName);
				} else {
					request.setAttribute("status", "No Items ");
				}
				ArrayList<Category> category = new ItemsDaoManager().getCategories();
				if (!category.isEmpty()) {
					request.setAttribute("category", category);
				}
				
				
		} catch (Exception e) {
			request.setAttribute("status", "No Items ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
