package com.auction.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.ItemsDaoManager;
import com.auction.pojo.Category;
import com.auction.util.UtilConstants;

public class AddCategoryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String task = req.getParameter("task");
		String categoryname = req.getParameter("category");
		String target = "/addCategory.jsp";
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(task) && task.equalsIgnoreCase("categories")) {
				flag = new ItemsDaoManager().addCategory(categoryname);
				if (flag) {
					req.setAttribute("status", "Category Added Successfully");
				} else {
					req.setAttribute("status", "Category NOT added. Please again");
				}
			} else if (StringUtils.isNotBlank(task) && task.equalsIgnoreCase("update")) {
				String categoryId = req.getParameter("categoryId");
				flag = new ItemsDaoManager().updateCategory(categoryname,categoryId);
				if (flag) {
					req.setAttribute("status", "Category Updated Successfully");
				} else {
					req.setAttribute("status", "Category NOT Updated. Please try again");
				}
			} else if (StringUtils.isNotBlank(task) && task.equalsIgnoreCase("delete")) {
				String categoryId = req.getParameter("categoryId");
				flag = new ItemsDaoManager().deleteCategory(categoryId);
				if (flag) {
					req.setAttribute("status", "Category deleted Successfully");
				} else {
					req.setAttribute("status", "Category NOT deleted. Please try later");
				}
			}

			else if (StringUtils.isNotBlank(task) && task.equalsIgnoreCase("viewCategory")) {

				target = "/viewCategory.jsp";
			}
			ArrayList<Category> category = new ItemsDaoManager().getCategories();
			if (!category.isEmpty()) {
				req.setAttribute("category", category);
			}
		} catch (Exception e) {
			target = "/addCategory.jsp";
			req.setAttribute("status", "Please Try again with proper data. Duplicates are not allowed");
		} finally {
			RequestDispatcher rd = req.getRequestDispatcher(target);
			rd.forward(req, resp);
		}
	}
}
