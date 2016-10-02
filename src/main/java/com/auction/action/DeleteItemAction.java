package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.daomgr.ItemsDaoManager;

public class DeleteItemAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String target = "/adminhomepage.jsp";
		try {
			    String itemId = request.getParameter("itemid");
			    int id = Integer.parseInt(itemId);
				boolean flag = false;
				flag = new ItemsDaoManager().deleteItems(id);
				if (flag) {
					request.setAttribute("status", "Item deleted successfully");
				} else {
					request.setAttribute("status", "Item not deleted. Please try later. ");
				}
		} catch (Exception e) {
			target = "/adminhomepage.jsp";
			request.setAttribute("status", "Please Try again ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}

}
