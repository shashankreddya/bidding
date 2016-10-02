package com.auction.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.UserDetails;
import com.auction.util.UtilConstants;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDetails ud = new UserDetails();
		String username = request.getParameter("userName");
		username = username.toUpperCase();
		ud.setLoginId(username.toUpperCase());
		String password = request.getParameter("password");
		ud.setPassword(password);
		String target = "";
		String role = "";

		try {
			role = new RegistrationDaoManager().getRole(ud);

			if (role.equalsIgnoreCase(UtilConstants._ADMIN)) {

				request.setAttribute("status", "Welcome " + username);

				target = UtilConstants._ADMIN_HOME;

				session.setAttribute(UtilConstants._LOGINID, username);
				session.setAttribute(UtilConstants._ROLE, role);
				// session.setAttribute(UtilConstants._PASSWORD,password);

			} else if (role.equalsIgnoreCase(UtilConstants._SELLER)) {

				request.setAttribute("status", "Welcome " + username);
				target = UtilConstants._SELLER_HOME;

				session.setAttribute(UtilConstants._LOGINID, username);
				session.setAttribute(UtilConstants._ROLE, role);

			} else if (role.equalsIgnoreCase(UtilConstants._BUYER)) {
				request.setAttribute("status", "Welcome " + username);
				target = UtilConstants._BUYER_HOME;

				session.setAttribute(UtilConstants._LOGINID, username);
				session.setAttribute(UtilConstants._ROLE, role);
			} else {
				request.setAttribute("status", UtilConstants._INVALID_USER);
				target = UtilConstants._LOGIN_PAGE;
			}

		} catch (Exception e) {
			request.setAttribute("status", UtilConstants._INVALID_USER_EXCEPTION);
			target = UtilConstants._LOGIN_PAGE;
		} finally {

			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}
