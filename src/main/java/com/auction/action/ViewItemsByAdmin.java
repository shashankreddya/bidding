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
import com.auction.pojo.Items;

public class ViewItemsByAdmin extends HttpServlet {

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
		String target = "/viewItemsByAdmin.jsp";
		String path;
		try {
			ArrayList<Category> category = new ItemsDaoManager().getCategories();
			if (!category.isEmpty()) {
				request.setAttribute("category", category);
			}
		} catch (Exception e) {
			request.setAttribute("status", "No Categories ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
