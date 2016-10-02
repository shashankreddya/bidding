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
import com.auction.pojo.Items;

public class ViewItemsAction extends HttpServlet {

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
		String target = "/viewItems.jsp";
		String path;
		try {
			String singleItem = request.getParameter("singleItem");
			path = request.getSession().getServletContext().getRealPath("/tmpImage");
			String path1 =  request.getContextPath()+"/tmpImage";
			ArrayList<Items> itemsList = new ItemsDaoManager().getItems(path,path1);
			if (StringUtils.isBlank(singleItem)) {
				if (!itemsList.isEmpty()) {
					request.setAttribute("itemsList", itemsList);
				} else {
					request.setAttribute("status", "No Items ");
				}
			} else {
				int singleItemId = Integer.parseInt(singleItem);
				for (Items item : itemsList) {
					if (item.getItemId() == singleItemId) {
						request.setAttribute("singleItem", item);
						target = "itemInfo.jsp";
					} 
				}
			}
		} catch (Exception e) {
			request.setAttribute("status", "No Items ");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
