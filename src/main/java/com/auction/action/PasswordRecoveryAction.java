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
import com.auction.daomgr.RegistrationDaoManager;
import com.auction.pojo.Items;

public class PasswordRecoveryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String target = "/passwordrecovery.jsp";
		String userName = request.getParameter("userName");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String password = null;
		try {
			password = new RegistrationDaoManager().recoveryPassword(userName, question, answer);
			if (StringUtils.isNotBlank(password)) {
                  request.setAttribute("password","Password : " +password);
                  request.setAttribute("status", "Please login using this password.");
			}else{
				request.setAttribute("password"," Please provide proper data.");
				request.setAttribute("status", "Problem getting while password recover");
				
			}
		} catch (Exception e) {
			request.setAttribute("status", "Somrthing went wrong. Please try again.");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}

}
