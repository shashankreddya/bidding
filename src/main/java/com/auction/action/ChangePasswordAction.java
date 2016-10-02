package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StringContent;

import org.apache.commons.lang3.StringUtils;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.util.UtilConstants;

public class ChangePasswordAction extends HttpServlet {

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
		String target = "/changepassword.jsp";
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String userid = (String) session.getAttribute(UtilConstants._LOGINID);
		System.out.println(userid);
		boolean flag;
		try {
			flag = new RegistrationDaoManager().changePassword(userid, oldpassword, newpassword);
			if (flag) {
                  request.setAttribute("status", "Password changed succfully.");
			}
		} catch (Exception e) {
			request.setAttribute("status", "Password chaged failed. Try again.");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
